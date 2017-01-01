(ns poligon.algorithms.numbers)

(defn euclid-for-two
  "Calculate GCD of given two numbers using Euclidean algorithm."
  [a b]
  (if (zero? b)
    (Math/abs a)
    (recur b (rem a b))))

(defn euclid
  "Calculate GCD of given numbers using Euclidean algorithm."
  [a b & other]
  (cond
    (and (zero? b) (nil? other)) (Math/abs a)
    (zero? b) (recur a (first other) (next other))
    :else (recur b (rem a b) other)))
