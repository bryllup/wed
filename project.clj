(defproject wed "1.0.0-alpha1+jervefonni"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [reagent "0.6.0"]
                 [re-frame "0.8.0"]
                 [org.clojure/core.async "0.2.395"]
                 [re-com "1.0.0"]
                 [secretary "1.2.3"]
                 [garden "1.3.2"]
                 [ns-tracker "0.3.0"]
                 [compojure "1.5.1"]
                 [yogthos/config "0.8"]
                 [ring "1.5.0"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-garden "0.2.8"]]

  :ring {:handler wed.handler/handler
         :uberwar-name "wed.war"}

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :server-port 8080
             :ring-handler wed.handler/dev-handler
             :open-file-command "open-with-emacs"}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   wed.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]
                   [figwheel-sidecar "0.5.8"]
                   [com.cemerick/piggieback "0.2.1"]]

    :plugins      [[lein-figwheel "0.5.8"]
                   [jonase/eastwood "0.2.3"]
                   [lein-doo "0.1.7"]
                   [cider/cider-nrepl "0.13.0"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "wed.core/mount-root"}
     :compiler     {:main                 wed.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            wed.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          wed.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]}

  :main wed.server

  :aot [wed.server]

  :uberjar-name "wed.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["garden" "once"] "compile"])
