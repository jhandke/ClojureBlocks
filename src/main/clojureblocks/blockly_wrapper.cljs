(ns clojureblocks.blockly-wrapper
  (:require ["@blockly/theme-dark" :default DarkTheme]
            ["blockly" :as blockly]
            [clojureblocks.blocks.all :as blocks]
            [clojureblocks.generator.generator :as generator]
            [clojureblocks.contextmenu :as contextmenu]
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
  (let [old-code @generated-code
        new-code (.workspaceToCode
                  generator/generator
                  @workspace)]
    (when-not (= old-code new-code)
      (reset! generated-code new-code)
      (display-fn new-code))))

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

(defn inject-workspace
  "Injects the blockly workspace."
  [options]
  (let [blockly-div (.getElementById js/document blockly-div-id)]
    (.replaceChildren blockly-div)
    (reset! workspace
            (.inject blockly "blockly-div" (clj->js (merge {:toolbox (toolbox/generate-toolbox)} options))))))
  
(defn reset-blocks 
  [persist]
  (when persist
    (serialization/save-workspace @workspace))
  (. @workspace clear))

(defn init-workspace
  [options output-function]
  (define-blocks blocks/all-blocks)
  (inject-workspace options)
  (load-workspace (serialization/load-workspace))
  (.addEventListener js/window "resize" resize-handler false)
  (resize-handler)
  (. ^js/Object @workspace addChangeListener (fn [] (blockly-change-handler output-function)))
  (contextmenu/register-contextmenu))

(defn set-dark-theme
  [dark?]
  (let [new-theme (if dark? (get themes :dark) (get themes :light))]
    (.. ^js/Object @workspace -themeManager_ (setTheme new-theme))))

(defn export-workspace []
  (serialization/serialize @workspace))