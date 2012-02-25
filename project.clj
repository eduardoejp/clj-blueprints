(defproject clj-blueprints "0.1.0"
  :description "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 1.1 of the Blueprints API."
  :url "https://github.com/eduardoejp/clj-blueprints"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :plugins [[lein-autodoc "0.9.0"]]
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [com.tinkerpop.blueprints/blueprints-core "1.1"]]
  :repositories {"tinkerpop" "http://tinkerpop.com/maven2"}
  :autodoc {:name "clj-blueprints"
            :description "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 1.1 of the Blueprints API."
            :copyright "Copyright 2011 Eduardo Julian"
            :web-src-dir "http://github.com/eduardoejp/clj-blueprints/blob/"
            :web-home "http://eduardoejp.github.com/clj-blueprints/"
            :output-path "autodoc"}
	)
