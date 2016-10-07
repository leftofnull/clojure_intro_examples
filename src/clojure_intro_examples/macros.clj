(ns clojure-intro-examples.macros)

(defmacro unless
  [pred a b]
  `(if (not ~pred) ~a ~b))

(defmacro henceforth
  [pred body]
  `(if ~pred ~body (throw (Exception. "henceforth cannot forth hence"))))
