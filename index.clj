{:namespaces
 ({:source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints/clj-blueprints-api.html",
   :name "clj-blueprints",
   :author "Eduardo Julián",
   :doc
   "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 1.1 of the Blueprints API."}),
 :vars
 ({:name "*db*",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L25",
   :dynamic true,
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/*db*",
   :doc "This dynamic var holds the currently used database.",
   :var-type "var",
   :line 25,
   :file "src/clj_blueprints.clj"}
  {:arglists ([g l]),
   :name "add-listener",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L264",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/add-listener",
   :doc
   "Adds a listener to an EventGraph.\nCan be passed either a GraphChangedListener or a hash-map that is compatible with graph-listener.",
   :var-type "function",
   :line 264,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem]),
   :name "as-map",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L55",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/as-map",
   :doc "Transforms an element to a Clojure hash-map.",
   :var-type "function",
   :line 55,
   :file "src/clj_blueprints.clj"}
  {:arglists ([] [item]),
   :name "as-read-only",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L187",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/as-read-only",
   :doc
   "When called with no arguments, this fn sets *db* to be a read-only version of itself.\nWhen called with one argument (Graph, Vertex, Edge or Index), it returns a read only version of it.",
   :var-type "function",
   :line 187,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "clear!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L85",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/clear!",
   :doc "Clears *db* of all nodes and edges.",
   :var-type "function",
   :line 85,
   :file "src/clj_blueprints.clj"}
  {:arglists ([kname class keys]),
   :name "create-automatic-index!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L149",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/create-automatic-index!",
   :doc "",
   :var-type "function",
   :line 149,
   :file "src/clj_blueprints.clj"}
  {:arglists ([kname class]),
   :name "create-manual-index!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L153",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/create-manual-index!",
   :doc "",
   :var-type "function",
   :line 153,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "current-buffer-size",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L71",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/current-buffer-size",
   :doc "Current Transaction Buffer Size",
   :var-type "function",
   :line 71,
   :file "src/clj_blueprints.clj"}
  {:arglists ([kname]),
   :name "drop-index!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L162",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/drop-index!",
   :doc "",
   :var-type "function",
   :line 162,
   :file "src/clj_blueprints.clj"}
  {:arglists ([graph]),
   :name "event-graph",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L262",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/event-graph",
   :doc "",
   :var-type "function",
   :line 262,
   :file "src/clj_blueprints.clj"}
  {:arglists ([vertex dir] [vertex dir label]),
   :name "get-edges",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L116",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-edges",
   :doc
   "Gets the edges from a vertex given the direction (:in or :out) and an optional filtering label (as a keyword).",
   :var-type "function",
   :line 116,
   :file "src/clj_blueprints.clj"}
  {:arglists ([vertex dir label] [vertex dir]),
   :name "get-ends",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L126",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-ends",
   :doc
   "In case you don't want to get the edges that meet a requirement but the vertices at the end of those, use get-ends just like get-edges.",
   :var-type "function",
   :line 126,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem]),
   :name "get-id",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L29",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-id",
   :doc "",
   :var-type "function",
   :line 29,
   :file "src/clj_blueprints.clj"}
  {:arglists ([kname class]),
   :name "get-index",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L157",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-index",
   :doc "",
   :var-type "function",
   :line 157,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "get-indices",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L159",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-indices",
   :doc "Returns the indices of the graph.",
   :var-type "function",
   :line 159,
   :file "src/clj_blueprints.clj"}
  {:arglists ([edge]),
   :name "get-label",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L124",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-label",
   :doc "",
   :var-type "function",
   :line 124,
   :file "src/clj_blueprints.clj"}
  {:arglists ([v1 v2]),
   :name "get-link",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L137",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-link",
   :doc
   "If vertex1 and vertex2 are connected by an edge, it returns that edge.",
   :var-type "function",
   :line 137,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "get-max-buffer-size",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L69",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-max-buffer-size",
   :doc "Get Max Transaction Buffer Size",
   :var-type "function",
   :line 69,
   :file "src/clj_blueprints.clj"}
  {:arglists ([eg]),
   :name "get-raw-graph",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L269",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-raw-graph",
   :doc "Returns the graph wrapped by an EventGraph.",
   :var-type "function",
   :line 269,
   :file "src/clj_blueprints.clj"}
  {:arglists ([edge dir]),
   :name "get-vertex",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L121",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-vertex",
   :doc "Gets the :in or :out vertex from an edge.",
   :var-type "function",
   :line 121,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "get-vertices",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L87",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/get-vertices",
   :doc "Returns all the vertices.",
   :var-type "function",
   :line 87,
   :file "src/clj_blueprints.clj"}
  {:arglists
   ([{:keys
      [edge-add
       edge-prop-changed
       edge-prop-remove
       edge-remove
       vertex-add
       vertex-prop-changed
       vertex-prop-remove
       vertex-remove
       graph-cleared]}]),
   :name "graph-listener",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L234",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/graph-listener",
   :doc
   "Creates a GraphChangedListener object by being passed a hash-map of functions to be used. All the functions are optional.\nNotation: vx = vertex; e = edge; k = key; v = val\nSignatures:\nedge-add [e]\nedge-prop-changed[e k v] \nedge-prop-remove [e k v]\nedge-remove [e]\nvertex-add [vx]\nvertex-prop-changed [vx k v]\nvertex-prop-remove [vx k v]\nvertex-remove [vx]\ngraph-cleared []",
   :var-type "function",
   :line 234,
   :file "src/clj_blueprints.clj"}
  {:arglists ([index key val]),
   :name "iget",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L174",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/iget",
   :doc "Gets an element from an index.",
   :var-type "function",
   :line 174,
   :file "src/clj_blueprints.clj"}
  {:arglists ([i]),
   :name "index-class",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L164",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/index-class",
   :doc "",
   :var-type "function",
   :line 164,
   :file "src/clj_blueprints.clj"}
  {:arglists ([i]),
   :name "index-name",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L166",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/index-name",
   :doc "",
   :var-type "function",
   :line 166,
   :file "src/clj_blueprints.clj"}
  {:arglists ([i]),
   :name "index-type",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L168",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/index-type",
   :doc "Returns whether the given index is :automatic or :manual.",
   :var-type "function",
   :line 168,
   :file "src/clj_blueprints.clj"}
  {:arglists ([index key val element]),
   :name "iput",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L178",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/iput",
   :doc "Puts an element from an index.",
   :var-type "function",
   :line 178,
   :file "src/clj_blueprints.clj"}
  {:arglists ([index key val element]),
   :name "iremove",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L182",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/iremove",
   :doc "Removes an element from an index.",
   :var-type "function",
   :line 182,
   :file "src/clj_blueprints.clj"}
  {:arglists
   ([id v1 label props v2] [v1 label props v2] [v1 label v2]),
   :name "link!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L103",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/link!",
   :doc
   "Adds an edge between vertex1 and vertex 2 given a vector like [label props-map]. The label must be a keyword and props-map can be nil.",
   :var-type "function",
   :line 103,
   :file "src/clj_blueprints.clj"}
  {:arglists ([v1 v2]),
   :name "linked?",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L143",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/linked?",
   :doc "Tells whether or not two vertices have an edge between them.",
   :var-type "function",
   :line 143,
   :file "src/clj_blueprints.clj"}
  {:arglists ([id]),
   :name "load-edge",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L95",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/load-edge",
   :doc "",
   :var-type "function",
   :line 95,
   :file "src/clj_blueprints.clj"}
  {:arglists ([id]),
   :name "load-vertex",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L93",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/load-vertex",
   :doc "",
   :var-type "function",
   :line 93,
   :file "src/clj_blueprints.clj"}
  {:arglists ([g1 g2]),
   :name "migrate-graph!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L198",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/migrate-graph!",
   :doc "",
   :var-type "function",
   :line 198,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem prop v] [elem prop v & kvs]),
   :name "passoc!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L38",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/passoc!",
   :doc "Assocs an element's property (given as a keyword).",
   :var-type "function",
   :line 38,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem prop]),
   :name "pdissoc!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L46",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/pdissoc!",
   :doc "Dissocs an element's property (given as a keyword).",
   :var-type "function",
   :line 46,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem prop]),
   :name "pget",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L31",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/pget",
   :doc "Retrieves an element's property (given as a keyword).",
   :var-type "function",
   :line 31,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem]),
   :name "pkeys",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L49",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/pkeys",
   :doc "Returns an element's keys as keywords.",
   :var-type "function",
   :line 49,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem]),
   :name "pvals",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L52",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/pvals",
   :doc "Returns an element's vals.",
   :var-type "function",
   :line 52,
   :file "src/clj_blueprints.clj"}
  {:arglists ([input-stream] [input-stream buffer-size]),
   :name "read-graph-json!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L223",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/read-graph-json!",
   :doc "Reads GraphSON formatted data into the current *db*.",
   :var-type "function",
   :line 223,
   :file "src/clj_blueprints.clj"}
  {:arglists
   ([input-stream
     &
     {:keys [buffer-size vertex-id-key edge-id-key edge-label-key]}]),
   :name "read-graph-ml!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L200",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/read-graph-ml!",
   :doc
   "Reads GraphML formatted data into the current *db*.\nAll the keys are optional.",
   :var-type "function",
   :line 200,
   :file "src/clj_blueprints.clj"}
  {:arglists ([elem]),
   :name "remove!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L109",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/remove!",
   :doc "Removes either a vertex or an edge from the Graph.",
   :var-type "function",
   :line 109,
   :file "src/clj_blueprints.clj"}
  {:arglists ([graph-db]),
   :name "set-db!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L78",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/set-db!",
   :doc
   "Given a Graph instance (like OrientGraph, TinkerGraph, etc), sets it as the global DB.",
   :var-type "function",
   :line 78,
   :file "src/clj_blueprints.clj"}
  {:arglists ([buffer-size]),
   :name "set-max-buffer-size",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L70",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/set-max-buffer-size",
   :doc "Set Max Transaction Buffer Size",
   :var-type "function",
   :line 70,
   :file "src/clj_blueprints.clj"}
  {:arglists ([]),
   :name "shutdown!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L83",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/shutdown!",
   :doc "",
   :var-type "function",
   :line 83,
   :file "src/clj_blueprints.clj"}
  {:arglists ([] [dir]),
   :name "tinker-graph",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L273",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/tinker-graph",
   :doc "",
   :var-type "function",
   :line 273,
   :file "src/clj_blueprints.clj"}
  {:arglists ([v1 v2]),
   :name "unlink!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L146",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/unlink!",
   :doc "Removes the edge between two vertices.",
   :var-type "function",
   :line 146,
   :file "src/clj_blueprints.clj"}
  {:arglists ([id props] [id] []),
   :name "vertex!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L97",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/vertex!",
   :doc
   "Adds a vertex to the database. If given a hash-map, sets the properties of the vertex.",
   :var-type "function",
   :line 97,
   :file "src/clj_blueprints.clj"}
  {:arglists ([graph-db & forms]),
   :name "with-db",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L74",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/with-db",
   :doc
   "Evaluates the given forms with the given Graph DB bound to *db*.",
   :var-type "macro",
   :line 74,
   :file "src/clj_blueprints.clj"}
  {:arglists ([& forms]),
   :name "with-tx",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L62",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/with-tx",
   :doc "Evaluates the given forms inside a transaction.",
   :var-type "macro",
   :line 62,
   :file "src/clj_blueprints.clj"}
  {:arglists
   ([out-stream & {:keys [vertex-props edge-props show-types]}]),
   :name "write-graph-json!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L227",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/write-graph-json!",
   :doc
   "Writes GraphSON formatted data from the current *db*.\nAll the keys are optional.",
   :var-type "function",
   :line 227,
   :file "src/clj_blueprints.clj"}
  {:arglists
   ([out-stream & {:keys [vertex-types edge-types normalize]}]),
   :name "write-graph-ml!",
   :namespace "clj-blueprints",
   :source-url
   "http://github.com/eduardoejp/clj-blueprints/blob/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj#L212",
   :raw-source-url
   "http://github.com/eduardoejp/clj-blueprints/raw/2462e73a52a3e8c509bbda69a32a6370774a7c9b/src/clj_blueprints.clj",
   :wiki-url
   "http://eduardoejp.github.com/clj-blueprints//clj-blueprints-api.html#clj-blueprints/write-graph-ml!",
   :doc
   "Writes GraphML formatted data from the current *db*.\nAll the keys are optional.",
   :var-type "function",
   :line 212,
   :file "src/clj_blueprints.clj"})}
