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

(defn generate-code-and-display 
  "Generates the blockly code and displays it with `display-fn`"
  [display-fn]
  (let [old-code @generated-code
        new-code (.workspaceToCode
                  generator/generator
                  @workspace)]
    (when-not (= old-code new-code)
      (reset! generated-code new-code)
      (display-fn new-code))))

(defn blockly-change-handler 
  "Updates the code output and saves the workspace"
  [output-function]
  (generate-code-and-display output-function)
  (serialization/save-workspace @workspace))

(defn load-workspace 
  "Loads the workspace from `data`. Does nothing when `data` is nil"
  [data]
  (when data
    (.. blockly -serialization -workspaces
        (load data @workspace false))))

(defn define-blocks
  "Injects `blocks` into blockly"
  [blocks]
  (.defineBlocksWithJsonArray blockly (clj->js  blocks)))

(defn inject-workspace
  "Injects the blockly workspace into the DOM"
  [options]
  (let [blockly-div (.getElementById js/document blockly-div-id)]
    (.replaceChildren blockly-div)
    (reset! workspace
            (.inject blockly "blockly-div" (clj->js (merge {:toolbox (toolbox/generate-toolbox)} options))))))
  
(defn reset-blocks 
  "Resets the workspace. Saves before when `persist?` is true."
  [persist?]
  (when persist?
    (serialization/save-workspace @workspace))
  (. @workspace clear))

(defn init-workspace
  "Initializes the blockly workspace with `blockly-options` and sets the code display function to `output-function`"
  [blockly-options output-function]
  (define-blocks blocks/all-blocks)
  (inject-workspace blockly-options)
  (load-workspace (serialization/load-workspace))
  (.addEventListener js/window "resize" resize-handler false)
  (resize-handler)
  (. ^js/Object @workspace addChangeListener (fn [] (blockly-change-handler output-function)))
  (contextmenu/register-contextmenu))

(defn set-dark-theme
  "Applies the dark theme, if `dark?` is true, applies the light theme otherwise"
  [dark?]
  (let [new-theme (if dark? (get themes :dark) (get themes :light))]
    (.. ^js/Object @workspace -themeManager_ (setTheme new-theme))))

(defn export-workspace
  "Serializes the workspace and returns the result"
  []
  (serialization/serialize @workspace))