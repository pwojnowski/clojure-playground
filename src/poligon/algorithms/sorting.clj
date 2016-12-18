(ns poligon.algorithms.sorting)

(defn vec-swap!
  "Swap items at positions x and y in given transient vector."
  [v x y]
  (let [tmp (get v x)]
    (assoc! (assoc! v x (get v y)) y tmp)))

(defn- insert-simple
  [tv right]
  (let [current-value (get tv right)]
    (loop [i right v tv]
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
  "Insert into correct position in transient vector `tv` element from index `right`."
  [tv right]
  (let [current-value (get tv right)]
    (loop [i right v tv]
      (let [left-value (get v (dec i))]
        (if (and (pos? i)
                 (> left-value current-value))
          (recur (dec i) (assoc! v i left-value))
          (assoc! v i current-value))))))

(defn insertion-sort
  "Insertion sort using transients."
  [v]
  (let [size (-> v count dec)]
    (loop [i 1 tv (transient v)]
      (if (<= i size)
        (recur (inc i) (insert tv i))
        (persistent! tv)))))
