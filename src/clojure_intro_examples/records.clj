(ns clojure-intro-examples.records
  (:require [clojure.string :as str]))

(defrecord student [first-name second-name class])

(defn new-student
  [class name]
  (let [name-parts (str/split name " ")
        fname (first name-parts)
        lname (last name-parts)]
    (->student fname lname class)))
