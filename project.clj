(defproject poligon "0.1.0-SNAPSHOT"
  :description "Clojure playground"
  :url "http://farenda.com/clojure-tutorial"
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [criterium "0.4.5"]
                 [org.clojure/test.check "0.10.0"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]}})
