(ns clojureblocks.generator.function
  (:require ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-defn-block
  "Generates the code for a defn block: `(defn fn-name [args] body)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        function-arguments (generator/generate-statement-code block "function_arguments")
        function-body (generator/generate-statement-code block "function_body")]
    (str "(defn " function-name " [" function-arguments "] " function-body ")")))

(defn generate-fn-call-block
  "Generates the code for a function call block: `(function arg1 arg2 ...)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        function-arguments (generator/generate-statement-code block "function_arguments")]
    (str "(" function-name
         (when-not (empty? function-arguments)
           (str " " function-arguments))
         ")")))