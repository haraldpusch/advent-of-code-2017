(ns advent-of-code-2017.day4
  (:require [clojure.string :refer [trim] :as strs]))

(defn get-input []
  (map
    #(strs/split % #"\s")
    (->
      (slurp "resources/day4.txt")
      trim
      strs/split-lines)))

(defn compare-to-set [col]
  (= (count col) (count (set col))))

(defn solve-puzzle-1 []
  (count
    (filter
      compare-to-set
      (get-input))))

(defn contains-no-anagram [col]
  (=
    (count col)
    (count
      (for [a col
            b col
            :when (= (sort a) (sort b))]
        true))))

(defn solve-puzzle-2 []
  (count
    (filter
      contains-no-anagram
      (get-input))))

(defn solve []
  (println (format "Day 4 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))
