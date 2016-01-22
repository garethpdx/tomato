(ns tomato.core
  (:gen-class))

(use '[clojure.java.shell :only [sh]])
(use 'clj-time.core)
(use 'seesaw.core)
(use 'clojure.repl)
(native!)

(defn tomato? [tomato] (before? (now) tomato))

(def status (label :text "Idle"  :h-text-position :right))

(defn pom-done[]
  (sh "notify-send" (clojure.string/join
                     ["--icon=" (System/getProperty "user.dir") "/resources/tomato.png"])
      "Buzz!")
  (text! status "Idle")
  )

(defn wait[]
  (text! status "Started")
  (timer (fn [_](text! status "Finish up!")) :initial-delay (* 23 60 1000) :repeats? false)  
  (timer (fn [_](pom-done)) :initial-delay (* 25 60 1000) :repeats? false)
  )

(def c
  (grid-panel
   :hgap 10
   :vgap 10
   :columns 1
   :items [(left-right-split
           (label
            :text "Click to Begin"
            :icon (clojure.string/join ["file://"
                                        (System/getProperty "user.dir")
                                        "/resources/tomato.png"])
            :listen [:mouse-clicked (fn [e] (wait))]
            )
           status
           :divider-size 20)]
   )
  )
(def f (frame :title "Tomato Timer"
              :width 450
              :height 200
              :on-close :exit))
(defn -main
  [& args]
  (-> f show!)
  (config! f :content c)
  )
