(ns poligon.transients-test
  (:require [poligon.transients :refer :all]
            [clojure.test :refer :all]))

(deftest transients-test
  (testing "Basic flow: immutable -> transient -> mutation -> immutable."
    (is (= [1 2 3] (transient-flow [1 2 3]))))
  (testing "conj! - add value"
    (is (= [1 2 3 42] (conj-inline [1 2 3] 42))))
  (testing "accoc! - replace value"
    (is (= [2 4 6] (times [1 2 3] 2))))
  (testing "dissoc! - remove mapping for key"
    (is (= {:name "Przemek", :points 1234}
           (remove-key {:name "Przemek" :points 1234 :level 4} :level))))
  (testing "pop! - remove last element from vector"
    (is (= [1 2] (remove-last [1 2 3]))))
  (testing "disj! - remove from set"
    (is (= #{1 3} (remove-from-set #{1 2 3} 2)))))

(deftest should-throw-exception-for-non-transientable-types
  (try
    (transient-list (range 10))
    (throw (IllegalStateException. "Should fail!"))
    (catch ClassCastException e
      (prn "List cannot be transient: " (.getMessage e)))))
