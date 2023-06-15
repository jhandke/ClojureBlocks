(ns clojureblocks.generator.multimethods
  (:require ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-defmulti-block
  "Generates the code for a defmulti block: `(defmulti my-mm dispatch-fn)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        dispatch-function (generator/generate-statement-code block "dispatch_function")]
    (generator/expression "defmulti" function-name dispatch-function)))

(defn generate-defmethod-block
  "Generates the code for a defmethod block: `(defmethod my-mm fn-tail)`"
  [^blockly/Block block]
  (let [function-name (.getFieldValue block "function_name")
        dispatch-value (generator/generate-statement-code block "dispatch_value")
        function-tail (generator/generate-statement-code block "function_tail")]
    (generator/expression "defmethod" function-name dispatch-value function-tail)))