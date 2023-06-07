(ns clojureblocks.core
  (:require [clojure.string :as string]
            [clojureblocks.blockly-wrapper :as blockly-wrapper]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.import-export :as import-export]
            [clojureblocks.serialization :as serialization]
            [clojureblocks.code-formatter :as formatter]
            [clojureblocks.hof-inspection :as hof-inspection]))


(def output-div (atom nil))
(def reset-button (atom nil))
(def button-evaluate (atom nil))

(def button-settings (atom nil))
(def dialog-settings (atom nil))
(def button-settings-dialog-close (atom nil))
(def checkbox-auto-evaluate (atom nil))
(def input-print-length (atom nil))
(def input-preview-length (atom nil))

(def export-button (atom nil))
(def import-button (atom nil))
(def theme-switch (atom nil))

(def settings (atom {:auto-evaluation false
                     :preview-length 12
                     :print-length 12}))

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
    (do (blockly-wrapper/set-theme :dark)
        (.. @dialog-settings -classList (add "dialog-dark")))
    (do (blockly-wrapper/set-theme :light)
        (.. @dialog-settings -classList (remove "dialog-dark")))))

(defn apply-settings
  [settings-map]
  (set! (.-disabled @button-evaluate) (get settings-map :auto-evaluation))
  (evaluator/set-print-length (get settings-map :print-length))
  (reset! hof-inspection/preview-length (get settings-map :preview-length)))

(defn open-settings-dialog
  []
  (let [settings @settings]
    (set! (.-checked @checkbox-auto-evaluate) (get settings :auto-evaluation))
    (set! (.-value @input-preview-length) (get settings :preview-length))
    (set! (.-value @input-print-length) (get settings :print-length)))
  (.showModal @dialog-settings))

(defn handle-settings-dialog
  []
  (let [settings-map {:auto-evaluation (. @checkbox-auto-evaluate -checked)
                      :print-length (js/parseInt (. @input-print-length -value))
                      :preview-length (js/parseInt (. @input-preview-length -value))}]
    (reset! settings settings-map)
    (serialization/save-settings settings-map)
    (apply-settings settings-map)
    (.close @dialog-settings)))

(defn reset []
  (blockly-wrapper/reset-blocks true)
  (evaluator/reset-evaluation-context)
  (show-code ""))

(defn download-workspace []
  (let [data (blockly-wrapper/export-workspace)]
    (import-export/download-data data "workspace.json" "text/json")))

(defn upload-workspace []
  (import-export/file-input (fn [file] (blockly-wrapper/load-workspace (.parse js/JSON file)))))

(defn evaluate-code-and-display
  [code] 
  (show-code
   (string/join "\n"
                (map (fn [result-element]
                       (str (get result-element :expression)
                            " ; => "
                            (if (get result-element :error)
                              (get result-element :error)
                              (get result-element :result))))
                     (evaluator/split-and-evaluate code)))))

(defn evaluate-manual
  []
  (show-code
   (string/join "\n"
                (map (fn [result-element]
                       (str (get result-element :expression)
                            " ; => "
                            (if (get result-element :error)
                              (get result-element :error)
                              (get result-element :result))))
                     (evaluator/split-and-evaluate @blockly-wrapper/generated-code)))))

(defn code-update-handler
  [code]
  (let [formatted-code (formatter/format-code code)]
    (if (get @settings :auto-evaluation)
      (evaluate-code-and-display code)
      (show-code formatted-code))))

(defn register-io []
  (reset! output-div (.getElementById js/document "output"))

  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "click" evaluate-manual)

  (reset! reset-button (.getElementById js/document "button-reset"))
  (.addEventListener @reset-button "click" reset)

  (reset! import-button (.getElementById js/document "button-import"))
  (.addEventListener @import-button "click" upload-workspace)

  (reset! export-button (.getElementById js/document "button-export"))
  (.addEventListener @export-button "click" download-workspace)

  (reset! dialog-settings (.getElementById js/document "dialog-settings"))

  (reset! button-settings (.getElementById js/document "button-settings"))
  (.addEventListener @button-settings "click" open-settings-dialog)

  (reset! checkbox-auto-evaluate (.getElementById js/document "checkbox-auto-evaluation"))
  (reset! input-print-length (.getElementById js/document "input-print-length"))
  (reset! input-preview-length (.getElementById js/document "input-preview-length"))

  (reset! button-settings-dialog-close (.getElementById js/document "button-dialog-close"))
  (.addEventListener @button-settings-dialog-close "click" handle-settings-dialog)

  (reset! theme-switch (.getElementById js/document "checkbox-dark-theme"))
  (.addEventListener @theme-switch "change" handle-theme-switch))

(defn load-theme
  []
  (let [saved-theme (serialization/load-theme)]
    (when (= saved-theme :dark)
      (set! (.. @theme-switch -checked) true))
    (.. @dialog-settings -classList (add "dialog-dark"))
    (when (= saved-theme :light)
      (set! (.. @theme-switch -checked) false)
      (.. @dialog-settings -classList (remove "dialog-dark")))))

(defn load-settings 
  []
  (let [settings-map (serialization/load-settings)]
    (when settings-map 
      (reset! settings (serialization/load-settings)))
    (apply-settings @settings)))

(defn clojureblocks-init
  "Main entrypoint. Initializes the application."
  []
  (blockly-wrapper/init-workspace blockly-options code-update-handler)
  (register-io)
  (load-theme)
  (load-settings))

(defn ^:dev/after-load reload
  "Reloads the entire page."
  []
  (.. js/window -location reload))