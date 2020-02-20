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
    (is (= sorted-data (insertion-sort unsorted-data))))
  (testing "Apply insertion sort on part of a vector"
    (is (= [] (insertion-sort [])))
    (is (= [1 2 3] (insertion-sort [1 2 3] 0 2)))
    (is (= [1 2 3 4 5] (insertion-sort [5 3 2 1 4] 0 4)))
    (is (= [5 2 3 1 4] (insertion-sort [5 3 2 1 4] 1 2)))))

(deftest merge-sort-test
  (testing "Simple merge sort."
    (is (= [] (merge-sort [])))
    (is (= [1 2 3] (merge-sort [1 2 3])))
    (is (= [1 2 3 4 5] (merge-sort [5 2 3 4 1])))
    (is (= sorted-data (merge-sort unsorted-data)))))
