(ns clojure-intro-examples.peg-game.core
  (:gen-class))

(declare successful-move
         prompt-move
         game-over
         query-rows
         user-entered-valid-move
         user-entered-invalid-move
         prompt-rows)

; (def example-board {1 {:pegged true, :connections {6 3, 4 2}},
;                     2 {:pegged true, :connections {9 5, 7 4}},
;                     3 {:pegged true, :connections {10 6, 8 5}},
;                     4 {:pegged true, :connections {13 8, 11 7, 6 5, 1 2}},
;                     5 {:pegged true, :connections {14 9, 12 8}},
;                     6 {:pegged true, :connections {15 10, 13 9, 4 5, 1 3}},
;                     7 {:pegged true, :connections {9 8, 2 4}},
;                     8 {:pegged true, :connections {10 9, 3 5}},
;                     9 {:pegged true, :connections {7 8, 2 5}},
;                     10 {:pegged true, :connections {8 9, 3 6}},
;                     11 {:pegged true, :connections {13 12, 4 7}},
;                     12 {:pegged true, :connections {14 13, 5 8}},
;                     13 {:pegged true, :connections {15 14, 11 12, 6 9, 4 8}},
;                     14 {:pegged true, :connections {12 13, 5 9}},
;                     15 {:pegged true, :connections {13 14, 6 10}},
;                     :rows 5})

(defn tri*
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(defn triangular? [n] (= n (last (take-while #(>= n %) tri))))

(defn row-tri [n] (last (take n tri)))

(defn row-num [n] (inc (count (take-while #(> n %) tri))))

(defn connect
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))

(defn connect-right
  [board max-pos pos]
  (let [neighbor (inc pos)
        destination (inc neighbor)]
    (if-not (or (triangular? neighbor) (triangular? pos))
      (connect board max-pos pos neighbor destination)
      board)))

(defn connect-down-left
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ row pos)
        destination (+ 1 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(defn connect-down-right
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        destination (+ 2 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(defn add-pos
  [board max-pos pos]
  (let [pegged-board (assoc-in board [pos :pegged] true)]
    (reduce (fn [new-board connection-creation-fn]
              (connection-creation-fn new-board max-pos pos))
            pegged-board
            [connect-right connect-down-left connect-down-right])))

(defn new-board
  [rows]
  (let [initial-board {:rows rows}
        max-pos (row-tri rows)]
    (reduce (fn [board pos] (add-pos board max-pos pos))
            initial-board
            (range 1 (inc max-pos)))))

(defn pegged? [board pos] (get-in board [pos :pegged]))

(defn remove-peg [board pos] (assoc-in board [pos :pegged] false))

(defn place-peg [board pos] (assoc-in board [pos :pegged] true))

(defn move-peg [board p1 p2] (place-peg (remove-peg board p1) p2))

(defn valid-moves
  [board pos]
  (into {}
        (filter (fn [[destination jumped]]
                  (and (not (pegged? board destination))
                       (pegged? board jumped)))
                (get-in board [pos :connections]))))

(defn valid-move?
  [board p1 p2]
  (get (valid-moves board p1) p2))

(defn make-move
  [board p1 p2]
  (if-let [jumped (valid-move? board p1 p2)]
    (move-peg (remove-peg board jumped) p1 p2)))

(defn can-move?
  [board]
  (some (comp not-empty (partial valid-moves board))
        (map first (filter #(get (second %) :pegged) board))))

(def alpha-start 97)
(def alpha-end 123)
(def letters (map (comp str char) (range alpha-start alpha-end)))
(def pos-chars 3)
(def ansi-styles {:red "[31m]"
                  :green "[32m"
                  :blue "[34m"
                  :reset "[0m"})

(defn ansi [style] (str \u001b (style ansi-styles)))

(defn colorize [text color] (str (ansi color) text (ansi :reset)))

(defn render-pos
  [board pos]
  (str (nth letters (dec pos))
       (if (get-in board [pos :pegged])
         (colorize "0" :blue)
         (colorize "-" :red))))

(defn row-positions
  [row-num]
  (range (inc (or (row-tri (dec row-num)) 0)) (inc (row-tri row-num))))

(defn row-padding
  [row-num rows]
  (let [pad-length (/ (* (- rows row-num) pos-chars) 2)]
    (apply str (take pad-length (repeat " ")))))

(defn render-row
  [board row-num]
  (str (row-padding row-num (:rows board))
       (clojure.string/join " " (map (partial render-pos board)
                                     (row-positions row-num)))))

(defn print-board
  [board]
  (doseq [row-num (range 1 (inc (:rows board)))]
    (println (render-row board row-num))))

(defn letter->pos [letter] (inc (- (int (first letter)) alpha-start)))

(defn get-input
  ([] (get-input nil))
  ([default]
   (let [input (clojure.string/trim (read-line))]
     (if (empty? input)
       default
       (clojure.string/lower-case input)))))

(defn characters-as-strings [string] (re-seq #"[a-zA-Z]" string))

(defn prompt-move
  [board]
  (println "\nHere's your board:")
  (print-board board)
  (println "Move from where to where? Enter two letters:")
  (let [input (map letter->pos (characters-as-strings (get-input)))]
    (if-let [new-board (make-move board (first input) (second input))]
      (user-entered-valid-move new-board)
      (user-entered-invalid-move board))))

(defn user-entered-invalid-move
  [board]
  (println "\n!!! That was an invalid move >:(\n")
  (prompt-move board))

(defn user-entered-valid-move
  [board]
  (if (can-move? board)
    (prompt-move board)
    (game-over board)))

(defn game-over
  [board]
  (let [remaining-pegs (count (filter :pegged (vals board)))]
    (println "Game over! You had " remaining-pegs " pegs left:")
    (print-board board)
    (println "Play again? y/n [y]")
    (let [input (get-input "y")]
      (if (= "y" input)
        (prompt-rows)
        (do
          (println "Bye!")
          (System/exit 0))))))

(defn prompt-empty-peg
  [board]
  (println "Here's your board:")
  (print-board board)
  (println "Remove which peg? [e]")
  (prompt-move (remove-peg board (letter->pos (get-input "e")))))

(defn prompt-rows
  []
  (println "How many rows? [5]")
  (let [rows (Integer. (get-input 5))
        board (new-board rows)]
    (prompt-empty-peg board)))

(defn -main
  [& args]
  (prompt-rows))
