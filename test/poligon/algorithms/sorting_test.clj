(ns poligon.algorithms.sorting-test
  (:require [poligon.algorithms.sorting :refer :all]
            [clojure.test :refer :all]))

(deftest vswap!-test
  (let [v (transient [1 2 3 4])]
    (vec-swap! v 0 2)
    (is (= (persistent! v) [3 2 1 4]))))

(def unsorted-data (vec (doall (repeatedly 10 #(rand-int 10000)))))

(def sorted-data (sort unsorted-data))

(deftest insertion-sort-test
  (testing "Insertion sort with immutable vectors."
    (is (= sorted-data (insertion-sort-simple unsorted-data))))
  (testing "Insertion sort with transient vectors."
    (is (= [] (insertion-sort [])))
    (is (= [1 2 3] (insertion-sort [1 2 3])))
    (is (= [1 2 3 4 5] (insertion-sort [5 2 3 4 1])))
    (is (= sorted-data (insertion-sort unsorted-data)))))
