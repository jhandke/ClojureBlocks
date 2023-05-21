(ns clojureblocks.generator.control
  (:require ["blockly" :as blockly]
            [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-if-block
  "Generates the code for an if-block: `(if test then else)`"
  [^blockly/Block block]
  (let [condition (string/join (generator/generate-statement-code block "condition"))
        then-body (string/join (generator/generate-statement-code block "then"))
        else-body (string/join (generator/generate-statement-code block "else"))]
    (str "(if " condition " " then-body " " else-body ")")))

(defn generate-when-block
  "Generates the code for a when-block: `(when test body)`"
  [^blockly/Block block]
  (let [condition (string/join " " (generator/generate-statement-code block "condition"))
        body (string/join " " (generator/generate-statement-code block "body"))]
    (str "(when " condition " " body ")")))