(defproject tomato "0.1.0-SNAPSHOT"
  :description "Simple 25 minute tomato timer"
  :url "https://github.com/garethpdx/tomato"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-time "0.11.0"]
                 [seesaw "1.4.5"]]
  :plugins [[cider/cider-nrepl "0.9.1"]]
  :main ^:skip-aot tomato.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
