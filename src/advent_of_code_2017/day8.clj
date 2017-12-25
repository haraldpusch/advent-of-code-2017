(ns advent-of-code-2017.day8
  (:require [clojure.string :as strs]))

;; just defining `(def != not=)` and generally using `(eval (read-string op))` didn't work when using `lein run` for some reason...
(defn get-op [s]
  (if (.equals s "!=") not= (eval (read-string s))))

(defn build-cond [[reg op v]]
  (fn [m]
    "m -> map of registers and their values"
    (let [register-value (get m reg 0)]
      ((get-op op) register-value (Integer. v)))))

(defn convert-line [l]
  (let [l (strs/split l #"\s")]
    {:register (first l)
     :operation (case (second l)
                  "inc" +
                  "dec" -)
     :value (Integer. (nth l 2))
     :condition (build-cond (nthrest l 4))}))

(defn step [registers {:keys [register operation value condition]}]
  (let [v (get registers register 0)
        m (:max registers)
        new-v (if
                (condition registers)
                (operation v value)
                v)]
    (assoc registers register new-v :max (max m new-v))))

(defn solve-puzzle-1 []
  (let [input (strs/split-lines (slurp "resources/day8.txt"))]
    (apply max (vals (dissoc (reduce step {:max 0} (map convert-line input)) :max)))))

(defn solve-puzzle-2 []
  (let [input (strs/split-lines (slurp "resources/day8.txt"))]
    (:max (reduce step {:max 0} (map convert-line input)) :max)))

(defn solve []
  (println (format "Day 8 - solution 1: %d - solution 2: %d" (solve-puzzle-1) (solve-puzzle-2))))
