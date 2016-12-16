(ns poligon.patterns.composed-function-test
  (:require [poligon.patterns.composed-function :refer :all]
            [clojure.test :refer :all]))

(deftest test-sum-best-before
  (is (= (sum-best-before "1 2 3 4 5" 5 2) 9)))

(deftest test-sum-best-after
  (is (= (sum-best-after "1 2 3 4 5" 5 2) 9)))
