(ns poligon.algorithms.numbers-test
  (:require [poligon.algorithms.numbers :refer :all]
            [clojure.test :refer :all]))

(deftest should-calculate-gcd-using-euclidean-algorithm
  (is (= 42 (euclid 0 42)))
  (is (= 120 (euclid 1080 1920)))
  (is (= 120 (euclid 1920 1080)))
  (is (= 3 (euclid 21 -9)))
  (is (= 3 (euclid -21 9)))
  (is (= 3 (euclid -6 12 9)))
  (is (= 1 (euclid 5 8 13))))
