(ns advent-of-code-2017.day7
  (require [clojure.string :refer [trim] :as strs]
           [loom.graph :as g]
           [loom.attr :as attr]
           [loom.alg :as alg]
           [loom.io :refer [view]]))

(defn input-lines []
  (strs/split-lines (strs/trim (slurp "resources/day7.txt"))))

(defn weight [s]
  (Integer/valueOf (.substring s 1 (dec (count s)))))

(defn children [c]
  (if (> (count c) 3)
    (map #(strs/replace % #"," "")
         (nthnext c 3))
    []))

(defn read-entry [s]
  (let [c (strs/split s #"\s")
        node-name (first c)
        node-weight (weight (second c))
        childs (children c)]
    {:name node-name :weight node-weight :children childs}))

(defn read-input []
  (map read-entry (input-lines)))

(defn build-graph []
  (let [input (read-input)
        graph (g/digraph (reduce #(assoc %1 (:name %2) (:children %2)) {} input))]
    (reduce #(attr/add-attr %1 (:name %2) :weight (:weight %2)) graph input)))

(def g1
  (let [g (build-graph)]
    (reduce #(set-real-weight %1 %2) g (reverse (alg/topsort g)))))




(defn set-real-weight [g n]
  (let [childs (g/successors g n)]
    (if (nil? childs)
      (attr/add-attr g n :real-weigth (attr/attr g n :weight))
      (attr/add-attr g n :real-weigth
                     (+
                       (attr/attr g n :weight)
                       (reduce #(+ %1
                                   (attr/attr g %2 :real-weight))
                               0
                               childs))))))

(defn is-balanced? [g n]
  (apply = (map #(attr/attr g % :real-weigth) (g/successors g n))))

(map #(attr/attr g1 % :real-weigth) (g/successors g1 "cqmvs"))
(is-balanced? g1 "cqmvs")

(for [n (g/successors g1 "cqmvs")]
  {n (attr/attrs g1 n)})

(apply = [1 1 1 1])


(g/nodes g1)


(view g1)

