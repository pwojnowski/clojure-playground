(ns poligon.core-test
  (:require [clojure.test :refer :all]
            [poligon.core :refer :all]))

;;; Examples for http://farenda.com/clojure/clojure-for-loop
(deftest test-for-examples
  (testing "Simple iteration over values"
    (is (= (for [i (range 1 6)] i)
           '(1 2 3 4 5))))

  (testing "Iteration over two vars"
    (is (= (for [x (range 1 3) y (range 6 8)] [x y])
           '([1 6] [1 7] [2 6] [2 7]))))

  (testing "With filtering"
    (is (= (for [i (range 1 11) :when (even? i)] i)
           '(2 4 6 8 10))))

  (testing "As long as condition is met"
    (is (= (for [i (range 1 11) :while (<= i 5)] i)
           '(1 2 3 4 5))))

  (testing "With new vars based on iterated value"
    (is (= (for [i (range 1 6) :let [squared (* i i)]]
             [i squared])
           '([1 1] [2 4] [3 9] [4 16] [5 25]))))

  (testing "when then while"
    (is (= (for [i (range 1 100)
                 :when (odd? i) :while (< i 10)]
             i)
           '(1 3 5 7 9))))

  (testing "Combo"
    (is (= (for [i (range 1 100)
                 :while (< i 10)
                 :when (even? i)
                 :let [tripple (* i i i)]]
             tripple)
           '(8 64 216 512)))))
