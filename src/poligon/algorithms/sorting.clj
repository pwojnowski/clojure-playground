(ns poligon.algorithms.sorting)

(defn vec-swap!
  "Swap items at positions x and y in given transient vector."
  [v x y]
  (let [tmp (get v x)]
    (assoc! (assoc! v x (get v y)) y tmp)))

(defn- insert-simple
  [tv cur-idx]
  (let [current-value (get tv cur-idx)]
    (loop [i cur-idx v tv]
      (let [left-value (get v (dec i))]
        (if (and (pos? i)
                 (> left-value current-value))
          (recur (dec i) (assoc v i left-value))
          (assoc v i current-value))))))

(defn insertion-sort-simple
  [v]
  (let [size (-> v count dec)]
    (loop [i 1 tv v]
      (if (<= i size)
        (recur (inc i) (insert-simple tv i))
        tv))))

(defn- insert
  "Insert element from `cur-idx` into correct position in transient vector `tv`."
  [tv bound cur-idx]
  (let [current-value (get tv cur-idx)]
    (loop [i cur-idx v tv]
      (let [left-value (get v (dec i))]
        (if (and (< bound i)
                 (> left-value current-value))
          (recur (dec i) (assoc! v i left-value))
          (assoc! v i current-value))))))

(defn insertion-sort
  "Insertion sort using transients."
  ([v] (insertion-sort v 0 (-> v count dec)))
  ([v from to]
   (loop [i 1 tv (transient v)]
     (if (<= i to)
       (recur (inc i) (insert tv from i))
       (persistent! tv)))))

(defn merge-sort
  "Merge sort given sequence."
  [v]
  (let [size (count v)]
    (if (> size 1)
      (let [[part-1 part-2] (split-at (/ size 2) v)]
        (loop [merged []
               s1 (merge-sort part-1)
               s2 (merge-sort part-2)]
          (let [f1 (first s1)
                f2 (first s2)]
            (cond
              (empty? s1) (into merged s2)
              (empty? s2) (into merged s1)
              (<= f1 f2) (recur (conj merged f1) (rest s1) s2)
              :else (recur (conj merged f2) s1 (rest s2))))))
      v)))
