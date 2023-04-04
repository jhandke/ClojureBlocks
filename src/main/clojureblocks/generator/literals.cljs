(ns clojureblocks.generator.literals
  (:require ["blockly" :as blockly]) )

(defn generate-number-literal [^blockly/Block block]
  (str (.getFieldValue block "number")))