;Version: 0.1.0
;Copyright: Eduardo Emilio Julián Pereyra, 2011
;Email: eduardoejp@gmail.com
;License: EPL 1.0 -> http://www.eclipse.org/legal/epl-v10.html

(ns clj-blueprints
  #^{:author "Eduardo Emilio Julián Pereyra",
     :doc "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 0.7 of the Blueprints API."}
  (:import (com.tinkerpop.blueprints.pgm Graph Element Vertex Edge TransactionalGraph TransactionalGraph$Mode TransactionalGraph$Conclusion IndexableGraph Index Index$Type)
    (com.tinkerpop.blueprints.pgm.impls.readonly ReadOnlyGraph ReadOnlyEdge ReadOnlyVertex ReadOnlyIndex)
    (com.tinkerpop.blueprints.pgm.util.graphml GraphMigrator GraphMLReader GraphMLWriter)))

(def #^{:doc "This dynamic var holds the currently used database."}
  *graph-db* nil)

; Element fns
(defn get-id "" [#^Element elem] (.getId elem))

(defn pget "Gets an element's property (given as a keyword)."
  [#^Element elem prop] (.getProperty elem (name prop)))

(defn passoc! "Assocs an element's property (given as a keyword)."
  ([#^Element elem prop v] (.setProperty elem (name prop) v) elem)
  ([#^Element elem prop v & kvs] (.setProperty elem (name prop) v) (apply passoc! elem kvs)))

(defn pdissoc! "Dissocs an element's property (given as a keyword)."
  [#^Element elem prop] (.removeProperty elem (name prop)) elem)

(defn pkeys "Returns an element's keys as keywords."
  [#^Element elem] (map keyword (.getPropertyKeys elem)))

(defn pvals "Returns an element's vals."
  [#^Element elem] (for [k (pkeys elem)] (pget k)))

(defn as-map "Transforms an element to a Clojure hash-map."
  [#^Element elem] (apply hash-map (flatten (for [k (pkeys elem)] [k (pget elem k)]))))

; Transactions
(def +tx-success+ TransactionalGraph$Conclusion/SUCCESS)
(def +tx-failure+ TransactionalGraph$Conclusion/FAILURE)

(defn set-transaction-mode
  "Sets the transaction mode as either :automatic or :manual."
  [mode]
  (let [mode (case mode :automatic TransactionalGraph$Mode/AUTOMATIC, :manual TransactionalGraph$Mode/MANUAL)]
    (.setTransactionMode #^TransactionalGraph *graph-db* mode)))

(defn get-transaction-mode
  "Returns the transaction mode as either :automatic or :manual."
  []
  (let [mode (.getTransactionMode #^TransactionalGraph *graph-db*)]
    (get {TransactionalGraph$Mode/AUTOMATIC :automatic, TransactionalGraph$Mode/MANUAL :manual} mode)))

(defmacro with-tx "" [& forms]
  `(if (= :manual (get-transaction-mode))
     (do (.startTransaction #^TransactionalGraph *graph-db*)
       (try
         (let [r# (do ~@forms)] (.stopTransaction #^TransactionalGraph *graph-db* +tx-success+) r#)
         (catch Exception ~'e (.stopTransaction #^TransactionalGraph *graph-db* +tx-failure+) (.printStackStrace ~'e))))
     (throw (Exception. "Transaction mode must be MANUAL."))))

; Graph fns
(defmacro with-graph-db "" [graph-db & forms]
  `(with-bindings {#'*graph-db* ~graph-db} ~@forms))

(defn set-db! "Given a Graph instance (like OrientGraph, TinkerGraph, etc), sets it as the global DB."
  [#^Graph graph-db] (def *graph-db* graph-db) graph-db)

(defn close-db! "" [] (.shutdown #^Graph *graph-db*))

(defn clear-db! "" [] (.clear #^Graph *graph-db*))

(defn db-vertices "" [] (.getVertices #^Graph *graph-db*))

(defn db-edges "" [] (.getEdges #^Graph *graph-db*))

(defn load-vertex "" [id] (.getVertex #^Graph *graph-db* id))

(defn load-edge "" [id] (.getEdge #^Graph *graph-db* id))

(defn add-vertex!
  "Adds a vertex to the database. If given a hash-map, sets the properties of the vertex."
  ([id props] (let [v #^Vertex (.addVertex #^Graph *graph-db* id)] (when props (apply passoc! v (interleave (map name (keys props)) (vals props)))) v))
  ([id] (if-not (map? id) (.addVertex #^Graph *graph-db* id) (add-vertex! nil id)))
  ([] (.addVertex #^Graph *graph-db* nil)))

(defn add-edge!
  "Adds an edge between vertex1 and vertex 2 given a vector like [label props-map]. The label must be a keyword and props-map can be nil."
  ([id v1 [label props] v2] (let [e (.addEdge #^Graph *graph-db* id v1 v2 (name label))] (when props (apply passoc! e (interleave (map name (keys props)) (vals props)))) e))
  ([v1 lab-props v2] (add-edge! nil v1 lab-props v2)))

(defn remove! "Removes either a vertex or an edge from the Graph."
  [elem]
  (cond
    (isa? (class elem) Vertex) (.removeVertex #^Graph *graph-db* elem)
    (isa? (class elem) Edge) (.removeEdge #^Graph *graph-db* elem)))

; Vertex fns
(defn get-edges "Gets the edges from a vertex given the direction (:in or :out) and an optional filtering label (as a keyword)."
  ([#^Vertex vertex dir] (case dir :in (.getInEdges vertex), :out (.getOutEdges vertex), :both (concat (.getInEdges vertex) (.getOutEdges vertex))))
  ([#^Vertex vertex dir label] (filter #(= (.getLabel #^Edge %) (name label)) (case dir :in (.getInEdges vertex), :out (.getOutEdges vertex), :both (concat (.getInEdges vertex) (.getOutEdges vertex))))))

; Edge fns.
(defn get-vertex "Gets the :in or :out vertex from an edge."
  [#^Edge edge dir] (case dir :in (.getInVertex edge), :out (.getOutVertex edge)))

(defn get-label "" [#^Edge edge] (.getLabel edge))

(defn get-ends
  "In case you don't want to get the edges that meet a requirement but the vertices at the end of those, use get-ends just like get-edges."
  ([#^Vertex vertex dir label]
   (if (= :both dir)
     (concat (get-ends vertex :in label) (get-ends vertex :out label))
     (map #(get-vertex % (case dir :in :out, :out :in)) (get-edges vertex dir label))))
  ([#^Vertex vertex dir]
   (if (= :both dir)
     (concat (get-ends vertex :in) (get-ends vertex :out))
     (map #(get-vertex % (case dir :in :out, :out :in)) (get-edges vertex dir)))))

(defn get-connection
  "If vertex1 and vertex2 are connected by an edge, it returns that edge."
  [#^Vertex v1, #^Vertex v2]
  (or (some #(when (= v2 (get-vertex % :out)) %) (get-edges v1 :in))
    (some #(when (= v2 (get-vertex % :in)) %) (get-edges v1 :out))))

(defn connected? "Tells whether or not two vertices are connected."
  [#^Vertex v1, #^Vertex v2] (if (get-connection v1 v2) true false))

(defn disconnect "Removes the edge between two vertices." [#^Vertex v1, #^Vertex v2] (remove! (get-connection v1 v2)))

; Indexes
(defn automatic-index! "" [kname class keys]
  (.createAutomaticIndex #^IndexableGraph *graph-db* (name kname) class (->> keys seq (map name) (apply hash-set))))

(defn manual-index! "" [kname class] (.createManualIndex #^IndexableGraph *graph-db* (name kname) class))

(defn create-index! "" [kname class type]
  (case type
    :automatic (.createAutomaticIndex #^IndexableGraph *graph-db* (name kname) class type)
    :manual 
    ))

(defn get-index "" [kname class] (let [class (case class :vertices Index/VERTICES, :edges Index/EDGES)] (.getIndex #^IndexableGraph *graph-db* (name kname) class)))

(defn get-indices "" [] (.getIndices #^IndexableGraph *graph-db*))

(defn drop-index! "" [kname] (.dropIndex #^IndexableGraph *graph-db* (name kname)))

(defn index-class "" [#^Index i] (.getIndexClass i))

(defn index-name "" [#^Index i] (.getIndexName i))

(defn index-type
  "Returns whether the given index is :automatic or :manual."
  [#^Index i] (get {Index$Type/AUTOMATIC :automatic, Index$Type/MANUAL :manual} (.getIndexType i)))

(defn iget "Gets an element from an index." [#^Index index key val] (.get index (name key) val))

(defn iput "Puts an element from an index." [#^Index index key val element] (.put index key val element))

(defn iremove "Removes an element from an index." [#^Index index key val element] (.remove index key val element))

(defn search-graph
  "Searches the Graph for a element of the given type (Vertex or Edge) with the given key-val combination."
  [type key val] (-> (get-index type (case type :vertices Vertex, :edges Edge)) (iget (name key) val)))

; Read-Only
(defn as-read-only
"When called with no arguments, this fn sets *graph-db* to be a read-only version of itself.
When called with one argument (Graph, Vertex, Edge or Index), it returns a read only version of it."
  ([] (set-db! (ReadOnlyGraph. #^Graph *graph-db*)))
  ([item] (case (class item)
            Graph (ReadOnlyGraph. #^Graph item)
            Vertex (ReadOnlyVertex. #^Vertex item)
            Edge (ReadOnlyEdge. #^Edge item)
            Index (ReadOnlyIndex. #^Index item))))

; GraphML
(defn migrate-graph! "" [#^Graph g1, #^Graph g2] (GraphMigrator/migrateGraph g1 g2))

(defn read-graph! "Reads data into the current *graph-db*"
  ([#^java.io.InputStream input-stream] (GraphMLReader/inputGraph #^Graph *graph-db* input-stream))
  ([#^java.io.InputStream input-stream buffer-size vertex-id edge-id edge-label] (GraphMLReader/inputGraph #^Graph *graph-db* input-stream buffer-size vertex-id edge-id edge-label)))

(defn write-graph! "Writes data into the current *graph-db*"
  ([#^java.io.OutputStream out-stream] (GraphMLWriter/outputGraph *graph-db* out-stream))
  ([#^java.io.OutputStream out-stream vertex-key-types edge-key-types] (GraphMLWriter/outputGraph *graph-db* out-stream vertex-key-types edge-key-types)))
