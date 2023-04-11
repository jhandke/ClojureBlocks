(ns clojureblocks.helper.resize
  (:require ["blockly" :as blockly]))

(defn resize-handler [blockly-area blockly-div workspace]
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