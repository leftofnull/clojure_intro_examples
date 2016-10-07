(ns clojure-intro-examples.simple-functions
  (:require [clojure.string :as str]
            [clojure.core.async :as a
                                :refer [>! <! >!! <!! go chan buffer close!
                                        thread alts! alts!! timeout]]))

(defn string-concat
  [data]
  (str "Hello " data " from " "the " "string " "concatenator" "."))

(defn listify [] (list 1 2 3 4))
(defn vectorize [] (vector 1 2 3 4))
(defn mapify [] (hash-map "name" "Alexander" "class" "Intro to Clojure"))
(defn setify [] (hash-set 1 2 3 1 2 3 1 2 3))
(defn list->vec [l] (vec l))
(defn vec->set [v] (set v))

(defn multiply-100 [n] (* n 100))
(defn square [n] (* n n))
(defn cube [n] (* n n n))
(defn add-10 [n] (+ n 10))
(defn divide-by-2 [n] (/ n 2))

(defn name-from-map-by-key [m] (:name m))
(defn name-from-map-by-key-get [m] (get m :name))
(defn name-from-nested-map-by-key [m] (get-in m [:person :name]))
(defn name-from-nested-map-by-key-composed [m] ((comp :name :person) m))
(defn even-number? [n] (= (mod n 2) 0))
(defn named-alex?
  [m]
  (cond
    (contains? m :name) (= (str/lower-case (:name m)) "alex")
    :default false))

(defn guess-number
  [a b]
  (->>
   (* 2)
   (* b)
   (/ 2)
   (double)
   (- a)
   (Math/ceil)
   (int)))
