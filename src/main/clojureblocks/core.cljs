(ns clojureblocks.core
  (:require [clojure.string :as string]
            [clojureblocks.blockly-wrapper :as blockly-wrapper]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.import-export :as import-export]
            [clojureblocks.serialization :as serialization]))


(def output-div (atom nil))
(def reset-button (atom nil))
(def button-evaluate (atom nil))
(def export-button (atom nil))
(def import-button (atom nil))
(def theme-switch (atom nil))

(def blockly-options
  {:theme (blockly-wrapper/get-default-theme)
   :move {:scrollbars {:horizontal true
                       :vertical true}
          :drag true
          :wheel false}})

(defn show-code
  "Displays code in output-div"
  [code]
  (set! (.. @output-div -innerText) code))

(defn handle-theme-switch [e]
  (if (.. e -target -checked)
    (blockly-wrapper/set-theme :dark)
    (blockly-wrapper/set-theme :light)))

(defn reset-workspace []
  (blockly-wrapper/reset-workspace blockly-options true)
  (evaluator/reset-evaluation-context)
  (show-code ""))

(defn download-workspace []
  (let [data (blockly-wrapper/export-workspace)]
    (import-export/download-data data "workspace.json" "text/json")))

(defn upload-workspace []
  (import-export/file-input (fn [file] (blockly-wrapper/load-workspace (.parse js/JSON file)))))

(defn evaluate-code-and-display []
  (set! (.. @output-div -innerText)
        (string/join "\n"
                     (map (fn [result-element]
                            (println result-element)
                            (str (get result-element :expression)
                                 " => "
                                 (if (get result-element :error)
                                   (get result-element :error)
                                   (get result-element :result))))
                          (evaluator/split-and-evaluate @blockly-wrapper/generated-code)))))

(defn register-io []
  (reset! output-div (.getElementById js/document "output"))

  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "click" evaluate-code-and-display)

  (reset! reset-button (.getElementById js/document "button-reset"))
  (.addEventListener @reset-button "click" reset-workspace)

  (reset! import-button (.getElementById js/document "button-import"))
  (.addEventListener @import-button "click" upload-workspace)

  (reset! export-button (.getElementById js/document "button-export"))
  (.addEventListener @export-button "click" download-workspace))

(reset! theme-switch (.getElementById js/document "checkbox-dark-theme"))
(.addEventListener @theme-switch "change" handle-theme-switch)
(let [saved-theme (serialization/load-theme)]
  (when (= saved-theme :dark)
    (set! (.. @theme-switch -checked) true))
  (when (= saved-theme :light)
    (set! (.. @theme-switch -checked) false)))

(defn clojureblocks-init
  "Main entrypoint. Initializes the application."
  []
  (blockly-wrapper/init-workspace blockly-options show-code)
  (register-io))

(defn ^:dev/after-load reload
  "Reloads the entire page."
  []
  (.. js/window -location reload))