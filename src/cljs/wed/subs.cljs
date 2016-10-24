(ns wed.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 :name)

(re-frame/reg-sub
 :active-panel
 :active-panel)
