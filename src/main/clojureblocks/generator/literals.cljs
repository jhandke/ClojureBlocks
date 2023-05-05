(ns clojureblocks.generator.literals
  (:require ["blockly" :as blockly]))

(defn generate-number-block 
  "Returns the field value for number input."
  [^blockly/Block block]
  (str (.getFieldValue block "number")))

(defn generate-symbol-block 
  "Returns the field value for symbol name input."
  [^blockly/Block block]
  (str (.getFieldValue block "symbol_name")))

(defn generate-string-block 
  "Returns the formatted value for string input."
  [^blockly/Block block]
  (str "\"" (.getFieldValue block "string_text") "\""))

(defn generate-keyword-block 
  "Returns the formatted value for keyword name input."
  [^blockly/Block block]
  (str ":" (.getFieldValue block "keyword_name")))