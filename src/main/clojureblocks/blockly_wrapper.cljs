(ns clojureblocks.blockly-wrapper
  (:require ["@blockly/theme-dark" :default DarkTheme]
            ["blockly" :as blockly]
            [clojureblocks.blocks.all :as blocks]
            [clojureblocks.generator :as generator]
            [clojureblocks.contextmenu :as contextmenu]
            [clojureblocks.modal-view :as modal-view]
            [clojureblocks.serialization :as serialization]
            [clojureblocks.toolbox :as toolbox]))

(def workspace (atom nil))
(def blockly-div-id "blockly-div")
(def blockly-area-id "blockly-area")
(def generated-code (atom ""))

(def themes {:light (.. blockly -Themes -Classic)
             :dark DarkTheme})

(defn resize-handler
  "Resizes the `blockly-div` to fit into `blockly-area`."
  []
  (let [blockly-area (.getElementById js/document blockly-area-id)
        blockly-div (.getElementById js/document blockly-div-id)]
    (loop [element blockly-area
           x 0
           y 0]
      (if element
        (recur (.-offsetParent element)
               (+ x (.-offsetLeft element))
               (+ y (.-offsetTop element)))
        (do
          (set! (.-left (.-style blockly-div)) (str x "px"))
          (set! (.-top (.-style blockly-div)) (str y "px"))
          (set! (.-width (.-style blockly-div)) (str (.-offsetWidth blockly-area) "px"))
          (set! (.-height (.-style blockly-div)) (str (.-offsetHeight blockly-area) "px"))
          (.svgResize blockly @workspace))))))

(defn generate-code-and-display [display-fn]
  (let [code (.workspaceToCode
              generator/clojure-generator
              @workspace)]
    (reset! generated-code code)
    (display-fn code)))

(defn blockly-change-handler [output-function]
  (generate-code-and-display output-function)
  (serialization/save-workspace @workspace))

(defn load-workspace [data]
  (when data
    (.. blockly -serialization -workspaces
        (load data @workspace false))))

(defn define-blocks
  "Injects `blocks` into blockly."
  [blocks]
  (.defineBlocksWithJsonArray blockly (clj->js  blocks)))

(defn reset-workspace
  "Resets the blockly workspace."
  [options persist]
  (let [blockly-div (.getElementById js/document blockly-div-id)]
    (.replaceChildren blockly-div)
    (reset! workspace
            (.inject blockly "blockly-div" (clj->js (merge {:toolbox (toolbox/generate-toolbox)} options))))
    (when persist
      (serialization/save-workspace @workspace))))

(defn init-workspace
  [options output-function]
  (define-blocks blocks/all-blocks)
  (reset-workspace options false)
  (load-workspace (serialization/load-workspace))
  (.addEventListener js/window "resize" resize-handler false)
  (resize-handler)
  (. ^js/Object @workspace addChangeListener (fn [] (blockly-change-handler output-function)))
  (modal-view/init @workspace)
  (contextmenu/register-contextmenu))

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

(defn export-workspace []
  (serialization/serialize @workspace))