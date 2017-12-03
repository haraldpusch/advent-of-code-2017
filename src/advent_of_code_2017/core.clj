(ns advent-of-code-2017.core
  (:require [advent-of-code-2017.day0]
            [advent-of-code-2017.day1]
            [advent-of-code-2017.day2])
  (:gen-class))

(defn -main
  "Solve all the puzzles"
  [& args]
  (advent-of-code-2017.day0/solve)
  (advent-of-code-2017.day1/solve)
  (advent-of-code-2017.day2/solve))
