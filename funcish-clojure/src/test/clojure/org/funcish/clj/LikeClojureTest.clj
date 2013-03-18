(ns org.funcish.clj.LikeClojureTest)
(clojure.core/use 'clojure.core)
(use 'clojure.test)
(use 'clojure.test.junit)

(import '(org.funcish.core Functions Mappings Predicates Reducers))
(import '(org.funcish.clj ToClojure FromClojure LikeClojure))


(with-junit-output
  (run-tests 'org.funcish.clj.LikeClojureTest))

(deftest like-clojure-fns
  (is (LikeClojure/reducator (FromClojure/function +)))
  (is (= 6 (.reduce (LikeClojure/reducator (FromClojure/function + 2)) (list 1 2 3))))
  )
