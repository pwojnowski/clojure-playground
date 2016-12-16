(ns poligon.patterns.composed-function
  (:import (java.util Collections Scanner)))

;;; Clojure part for http://farenda.com/patterns/composed-method-pattern

;; Before Composed Function:
(defn sum-best-before
  [data size how-many]
  (let [scanner (Scanner. data)]
    (->> (for [i (range 0 size)] (.nextInt scanner))
         (sort (Collections/reverseOrder))
         (take how-many)
         (apply +))))

;; After Composed Function:
(defn- load-scores [data size]
  (let [scanner (Scanner. data)]
    (for [i (range 0 size)] (.nextInt scanner))))

(defn- sort-desc [scores]
  (sort (Collections/reverseOrder) scores))

(defn- sum-biggest [how-many scores]
  (apply + (take how-many scores)))

(defn sum-best-after
  [data size how-many]
  (->> (load-scores data size)
       sort-desc
       (sum-biggest how-many)))
