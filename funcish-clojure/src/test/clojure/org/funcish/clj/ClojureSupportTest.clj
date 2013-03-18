(ns org.funcish.clj.ClojureSupportTest)
(clojure.core/use 'clojure.core)
(use 'clojure.test)
(use 'clojure.test.junit)

(import '(org.funcish.core Functions Mappings Predicates Reducers))
(import '(org.funcish.clj ClojureSupport))


(with-junit-output
  (run-tests 'org.funcish.clj.ClojureSupportTest))

(deftest clojure-support-fns
  (is (ClojureSupport/function +))
  (is (= 6 (.reduce (ClojureSupport/reducator + 0) '(1 2 3))))
  )
