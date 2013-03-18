(ns org.funcish.clj.ToClojureTest)
(clojure.core/use 'clojure.core)
(use 'clojure.test)
(use 'clojure.test.junit)

(import '(org.funcish.core Functions Mappings Predicates Reducers))
(import '(org.funcish.clj ToClojure FromClojure LikeClojure))

(import '(org.funcish.clj ToClojureTestSupport))

(with-junit-output
  (run-tests 'org.funcish.clj.ToClojureTest))

(deftest to-clojure-fns
  (is (ToClojure/clojureIFn ToClojureTestSupport/PLUS))
  (is (= 6 (reduce (ToClojure/clojureIFn ToClojureTestSupport/PLUS) (list 1 2 3))))
  )
