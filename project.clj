(defproject clj-blueprints "0.1.0"
  :description "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 0.7 of the Blueprints API."
  :url "https://github.com/eduardoejp/clj-blueprints"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.tinkerpop.blueprints/blueprints-core "0.7"]
                 [active-vars "0.1.0"]]
  :dev-dependencies [[org.clojars.rayne/autodoc "0.8.0-SNAPSHOT"]]
  :repositories {"tinkerpop" "http://tinkerpop.com/maven2"}
  :autodoc {:name "clj-blueprints"
            :description "Wrapper for the TinkerPop Blueprints API for Graph DBMSs. It supports version 0.7 of the Blueprints API."
            :copyright "Copyright 2011 Eduardo Julian"
            :web-src-dir "http://github.com/eduardoejp/clj-blueprints/blob/"
            :web-home "http://eduardoejp.github.com/clj-blueprints/"
            :output-path "autodoc"}
	)
