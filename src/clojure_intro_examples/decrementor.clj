(ns clojure-intro-examples.decrementor)

(def counter (atom 0))
(defn- inc-counter! [] (swap! counter (fn [c] (inc c))))

(defn dec-maker [n] #(- % n))

(def dec-1 (dec-maker 1))
(def dec-5 (dec-maker 5))
(def dec-10 (dec-maker 10))
(defn dec-counter
  [n]
  (inc-counter!)
  (dec n))
(defn decrement-count [] @counter)
