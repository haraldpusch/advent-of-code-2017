(ns advent-of-code-2017.day6
  (require [clojure.string :refer [trim] :as strs]))

(defn get-input []
  (vec
    (map #(Integer/valueOf %)
         (-> (slurp "resources/day6.txt")
             trim
             (strs/split #"\s+")
             ))))

(defn redistribute [c l v i]
  (let [c (assoc c i (inc (nth c i)))
        v (dec v)
        i (mod (inc i) l)]
    (if (= v 0)
      c
      (recur c l v i))))

(defn step [input-col]
  (let [c input-col
        l (count c)
        m (apply max c)
        i (.indexOf c m)
        c (assoc c i 0)
        i (mod (inc i) l)]
    (redistribute c l m i)))

(defn solve-puzzle-1 []
  (loop [input-col (get-input)
         results [input-col]]
    (let [r (step input-col)]
      (if (some #(= % r) results)
        (count results)
        (recur r (conj results r))))))

(defn solve-puzzle-2 []
  (loop [input-col (get-input)
         results [input-col]]
    (let [r (step input-col)]
      (if (some #(= % r) results)
        (- (count results) (.indexOf results r))
        (recur r (conj results r))))))

(defn solve []
  (println (format "Day 6 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))

(solve)
