(ns poligon.transients)

;; Basic flow: immutable -> transient -> mutation -> persistent!
(defn transient-flow [v]
  (let [tv (transient v)]
    (-> tv
        ;; mutate with bang! functions
        persistent!)))

;; Some types just cannot be transient:
;; -> ClassCastException clojure.lang.LongRange cannot be cast to clojure.lang.IEditableCollection
(defn transient-list [l]
  (persistent! (transient l)))

;; conj! - add to transient collection:
(defn conj-inline [coll val]
  (let [tv (transient coll)]
    (persistent! (conj! tv val))))

;; assoc! - add/change mapping:
(defn times [v n]
  (loop [tv (transient v) i (dec (count v))]
    (if (<= 0 i)
      (recur (assoc! tv i (* n (get tv i))) (dec i))
      (persistent! tv))))

;; dissoc! - remove mapping:
(defn remove-key [m key]
  (-> m transient (dissoc! key) persistent!))

;; pop! - remove last from a vector:
(defn remove-last [v]
  (-> v transient pop! persistent!))

;; disj! - remove element from set
(defn remove-from-set [s key]
  (-> s transient (disj! key) persistent!))
