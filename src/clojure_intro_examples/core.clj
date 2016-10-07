(ns clojure-intro-examples.core
  (:require [clojure.string :as s]
            [clojure-intro-examples.decrementor :as d]
            [clojure-intro-examples.simple-functions :as sf]
            [clojure-intro-examples.macros :as m]
            [clojure-intro-examples.records :as r])
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
  ; (listify) ;= '(1 2 3 4)
  ; (vectorize) ;= [1 2 3 4]
  ; (mapify) ;= {"name" "Alexander", "class" "Intro to Clojure"}
  ; (setify) ;= #{1 2 3}
  ; (list->vec '(1 2 3)) ;= [1 2 3]
  ; (vec->set [1 2 1 2 3]) ;= #{1 2 3}
  ; (multiply-100 2) ;= 200
  ; (square 4) ;= 16
  ; (cube 3) ;= 27
  ; (add-10 40) ;= 50
  ; (divide-by-2 1) ;= 1/2
  ; (double (divide-by-2 1)) ;= 0.5
  ; (name-from-map-by-key {:name "intro to clojure" :topic "clojure"}) ;= "intro to clojure"
  ; (name-from-map-by-key-get {:name "intro to clojure" :topic "clojure"}) ;= "intro to clojure"
  ; (name-from-nested-map-by-key {:person {:name "alexander" :topic "clojure"}}) ;= "alexander"
  ; (name-from-nested-map-by-key-composed {:person {:name "alexander" :topic "clojure"}}) ;= "alexander"
  ; (even-number? 2) ;= true
  ; (even-number? 3) ;= false
  ; (named-alex? {:name "Alex"}) ;= true
  ; (named-alex? {:name "Clojure"}) ;= false
  ; (named-alex? {:title "Clojure"}) ;= false
  ; (guess-number 7 5) ;= 7
  ; (loop [n 5]
  ;   (if (<= n 0)
  ;     (d/decrement-count)
  ;     (recur (d/dec-counter n))))
  (println "Uncomment one of the functions to see it in action"))
