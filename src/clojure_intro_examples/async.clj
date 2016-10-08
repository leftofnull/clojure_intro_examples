(ns clojure-intro-examples.async
  (:require [clojure.core.async :as a :refer [>!
                                              >!!
                                              <!!
                                              <!
                                              go
                                              go-loop
                                              chan
                                              close!]]))

(defn mock-api-add-5
  [n]
  (Thread/sleep 5000)
  (+ n 5))

(defn future-example
  []
  (future (println "delayed output of 5 + 5 is " (mock-api-add-5 5)))
  (println "this isn't blocked and will execute immediately"))

(defn promise-example
  []
  (let [p (promise)]
    (future (deliver p (mock-api-add-5 5)))
    (println "This will block until the future is realized as " @p)))

(defn soda-machine
  [soda-cans]
  (let [in (chan)
        out (chan)]
    (go (loop [can soda-cans]
          (if (> can 0)
            (let [input (<! in)]
              (if (= 25 input)
                (do (>! out "cola")
                  (recur (dec can)))
                (do (>! out "shasta")
                  (recur can))))
            (do (close! in)
              (close! out)))))
    [in out]))

(defn soda-machine-consumer
  []
  (let [[in out] (soda-machine 2)]
    (>!! in "pocket lint")
    (println (<!! out))

    (>!! in 25)
    (println (<!! out))

    (>!! in 25)
    (println (<!! out))

    (>!! in 25)
    (<!! out)))
