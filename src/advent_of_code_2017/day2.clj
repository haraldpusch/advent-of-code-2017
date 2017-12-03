(ns advent-of-code-2017.day2
  (:require [clojure.string :as strings]))

(defn convert-line [line]
  (let [line (strings/split line #"\t")]
    (map (fn [v]
           (Integer/valueOf v)) line)))

(defn read-input [uri]
  (let [s (slurp uri)
        lines (filter #(not (strings/blank? %)) (strings/split-lines s))]
    (map
      convert-line
      lines)))

(defn divide-divisable-numbers [c]
  (for [a c
        b c
        :when (and (not= a b) (= (mod a b) 0))]
    (/ a b)))

(defn solve-puzzle-1 []
  (let [lines (read-input "resources/day2.txt")]
    (reduce
      +
      (map
        #(- (apply max %) (apply min %))
        lines))))

(defn solve-puzzle-2 []
  (let [lines (read-input "resources/day2.txt")]
    (reduce
      +
      (flatten
        (map
          divide-divisable-numbers
          lines)))))

(defn solve []
  (println (format "Day 2 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))
