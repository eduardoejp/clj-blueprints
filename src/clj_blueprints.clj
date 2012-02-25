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

(ns ^{:doc "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 1.1 of the Blueprints API."
      :author "Eduardo Julián"}
  clj-blueprints
  (:import (com.tinkerpop.blueprints.pgm Graph Element Vertex Edge TransactionalGraph TransactionalGraph$Conclusion IndexableGraph Index Index$Type)
    (com.tinkerpop.blueprints.pgm.impls.readonly ReadOnlyGraph ReadOnlyEdge ReadOnlyVertex ReadOnlyIndex)
    (com.tinkerpop.blueprints.pgm.util.graphml GraphMigrator GraphMLReader GraphMLWriter)
    (com.tinkerpop.blueprints.pgm.util.json GraphSONReader GraphSONWriter)
    (com.tinkerpop.blueprints.pgm.impls.event EventGraph)
    (com.tinkerpop.blueprints.pgm.impls.event.listener GraphChangedListener)
    (com.tinkerpop.blueprints.pgm.impls.tg TinkerGraph))
  (:require [clojure.walk :as walk]))

(def ^{:doc "This dynamic var holds the currently used database."}
  ^:dynamic *db* nil)

; Element fns
(defn get-id "" [elem] (.getId elem))

(defn pget "Retrieves an element's property (given as a keyword)."
  [elem prop]
  (let [v (.getProperty elem (name prop))]
    (if (and (string? v) (= (first v) \:))
      (keyword (subs v 1))
      v)))

(defn passoc! "Assocs an element's property (given as a keyword)."
  ([elem prop v]
    (if (keyword? v)
      (recur elem prop (str v))
      (do (.setProperty elem (name prop) (if (keyword? v) (str v) v))
        elem)))
  ([elem prop v & kvs] (passoc! elem prop v) (apply passoc! elem kvs)))

(defn pdissoc! "Dissocs an element's property (given as a keyword)."
  [elem prop] (.removeProperty elem (name prop)) elem)

(defn pkeys "Returns an element's keys as keywords."
  [elem] (map keyword (.getPropertyKeys elem)))

(defn pvals "Returns an element's vals."
  [elem] (for [k (pkeys elem)] (pget k)))

(defn as-map "Transforms an element to a Clojure hash-map."
  [elem] (apply hash-map (reduce concat (for [k (pkeys elem)] [k (pget elem k)]))))

; Transactions
(def +tx-success+ TransactionalGraph$Conclusion/SUCCESS)
(def +tx-failure+ TransactionalGraph$Conclusion/FAILURE)

(defmacro with-tx "Evaluates the given forms inside a transaction."
  [& forms]
  `(do (.startTransaction *db*)
     (try
       (let [r# (do ~@forms)] (.stopTransaction *db* +tx-success+) r#)
       (catch Exception ~'e (.stopTransaction *db* +tx-failure+) (throw ~'e)))))

(defn get-max-buffer-size "Get Max Transaction Buffer Size" [] (.getMaxBufferSize *db*))
(defn set-max-buffer-size "Set Max Transaction Buffer Size" [buffer-size] (.setMaxBufferSize *db* buffer-size))
(defn current-buffer-size "Current Transaction Buffer Size" [] (.getCurrentBufferSize *db*))

; Graph fns
(defmacro with-db "Evaluates the given forms with the given Graph DB bound to *db*."
  [graph-db & forms]
  `(binding [*db* ~graph-db] ~@forms))

