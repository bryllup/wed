(ns wed.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [wed.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 1))))
