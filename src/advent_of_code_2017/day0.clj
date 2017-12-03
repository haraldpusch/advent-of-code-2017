(ns advent-of-code-2017.day0
  (:require [clojure.string :as strings]))

;; this is a warmup problem posted before the start of the actual advent of code - see https://pastebin.com/BMd61PUv

(defn move [{x :x y :y} direction]
  (case direction
    "Down" {:x x :y (- y 1)}
    "Up" {:x x :y (+ y 1)}
    "Left" {:x (- x 1) :y y}
    "Right" {:x (+ x 1) :y y}))

(defn step [{marks :marks pos :currentPosition} command]
  (case command
    ("A" "B") {:marks (conj marks (assoc pos :mark command)) :currentPosition pos}
    "Start" (reduced marks)
    {:marks marks :currentPosition (move pos command)}))

(defn load-marks []
  (reduce
    step
    {:marks [] :currentPosition {:x 0 :y 0}}
    (map strings/trim (strings/split (slurp "https://pastebin.com/raw/wGmzZHeq") #","))))

(defn taxicap-distance
  ([{:keys [x y] :as mark}]
   (assoc mark :dist (+ (Math/abs x) (Math/abs y))))
  ([{x1 :x y1 :y :as mark1} {x2 :x y2 :y :as mark2}]
   {:mark1 mark1 :mark2 mark2 :dist (+ (Math/abs (- x1 x2)) (Math/abs (- y1 y2)))}))

(defn solve-puzzle-1 [marks]
  (:dist (apply max-key :dist (map taxicap-distance marks))))

(defn solve-puzzle-2[marks]
  (:dist
    (apply max-key :dist
           (for [a marks
                 b marks
                 :when (not= (:mark a) (:mark b))]
             (taxicap-distance a b)))))

(defn solve []
  (let [marks (load-marks)]
    (println
      (format
        "Warmup problem - solution 1: %d - solution 2: %d"
        (solve-puzzle-1 marks)
        (solve-puzzle-2 marks)))))
