(ns org.funcish.clj.FromClojureTest)
(clojure.core/use 'clojure.core)
(use 'clojure.test)
(use 'clojure.test.junit)

(import '(org.funcish.core Functions Mappings Predicates Reducers))
(import '(org.funcish.clj ToClojure FromClojure LikeClojure))


(with-junit-output
  (run-tests 'org.funcish.clj.FromClojureTest))

(deftest from-clojure-fns
  (is (FromClojure/function +))
  (is (= 6 (.reduce (FromClojure/reducator + 0) '(1 2 3))))
  )
