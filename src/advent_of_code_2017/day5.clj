(ns advent-of-code-2017.day5
  (:require [clojure.string :as strs]))

(defn read-input [uri]
  (let [s (strs/trim (slurp uri))
        lines (strs/split-lines s)]
    (vec
      (map
        #(Integer/valueOf (str %))
        lines))))

(defn create-step-fn [increment-fn]
  (fn [col pos iteration]
    (let [v (nth col pos)
          col (assoc col pos (increment-fn v))
          pos (+ pos v)]
      (if (>= pos (count col))
        (inc iteration)
        (recur col pos (inc iteration))))))

(defn solve-puzzle-1 []
  (let [col (read-input "resources/day5.txt")]
    ((create-step-fn inc) col 0 0)))

(defn solve-puzzle-2 []
  (let [col (read-input "resources/day5.txt")]
    ((create-step-fn #(if (> % 2) (dec %) (inc %))) col 0 0)))

(defn solve []
  (println (format "Day 5 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))