(defn set-db! "Given a Graph instance (like OrientGraph, TinkerGraph, etc), sets it as the global DB."
  [graph-db]
  (alter-var-root #'*db* (fn [_] graph-db))
  graph-db)

(defn shutdown! "" [] (.shutdown *db*))

(defn clear! "Clears *db* of all nodes and edges." [] (.clear *db*))

(defn get-vertices "Returns all the vertices."
  [] (.getVertices *db*))

(defn get-edges "Returns all the edges."
  [] (.getEdges *db*))

(defn load-vertex "" [id] (.getVertex *db* id))

(defn load-edge "" [id] (.getEdge *db* id))

(defn vertex!
  "Adds a vertex to the database. If given a hash-map, sets the properties of the vertex."
  ([id props] (let [v (.addVertex *db* id)] (when props (apply passoc! v (interleave (map name (keys props)) (vals props)))) v))
  ([id] (if-not (map? id) (.addVertex *db* id) (vertex! nil id)))
  ([] (.addVertex *db* nil)))

(defn link!
  "Adds an edge between vertex1 and vertex 2 given a vector like [label props-map]. The label must be a keyword and props-map can be nil."
  ([id v1 label props v2] (let [e (.addEdge *db* id v1 v2 (name label))] (when props (apply passoc! e (apply concat (seq props)))) e))
  ([v1 label props v2] (link! nil v1 label props v2))
  ([v1 label v2] (link! nil v1 label nil v2)))

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
(defn create-automatic-index! ""
  [kname class keys]
  (.createAutomaticIndex *db* (name kname) class (->> keys seq (map name) (apply hash-set))))

(defn create-manual-index! ""
  [kname class]
  (.createManualIndex *db* (name kname) class))

(defn get-index "" [kname class] (let [class (case class :vertices Index/VERTICES, :edges Index/EDGES)] (.getIndex *db* (name kname) class)))

(defn get-indices "Returns the indices of the graph."
  [] (.getIndices *db*))

(defn drop-index! "" [kname] (.dropIndex *db* (name kname)))

(defn index-class "" [i] (.getIndexClass i))

(defn index-name "" [i] (.getIndexName i))

(defn index-type
  "Returns whether the given index is :automatic or :manual."
  [i]
  (get {Index$Type/AUTOMATIC :automatic, Index$Type/MANUAL :manual}
       (.getIndexType i)))

(defn iget "Gets an element from an index."
  [index key val]
  (.get index (name key) val))

(defn iput "Puts an element from an index."
  [index key val element]
  (.put index key val element))

(defn iremove "Removes an element from an index."
  [index key val element]
  (.remove index key val element))

; Read-Only
(defn as-read-only
"When called with no arguments, this fn sets *db* to be a read-only version of itself.
When called with one argument (Graph, Vertex, Edge or Index), it returns a read only version of it."
  ([] (set-db! (ReadOnlyGraph. *db*)))
  ([item] (condp = (class item)
            Graph (ReadOnlyGraph. item)
            Vertex (ReadOnlyVertex. item)
            Edge (ReadOnlyEdge. item)
            Index (ReadOnlyIndex. item))))

; GraphML
(defn migrate-graph! "" [g1 g2] (GraphMigrator/migrateGraph g1 g2))

(defn read-graph-ml!
  "Reads GraphML formatted data into the current *db*.
All the keys are optional."
  [input-stream & {:keys [buffer-size vertex-id-key edge-id-key edge-label-key]}]
  (let [gr (doto (GraphMLReader. *db*)
             (.setVertexIdKey (name vertex-id-key))
             (.setEdgeIdKey (name edge-id-key))
             (.setEdgeLabelKey (name edge-label-key)))]
    (if buffer-size
      (.inputGraph gr input-stream buffer-size)
      (.inputGraph gr input-stream))))

(defn write-graph-ml!
  "Writes GraphML formatted data from the current *db*.
All the keys are optional."
  [out-stream & {:keys [vertex-types edge-types normalize]}]
  (doto (GraphMLWriter. *db*)
    (.setVertexKeyTypes (walk/stringify-keys vertex-types))
    (.setEdgeKeyTypes (walk/stringify-keys vertex-types))
    (.setNormalize (boolean normalize))
    (.outputGraph out-stream)))

; GraphSON
(defn read-graph-json! "Reads GraphSON formatted data into the current *db*."
  ([input-stream] (GraphSONReader/inputGraph *db* input-stream))
  ([input-stream buffer-size] (GraphSONReader/inputGraph *db* input-stream buffer-size)))

(defn write-graph-json!
  "Writes GraphSON formatted data from the current *db*.
All the keys are optional."
  [out-stream & {:keys [vertex-props edge-props show-types]}]
  (GraphSONWriter/outputGraph *db* out-stream (map name edge-props) (map name vertex-props) (boolean show-types)))

; Graph Event Listeners
(defn graph-listener
  "Creates a GraphChangedListener object by being passed a hash-map of functions to be used. All the functions are optional.
Notation: vx = vertex; e = edge; k = key; v = val
Signatures:
edge-add [e]
edge-prop-changed[e k v] 
edge-prop-remove [e k v]
edge-remove [e]
vertex-add [vx]
vertex-prop-changed [vx k v]
vertex-prop-remove [vx k v]
vertex-remove [vx]
graph-cleared []"
  [{:keys [edge-add edge-prop-changed edge-prop-remove edge-remove
           vertex-add vertex-prop-changed vertex-prop-remove vertex-remove
           graph-cleared]}]
  (reify GraphChangedListener
    (edgeAdded [_ e] (if edge-add (edge-add e)))
    (edgePropertyChanged [_ e k v] (if edge-prop-changed (edge-prop-changed e k v)))
    (edgePropertyRemoved [_ e k v] (if edge-prop-remove (edge-prop-remove e k v)))
    (edgeRemoved [_ e] (if edge-remove (edge-remove e)))
    (vertexAdded [_ vx] (if vertex-add (vertex-add vx)))
    (vertexPropertyChanged [_ vx k v] (if vertex-prop-changed (vertex-prop-changed vx k v)))
    (vertexPropertyRemoved [_ vx k v] (if vertex-prop-remove (vertex-prop-remove vx k v)))
    (vertexRemoved [_ vx] (if vertex-remove (vertex-remove vx)))
    (graphCleared [_] (if graph-cleared (graph-cleared)))
    ))

(defn event-graph "" [graph] (EventGraph. graph))

(defn add-listener
  "Adds a listener to an EventGraph.
Can be passed either a GraphChangedListener or a hash-map that is compatible with graph-listener."
  [g l] (.addListener g (if (map? l) (graph-listener l) l)))

(defn get-raw-graph "Returns the graph wrapped by an EventGraph."
  [eg] (.getRawGraph eg))

; TinkerGraph
(defn tinker-graph ""
  ([] (TinkerGraph.))
  ([dir] (TinkerGraph. dir)))
