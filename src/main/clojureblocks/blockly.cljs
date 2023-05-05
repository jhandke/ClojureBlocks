(ns clojureblocks.blockly
  (:require ["@blockly/theme-dark" :default DarkTheme]
            ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]
            [clojureblocks.helper.contextmenu :as contextmenu]
            [clojureblocks.helper.modal-view :as modal-view]
            [clojureblocks.helper.resize :as resize]
            [clojureblocks.serialization.serializer :as serialization]))

(def workspace (atom nil))
(def blockly-div (atom nil))
(def blockly-area (atom nil))
(def generated-code (atom ""))

(def themes {:light (.. blockly -Themes -Classic)
             :dark DarkTheme})

(defn blockly-resize-handler []
  (resize/resize-handler blockly-area blockly-div workspace))

(defn generate-code-and-display [display]
  (let [code (.workspaceToCode
              generator/clojure-generator
              @workspace)]
    (reset! generated-code code)
    (display code)))

(defn blockly-change-handler [output-function]
  (generate-code-and-display output-function)
  (serialization/save-workspace @workspace))

(defn load-workspace [data]
  (.. blockly -serialization -workspaces
      (load data @workspace false)))

(defn init-workspace
  [toolbox options output-function]
  (reset! workspace
          (.inject blockly "blockly-div" (clj->js  (merge {:toolbox toolbox} options)))) 
  (load-workspace (serialization/load-workspace))
  (reset! blockly-div "blockly-div")
  (reset! blockly-area "blockly-area")
  (.addEventListener js/window "resize" blockly-resize-handler false)
  (blockly-resize-handler)
  (. ^js/Object @workspace addChangeListener (fn [] (blockly-change-handler output-function)))
  (modal-view/init @workspace)
  (contextmenu/register-contextmenu))

(defn define-blocks
  "Injects `blocks` into blockly."
  [blocks]
  (.defineBlocksWithJsonArray blockly (clj->js  blocks)))

(defn get-default-theme
  "Returns default theme if no theme is saved."
  []
  (let [theme (serialization/load-theme)]
    (if-not (nil? theme)
      (get themes theme)
      (get themes :dark))))

(defn set-theme
  "Sets the current theme to `theme`"
  [theme]
  (.. ^js/Object @workspace -themeManager_ (setTheme (themes theme)))
  (serialization/save-theme (str theme)))