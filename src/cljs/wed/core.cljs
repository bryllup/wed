(ns wed.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [wed.events]
              [wed.subs]
              [wed.routes :as routes]
              [wed.views :as views]
              [wed.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
