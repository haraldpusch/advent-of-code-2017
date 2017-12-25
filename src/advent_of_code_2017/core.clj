(ns advent-of-code-2017.core
  (:require [advent-of-code-2017.day0]
            [advent-of-code-2017.day1]
            [advent-of-code-2017.day2]
            [advent-of-code-2017.day3]
            [advent-of-code-2017.day4]
            [advent-of-code-2017.day5]
            [advent-of-code-2017.day8])
  (:gen-class))

(defn -main
  "Solve all the puzzles"
  [& args]
  (advent-of-code-2017.day0/solve)
  (advent-of-code-2017.day1/solve)
  (advent-of-code-2017.day2/solve)
  (advent-of-code-2017.day3/solve)
  (advent-of-code-2017.day4/solve)
  (advent-of-code-2017.day5/solve)
  (advent-of-code-2017.day8/solve))
