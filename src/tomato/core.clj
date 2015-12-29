(ns tomato.core
  (:gen-class))

(use '[clojure.java.shell :only [sh]])
(use 'clj-time.core)
(use 'seesaw.core)
(use 'clojure.repl)
(native!)



(defn pom-done[]
  (sh "notify-send" (clojure.string/join
                     ["--icon=" (System/getProperty "user.dir") "/resources/tomato.png"])
      "Buzz!")
  )

(defn tomato? [tomato] (before? (now) tomato)
  )

(defn wait[]
  (def tomato (plus (now) (minutes 25)) )
  (while (tomato? tomato)
    (do (Thread/sleep 1000)))
  (pom-done)
  )

(defn -main
  [& args]
  (def f (frame :title "Tomato Timer"
                :width 350
                :height 200))
  (-> f show!)
  (def c
    (grid-panel
     :hgap 10
     :vgap 10
     :columns 1
     :items [(label
              :text "Click to Begin"
              :icon (clojure.string/join ["file://"
                                          (System/getProperty "user.dir")
                                          "/resources/tomato.png"])
              :listen [:mouse-clicked (fn [e] (wait))])
             ]))
  (config! f :content c))
