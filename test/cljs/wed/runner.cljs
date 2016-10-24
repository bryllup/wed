(ns wed.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [wed.core-test]))

(doo-tests 'wed.core-test)
