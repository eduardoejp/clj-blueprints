;; Copyright (C) 2011, Eduardo Julián. All rights reserved.
;;
;; The use and distribution terms for this software are covered by the 
;; Eclipse Public License 1.0
;; (http://opensource.org/licenses/eclipse-1.0.php) which can be found
;; in the file epl-v10.html at the root of this distribution.
;;
;; By using this software in any fashion, you are agreeing to be bound
;; by the terms of this license.
;;
;; You must not remove this notice, or any other, from this software.

(ns clj-blueprints
  "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 0.7 of the Blueprints API."
  (:import (com.tinkerpop.blueprints.pgm Graph Element Vertex Edge TransactionalGraph TransactionalGraph$Mode TransactionalGraph$Conclusion IndexableGraph Index Index$Type)
    (com.tinkerpop.blueprints.pgm.impls.readonly ReadOnlyGraph ReadOnlyEdge ReadOnlyVertex ReadOnlyIndex)
    (com.tinkerpop.blueprints.pgm.util.graphml GraphMigrator GraphMLReader GraphMLWriter))
  (:use active-vars))

(def #^{:doc "This dynamic var holds the currently used database."}
  *db* nil)

; Element fns
(defn get-id "" [elem] (.getId elem))

(defn pget "Gets an element's property (given as a keyword)."
  [elem prop] (.getProperty elem (name prop)))

(defn passoc! "Assocs an element's property (given as a keyword)."
  ([elem prop v] (.setProperty elem (name prop) v) elem)
  ([elem prop v & kvs] (.setProperty elem (name prop) v) (apply passoc! elem kvs)))

(defn pdissoc! "Dissocs an element's property (given as a keyword)."
  [elem prop] (.removeProperty elem (name prop)) elem)

(defn pkeys "Returns an element's keys as keywords."
  [elem] (map keyword (.getPropertyKeys elem)))

(defn pvals "Returns an element's vals."
  [elem] (for [k (pkeys elem)] (pget k)))

(defn as-map "Transforms an element to a Clojure hash-map."
  [elem] (apply hash-map (flatten (for [k (pkeys elem)] [k (pget elem k)]))))

; Transactions
(def +tx-success+ TransactionalGraph$Conclusion/SUCCESS)
(def +tx-failure+ TransactionalGraph$Conclusion/FAILURE)

(defn set-transaction-mode!
  "Sets the transaction mode as either :automatic or :manual."
  [mode]
  (let [mode (case mode :automatic TransactionalGraph$Mode/AUTOMATIC, :manual TransactionalGraph$Mode/MANUAL)]
    (.setTransactionMode *db* mode)))

(defactive *transaction-mode*
  "The transaction mode. Can be either :automatic or :manual."
  (get {TransactionalGraph$Mode/AUTOMATIC :automatic, TransactionalGraph$Mode/MANUAL :manual}
       (.getTransactionMode *db*)))

(defmacro with-tx "Evaluates the given forms inside a transaction."
  [& forms]
  `(if (= :manual (get-transaction-mode))
     (do (.startTransaction *db*)
       (try
         (let [r# (do ~@forms)] (.stopTransaction *db* +tx-success+) r#)
         (catch Exception ~'e (.stopTransaction *db* +tx-failure+) (.printStackStrace ~'e))))
     (throw (Exception. "Transaction mode must be MANUAL."))))

; Graph fns
(defmacro with-db "Evaluates the given forms with the given Graph DB bound to *db*."
  [graph-db & forms]
  `(binding [*db* ~graph-db] ~@forms))

(defn set-db! "Given a Graph instance (like OrientGraph, TinkerGraph, etc), sets it as the global DB."
  [graph-db] (alter-var-root #'*db* (fn [_] graph-db)) graph-db)

(defn shutdown! "" [] (.shutdown *db*))

(defn clear! "Clears *db* of all nodes and edges." [] (.clear *db*))

(defactive *vertices* "All the vertices." (.getVertices *db*))

(defactive *edges* "All the edges." (.getEdges *db*))

(defn load-vertex "" [id] (.getVertex *db* id))

(defn load-edge "" [id] (.getEdge *db* id))

(defn vertex!
  "Adds a vertex to the database. If given a hash-map, sets the properties of the vertex."
  ([id props] (let [v (.addVertex *db* id)] (when props (apply passoc! v (interleave (map name (keys props)) (vals props)))) v))
  ([id] (if-not (map? id) (.addVertex *db* id) (vertex! nil id)))
  ([] (.addVertex *db* nil)))

(defn link!
  "Adds an edge between vertex1 and vertex 2 given a vector like [label props-map]. The label must be a keyword and props-map can be nil."
  ([id v1 [label props] v2] (let [e (.addEdge *db* id v1 v2 (name label))] (when props (apply passoc! e (interleave (map name (keys props)) (vals props)))) e))
  ([v1 lab-props v2] (link! nil v1 lab-props v2)))

(defn remove! "Removes either a vertex or an edge from the Graph."
  [elem]
  (cond
    (isa? (class elem) Vertex) (.removeVertex *db* elem)
    (isa? (class elem) Edge) (.removeEdge *db* elem)))

; Vertex fns
(defn get-edges "Gets the edges from a vertex given the direction (:in or :out) and an optional filtering label (as a keyword)."
  ([vertex dir] (case dir :in (.getInEdges vertex), :out (.getOutEdges vertex), :both (concat (.getInEdges vertex) (.getOutEdges vertex))))
  ([vertex dir label] (filter #(= (.getLabel %) (name label)) (case dir :in (.getInEdges vertex), :out (.getOutEdges vertex), :both (concat (.getInEdges vertex) (.getOutEdges vertex))))))

; Edge fns.
(defn get-vertex "Gets the :in or :out vertex from an edge."
  [edge dir] (case dir :in (.getInVertex edge), :out (.getOutVertex edge)))

(defn get-label "" [edge] (.getLabel edge))

(defn get-ends
  "In case you don't want to get the edges that meet a requirement but the vertices at the end of those, use get-ends just like get-edges."
  ([vertex dir label]
   (if (= :both dir)
     (concat (get-ends vertex :in label) (get-ends vertex :out label))
     (map #(get-vertex % (case dir :in :out, :out :in)) (get-edges vertex dir label))))
  ([vertex dir]
   (if (= :both dir)
     (concat (get-ends vertex :in) (get-ends vertex :out))
     (map #(get-vertex % (case dir :in :out, :out :in)) (get-edges vertex dir)))))

(defn get-link
  "If vertex1 and vertex2 are connected by an edge, it returns that edge."
  [v1 v2]
  (or (some #(when (= v2 (get-vertex % :out)) %) (get-edges v1 :in))
    (some #(when (= v2 (get-vertex % :in)) %) (get-edges v1 :out))))

(defn linked? "Tells whether or not two vertices have an edge between them."
  [v1 v2] (if (get-link v1 v2) true false))

(defn unlink! "Removes the edge between two vertices." [v1 v2] (remove! (get-link v1 v2)))

; Indexes
(defn automatic-index! "" [kname class keys]
  (.createAutomaticIndex *db* (name kname) class (->> keys seq (map name) (apply hash-set))))

(defn manual-index! "" [kname class] (.createManualIndex *db* (name kname) class))

(defn create-index! "" [kname class type]
  (case type
    :automatic (.createAutomaticIndex *db* (name kname) class type)
    :manual 
    ))

(defn get-index "" [kname class] (let [class (case class :vertices Index/VERTICES, :edges Index/EDGES)] (.getIndex *db* (name kname) class)))

(defactive *indices* "" (.getIndices *db*))

(defn drop-index! "" [kname] (.dropIndex *db* (name kname)))

(defn index-class "" [i] (.getIndexClass i))

(defn index-name "" [i] (.getIndexName i))

(defn index-type
  "Returns whether the given index is :automatic or :manual."
  [i] (get {Index$Type/AUTOMATIC :automatic, Index$Type/MANUAL :manual} (.getIndexType i)))

(defn iget "Gets an element from an index." [index key val] (.get index (name key) val))

(defn iput "Puts an element from an index." [index key val element] (.put index key val element))

(defn iremove "Removes an element from an index." [index key val element] (.remove index key val element))

(defn search-graph
  "Searches the Graph for a element of the given type (Vertex or Edge) with the given key-val combination."
  [type key val] (-> (get-index type (case type :vertices Vertex, :edges Edge)) (iget (name key) val)))

; Read-Only
(defn as-read-only
"When called with no arguments, this fn sets *db* to be a read-only version of itself.
When called with one argument (Graph, Vertex, Edge or Index), it returns a read only version of it."
  ([] (set-db! (ReadOnlyGraph. *db*)))
  ([item] (case (class item)
            Graph (ReadOnlyGraph. item)
            Vertex (ReadOnlyVertex. item)
            Edge (ReadOnlyEdge. item)
            Index (ReadOnlyIndex. item))))

; GraphML
(defn migrate-graph! "" [g1 g2] (GraphMigrator/migrateGraph g1 g2))

(defn read-graph! "Reads GraphML formatted data into the current *db*"
  ([input-stream] (GraphMLReader/inputGraph *db* input-stream))
  ([input-stream buffer-size vertex-id edge-id edge-label] (GraphMLReader/inputGraph *db* input-stream buffer-size vertex-id edge-id edge-label)))

(defn write-graph! "Writes GraphML formatted data from the current *db*"
  ([out-stream] (GraphMLWriter/outputGraph *db* out-stream))
  ([out-stream vertex-key-types edge-key-types] (GraphMLWriter/outputGraph *db* out-stream vertex-key-types edge-key-types)))
