(ns clojureblocks.core
  (:require [clojure.string :as string]
            [clojureblocks.blockly :as blockly-wrapper]
            [clojureblocks.evaluator.evaluate :as evaluator]
            [clojureblocks.serialization.serializer :as serialization]))


(def button-evaluate (atom nil))
(def output-div (atom nil))

(def theme-switch (atom nil))

(defn handle-theme-switch [e]
  (if (.. e -target -checked)
    (blockly-wrapper/set-theme :dark)
    (blockly-wrapper/set-theme :light)))

(def blockly-options
  {:theme (blockly-wrapper/get-default-theme)
   :move {:scrollbars {:horizontal true
                       :vertical true}
          :drag true
          :wheel false}})

(defn evaluate-code-and-display []
  (set! (.. @output-div -innerText)
        (string/join "\n"
                     (map (fn [result-element]
                            (println result-element)
                            (str (get result-element :expression)
                                 " => "
                                 (get result-element :result)))
                          (evaluator/split-and-evaluate @blockly-wrapper/generated-code)))))

(defn register-evaluate-button []
  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "click" evaluate-code-and-display))

(defn register-output-div []
  (reset! output-div (.getElementById js/document "output")))

(defn register-theme-switch []
  (reset! theme-switch (.getElementById js/document "checkbox-dark-theme"))
  (.addEventListener @theme-switch "change" handle-theme-switch)
  (let [saved-theme (serialization/load-theme)]
    (when (= saved-theme :dark)
      (set! (.. @theme-switch -checked) true))
    (when (= saved-theme :light)
      (set! (.. @theme-switch -checked) false))))

(defn show-code
  "Displays code in output-div"
  [code]
  (set! (.. @output-div -innerText) code))

(defn clojureblocks-init 
  "Main entrypoint. Initializes the application."
  []
  (blockly-wrapper/init-workspace blockly-options
                                  show-code)

  (register-evaluate-button)
  (register-output-div)
  (register-theme-switch))

(defn ^:dev/after-load reload 
  "Reloads the entire page."
  []
  (.. js/window -location reload))