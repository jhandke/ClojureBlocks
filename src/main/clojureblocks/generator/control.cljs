(ns clojureblocks.generator.control
  (:require ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-if-block
  "Generates the code for an if-block: `(if test then else)`"
  [^blockly/Block block]
  (let [condition (generator/generate-statement-code block "condition")
        then-body (generator/generate-statement-code block "then")
        else-body (generator/generate-statement-code block "else")]
    (generator/expression "if" condition then-body else-body)))

(defn generate-when-block
  "Generates the code for a when-block: `(when test body)`"
  [^blockly/Block block]
  (let [condition (generator/generate-statement-code block "condition")
        body (generator/generate-statement-code block "body")]
    (generator/expression "when" condition body)))