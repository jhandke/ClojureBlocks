(ns clojureblocks.core
  (:require [clojure.string :as string]
            [clojureblocks.blockly-wrapper :as blockly-wrapper]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.import-export :as import-export]
            [clojureblocks.serialization :as serialization]
            [clojureblocks.hof-inspection :as inspection]
            [zprint.core :as zprint]))


(def output-div (atom nil))
(def reset-button (atom nil))
(def button-evaluate (atom nil))
(def export-button (atom nil))
(def import-button (atom nil))
(def theme-switch (atom nil))
(def preview-number-input (atom nil))

(def blockly-options
  {:theme (blockly-wrapper/get-default-theme)
   :move {:scrollbars {:horizontal true
                       :vertical true}
          :drag true
          :wheel false}})

(defn format-code
  [code]
  (zprint/zprint-file-str (string/join "\n\n" (string/split-lines code)) "" {:style :rod
                                                                             :width 40}))

(defn show-code
  "Displays code in output-div"
  [code]
  (let [formatted-code (format-code code)]
    (println formatted-code)
    (set! (.. @output-div -innerText) formatted-code)))

(defn handle-theme-switch [e]
  (if (.. e -target -checked)
    (blockly-wrapper/set-theme :dark)
    (blockly-wrapper/set-theme :light)))

(defn handle-preview-number-change [event]
  (let [new-value (.. event -target -value)]
    (swap!
     inspection/number-previews
     (fn [old new] (if (integer? new) new old))
     new-value)))

(defn reset []
  (blockly-wrapper/reset-blocks true)
  (evaluator/reset-evaluation-context)
  (show-code ""))

(defn download-workspace []
  (let [data (blockly-wrapper/export-workspace)]
    (import-export/download-data data "workspace.json" "text/json")))

(defn upload-workspace []
  (import-export/file-input (fn [file] (blockly-wrapper/load-workspace (.parse js/JSON file)))))

(defn evaluate-code-and-display [] 
  (show-code
   (string/join "\n"
                (map (fn [result-element]
                       (str (get result-element :expression)
                            " ;; => "
                            (if (get result-element :error)
                              (get result-element :error)
                              (get result-element :result))))
                     (evaluator/split-and-evaluate @blockly-wrapper/generated-code)))))

(defn register-io []
  (reset! output-div (.getElementById js/document "output"))

  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "click" evaluate-code-and-display)

  (reset! reset-button (.getElementById js/document "button-reset"))
  (.addEventListener @reset-button "click" reset)

  (reset! import-button (.getElementById js/document "button-import"))
  (.addEventListener @import-button "click" upload-workspace)

  (reset! export-button (.getElementById js/document "button-export"))
  (.addEventListener @export-button "click" download-workspace)
  
  (reset! preview-number-input (.getElementById js/document "num-preview-input"))
  (.addEventListener @preview-number-input "change" handle-preview-number-change))

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