(ns advent-of-code-2017.day3
  (:require [clojure.string :refer [trim]]))

(defn abs [i] (Math/abs i))

(defn go [{:keys [:x :y :direction] :as m}]
  (case direction
    :left  (assoc m :x (dec x))
    :right (assoc m :x (inc x))
    :up    (assoc m :y (inc y))
    :down  (assoc m :y (dec y))))

(defn turn? [{:keys [:x :y]}]
  (let [ax (abs x)
        ay (abs y)]
    (cond
      (and (= ax ay) (> x 0) (< y 0)) false
      (= ax ay) true
      (and (= ax (+ ay 1)) (> x 0) (< y 0)) true
      :else false)))

(defn turn! [{direction :direction :as m}]
  (assoc m :direction
    (case direction
      :left :down
      :down :right
      :right :up
      :up :left)))

(defn step [{:keys [:direction :x :y :index] :as m}]
  (-> m
      (assoc :index (inc index))
      (go)
      (#(if (turn? %) (turn! %) %))))

(defn solve-puzzle-1 [col]
  (let [{x :x y :y} (first (filter
                             #(= 265149 (:index %))
                             col))]
    (+ (abs x) (abs y))))

(defn get-adjacent-positions [{:keys [:x :y] :as m}]
  [{:x (inc x) :y y}
   {:x (dec x) :y y}
   {:x x :y (inc y)}
   {:x x :y (dec y)}
   {:x (inc x) :y (inc y)}
   {:x (inc x) :y (dec y)}
   {:x (dec x) :y (inc y)}
   {:x (dec x) :y (dec y)}])

(defn find-first-value [{:keys [:x :y]} col index]
  (let [v (first (filter #(and (= (:x %) x) (= (:y %) y)) col))]
    (if
      (or (nil? v) (> (:index v) index))
      0
      (:value v))))

(defn calculate-value [{:keys [:x :y :index] :as in} col]
  (let [neighbors (get-adjacent-positions in)]
    (assoc in :value
      (reduce +
              (map
                #(find-first-value % col index)
                neighbors)))))

(defn solve-puzzle-2 [col]
  (let [col (take 60 (iterate step {:x 1 :y 1 :index 3 :direction :left}))
        col (cons {:x 1 :y 0 :index 2 :direction :left :value 1} col)]
    (:value
      (first
        (filter #(> (:value %) 265149)
                (reduce
                  #(conj %1 (calculate-value %2 %1))
                  [{:x 0 :y 0 :index 1 :direction :left :value 1}]
                  col))))))

(defn solve []
  (let [col (iterate step {:x 1 :y 1 :index 3 :direction :left})]
    (println (format "Day 3 - solution 1: %d - solution 2: %d" (solve-puzzle-1 col) (solve-puzzle-2 col)))))
