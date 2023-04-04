(ns clojureblocks.core
  (:require ["blockly" :as blockly]
            [clojureblocks.toolbox]
            [clojureblocks.blocks.all]
            [clojureblocks.generator.clojure]))

(def workspace (atom nil))
(def blockly-div (atom nil))
(def blockly-area (atom nil))
(def button-evaluate (atom nil))

(defn generate-code []
  (.workspaceToCode clojureblocks.generator.clojure/clojure-generator @workspace)
  )

(defn change-handler []
  (println "something changed :o"))

(defn resize-handler []
  (println "resizing")
  (let [blockly-area (.getElementById js/document @blockly-area)
        blockly-div (.getElementById js/document @blockly-div)]
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

(defn init-workspace
  [div area toolbox handler]
  (reset! workspace
          (.inject blockly div (clj->js (merge {:toolbox toolbox}))))
  (reset! blockly-div div)
  (reset! blockly-area area)
  (.addEventListener js/window "resize" resize-handler false)
  (resize-handler)
  (. ^js/Object @workspace addChangeListener handler))

(defn define-blocks
  "Define new block types. Blockdefs is a seq of maps that are converted to JSON as per https://developers.google.com/blockly/guides/configure/web/custom-blocks"
  [blocks]
  ;; (. blockly (defineBlocksWithJsonArray blocks))
  (.defineBlocksWithJsonArray blockly blocks))

(def cbtoolbox 
  (clj->js
   {:kind "flyoutToolbox"
    :contents [{:kind "block"
                :type "add_block"}
               {:kind "block"
                :type "list_block"}
               {:kind "block"
                :type "number_block"}]}))


(define-blocks (clj->js (clojureblocks.blocks.all/blocks)))

(defn init []
  (println "i am here :)") 
  (init-workspace
   "blockly-div"
   "blockly-area"
   cbtoolbox
   change-handler)

  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "clicked evaluate" (fn [] (println "click")))
  (println (.getAllBlocks ^js/Object @workspace)))