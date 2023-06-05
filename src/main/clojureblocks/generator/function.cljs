(ns clojureblocks.generator.function
  (:require ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-defn-block
  "Generates the code for a defn block: `(defn fn-name [args] body)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        function-arguments (generator/generate-statement-code block "function_arguments")
        function-body (generator/generate-statement-code block "function_body")]
    (generator/expression "defn" function-name (str "[" function-arguments "]") function-body)))

(defn generate-fn-call-block
  "Generates the code for a function call block: `(function arg1 arg2 ...)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        function-arguments (generator/generate-statement-code block "function_arguments")]
    (generator/expression function-name function-arguments)))

(defn generate-fn-block
  [block]
  (let [args (generator/generate-statement-code block "args")
        body (generator/generate-statement-code block "body")]
    (generator/expression "fn" (str "[" args "]") body)))

(defn generate-def-block
  [^blockly/Block block]
  (let [name (.getFieldValue block "symbol_name")
        value (generator/generate-statement-code block "value")]
    (generator/expression "def" name value)))