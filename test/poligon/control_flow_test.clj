(ns poligon.control-flow-test
  (:require [poligon.control-flow :refer :all]
            [clojure.test :refer :all]))

(deftest test-case-examples
  (testing "case example"
    (is (= "Unknown" (case-example "aoeu")))
    (is (= "English" (case-example "hi")))
    (is (= "Esperanto!" (case-example "saluton")))))

(deftest test-cond-examples
  (testing "cond example"
    (is (= (cond-example "abc") "too short"))
    (is (= (cond-example "very long string") "too long"))
    (is (nil? (cond-example "ok one")))))

(deftest test-condp-examples
  (testing "condp example"
    (is (= (condp-example + 1 2) 3))
    (is (= (condp-example - 1 2) -1))
    (is (= (condp-example * 1 2) "Unknown operator"))))
