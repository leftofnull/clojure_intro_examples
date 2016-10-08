(ns clojure-intro-examples.core
  (:require [clojure.string :as s]
            [clojure-intro-examples.decrementor :as d]
            [clojure-intro-examples.simple-functions :as sf]
            [clojure-intro-examples.macros :as m]
            [clojure-intro-examples.records :as r]
            [clojure-intro-examples.async :as a]
            [clojure-intro-examples.peg-game.core :as peg])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ; (d/dec-1 2) ;= 1
  ; (d/dec-5 10) ;= 5
  ; (d/dec-10 15) ;= 5
  ; (m/unless false (println "will print") (println "won't print")) ;= "will print"
  ; (m/unless true (println "will print") (println "won't print")) ;= "won't print"
  ; (m/henceforth true (println "will print")) ;= "will print"
  ; (m/henceforth false (println "will print")) ;= "henceforth cannot forth hence"
  ; (:first-name (->r/student "alex" "kahoun" "intro to clojure")) ;= "alex"
  ; (:first-name (r/new-student "intro to clojure" "alex kahoun")) ;= "alex"
  ; (string-concat "class") ;="Hello class from the string concatenator."
  ; (sf/listify) ;= '(1 2 3 4)
  ; (sf/vectorize) ;= [1 2 3 4]
  ; (sf/mapify) ;= {"name" "Alexander", "class" "Intro to Clojure"}
  ; (sf/setify) ;= #{1 2 3}
  ; (sf/list->vec '(1 2 3)) ;= [1 2 3]
  ; (sf/vec->set [1 2 1 2 3]) ;= #{1 2 3}
  ; (sf/multiply-100 2) ;= 200
  ; (sf/square 4) ;= 16
  ; (sf/cube 3) ;= 27
  ; (sf/add-10 40) ;= 50
  ; (sf/divide-by-2 1) ;= 1/2
  ; (sf/double (divide-by-2 1)) ;= 0.5
  ; (sf/name-from-map-by-key {:name "intro to clojure" :topic "clojure"}) ;= "intro to clojure"
  ; (sf/name-from-map-by-key-get {:name "intro to clojure" :topic "clojure"}) ;= "intro to clojure"
  ; (sf/name-from-nested-map-by-key {:person {:name "alexander" :topic "clojure"}}) ;= "alexander"
  ; (sf/name-from-nested-map-by-key-composed {:person {:name "alexander" :topic "clojure"}}) ;= "alexander"
  ; (sf/even-number? 2) ;= true
  ; (sf/even-number? 3) ;= false
  ; (sf/named-alex? {:name "Alex"}) ;= true
  ; (sf/named-alex? {:name "Clojure"}) ;= false
  ; (sf/named-alex? {:title "Clojure"}) ;= false
  ; (sf/guess-number 7 5) ;= 7
  ; (loop [n 5]
  ;   (if (<= n 0)
  ;     (d/decrement-count)
  ;     (recur (d/dec-counter n))))
  ; (a/soda-machine-consumer))
  ; (a/future-example))
  ; (a/promise-example))
  ; (peg/-main))
  (println "Uncomment one of the functions to see it in action"))
