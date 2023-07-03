(ns clojureblocks.core
  (:require [clojure.string :as string]
            [clojureblocks.blockly-wrapper :as blockly-wrapper]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.hof-inspection :as hof-inspection]
            [clojureblocks.import-export :as import-export]
            [clojureblocks.modal-preview :as modal-preview]
            [clojureblocks.serialization :as serialization]
            [zprint.core :as zp]))


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
                     :print-length 12
                     :dark-theme false}))

(def blockly-options
  {:move {:scrollbars {:horizontal true
                       :vertical true}
          :drag true}
   :comments true
   :trashcan true
   :disable false
   :zoom {:controls true
          :wheel false
          :maxScale 3
          :minScale 0.1}})

(defn show-code
  "Displays code in output-div"
  [code]
  (let [formatted-code (zp/zprint-file-str code "clojureblocks" {:parse-string-all? true})]
    (println code)
    (println formatted-code)
    (set! (.. @output-div -innerText) formatted-code)))

(defn apply-settings
  [settings-map]
  (set! (.-disabled @button-evaluate) (get settings-map :auto-evaluation))
  (evaluator/set-print-length (get settings-map :print-length))
  (reset! hof-inspection/preview-length (get settings-map :preview-length))

  (if (= true (get settings-map :dark-theme))
    (do
      (set! (.. @theme-switch -checked) true)
      (.. @dialog-settings -classList (add "dialog-dark"))
      (modal-preview/set-dark-theme true)
      (blockly-wrapper/set-dark-theme true))
    (do
      (set! (.. @theme-switch -checked) false)
      (.. @dialog-settings -classList (remove "dialog-dark"))
      (modal-preview/set-dark-theme false)
      (blockly-wrapper/set-dark-theme false))))

(defn update-settings
  "Updates, applies and persists the settings specified in `settings-map`."
  [settings-map]
  (let [settings-to-update (if settings-map settings-map {})
        [_ new] (swap-vals! settings #(merge %1 %2) settings-to-update)]
    (apply-settings new)
    (serialization/save-settings new)))

(defn handle-theme-switch [e]
  (let [switch-checked (.. e -target -checked)]
    (update-settings {:dark-theme switch-checked})))

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
    (update-settings settings-map)
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
   (string/join "\n\n"
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
   (string/join "\n\n"
                (map (fn [result-element]
                       (str (get result-element :expression)
                            " ; => "
                            (if (get result-element :error)
                              (get result-element :error)
                              (get result-element :result))))
                     (evaluator/split-and-evaluate @blockly-wrapper/generated-code)))))

(defn code-update-handler
  [code]
  (if (get @settings :auto-evaluation)
    (evaluate-code-and-display code)
    (show-code (string/join "\n\n" (string/split-lines code)))))

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

  (reset! button-settings-dialog-close (.getElementById js/document "button-dialog-settings-close"))
  (.addEventListener @button-settings-dialog-close "click" handle-settings-dialog)

  (reset! theme-switch (.getElementById js/document "checkbox-dark-theme"))
  (.addEventListener @theme-switch "change" handle-theme-switch))

(defn load-settings
  []
  (let [settings-map (serialization/load-settings)]
    (update-settings settings-map)))

(defn clojureblocks-init
  "Main entrypoint. Initializes the application."
  []
  (blockly-wrapper/init-workspace blockly-options code-update-handler)
  (register-io)
  (load-settings))

(defn ^:dev/after-load reload
  "Reloads the entire page."
  []
  (.. js/window -location reload))