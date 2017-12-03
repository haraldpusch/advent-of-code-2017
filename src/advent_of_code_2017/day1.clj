(ns advent-of-code-2017.day1
  (require [clojure.string :refer [trim]]))

(defn load-input []
  (trim (slurp "resources/day1.txt")))

(defn extract-pairs [input]
  (partition 2 1
             (map
               #(Integer/valueOf (str %))
               (str input (first input))))) ;; add the first value to the end to simulate wrapping

(defn solve-puzzle-1 []
  (let [input (load-input)]
    (reduce (fn [v [a b]] (if (= a b) (+ v a) v)) 0 (extract-pairs input))))

(defn solve-puzzle-2 []
  (let [input (map #(Integer/valueOf (str %)) (load-input))
        l (/ (count input) 2)
        col1 (take l input)
        col2 (take-last l input)]
    (* 2
       (reduce
         +
         (map
           (fn [a b] (if (= a b) a 0))
           col1
           col2)))))

(defn solve []
  (println (format "Day 1 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))
