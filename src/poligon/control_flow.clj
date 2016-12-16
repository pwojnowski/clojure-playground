(ns poligon.control-flow)

;;; case, cond, codp
(defn case-example
  "clojure.core.case example"
  [word]
  (case word
    "saluton" "Esperanto!"
    "hi" "English"
    "Unknown"))

(defn cond-example
  "clojure.core.cond example"
  [p]
  (cond
    (< (count p) 4) "too short"
    (> (count p) 10) "too long"
    ))

(defn condp-example
  "clojure.core.condp example"
  [op a b]
  (condp = op
    + (+ a b)
    - (- a b)
    "Unknown operator"))
