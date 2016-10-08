(defproject clojure-intro-examples "0.1.0-SNAPSHOT"
  :description "Desert Code Camp 2016 - Intro to Clojure"
  :url "https://github.com/leftofnull/clojure_intro_examples"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.2.391"]]
  :main ^:skip-aot clojure-intro-examples.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
