(ns clojureblocks.core
  (:require ["blockly" :as blockly]
            [clojure.string :as string]
            [clojureblocks.blocks.all :as blocks]
            [clojureblocks.evaluator.evaluate :as evaluator]
            [clojureblocks.generator.clojure]
            [clojureblocks.helper.resize :as resize]
            [clojureblocks.serialization.serializer :as serialization]
            [clojureblocks.toolbox]))

(def workspace (atom nil))
(def blockly-div (atom nil))
(def blockly-area (atom nil))
(def button-evaluate (atom nil))
(def output-div (atom nil))
(def generated-code (atom ""))

(defn generate-code []
  (let [code (.workspaceToCode clojureblocks.generator.clojure/clojure-generator @workspace)]
    (set! (.. @output-div -innerText) code)
    (reset! generated-code code)))

(defn change-handler []
  (generate-code)
  (serialization/save-workspace @workspace))

(defn resize []
  (resize/resize-handler blockly-area blockly-div workspace))

(defn init-workspace
  [div area toolbox handler]
  (reset! workspace
          (.inject blockly div (clj->js  {:toolbox toolbox})))
  (reset! blockly-div div)
  (reset! blockly-area area)
  (.addEventListener js/window "resize" resize false)
  (resize)
  (. ^js/Object @workspace addChangeListener handler))


(defn evaluate-code-and-display []
  (set! (.. @output-div -innerText)
        (string/join "\n" 
                     (map (fn [result-element]
                            (println result-element)
                            (str (get result-element :expression) " => " (get result-element :result)))
                          (evaluator/split-and-evaluate @generated-code)))))

(defn register-evaluate-button []
  (reset! button-evaluate (.getElementById js/document "button-evaluate-all"))
  (.addEventListener @button-evaluate "click" evaluate-code-and-display))

(defn register-output-div []
  (reset! output-div (.getElementById js/document "output")))

(defn init []
  (blocks/define-blocks)
  (init-workspace
   "blockly-div"
   "blockly-area"
   (clojureblocks.toolbox/generate-toolbox)
   change-handler)

  (serialization/load-workspace @workspace)
  (register-evaluate-button)
  (register-output-div))
