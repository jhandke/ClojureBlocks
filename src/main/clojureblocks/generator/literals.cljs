(ns clojureblocks.generator.literals
  (:require ["blockly" :as blockly]) )

(defn generate-number-block [^blockly/Block block]
  (str (.getFieldValue block "number")))

(defn generate-symbol-block [^blockly/Block block]
  (str (.getFieldValue block "symbol_name")))

(defn generate-string-block [^blockly/Block block]
  (str "\"" (.getFieldValue block "string_text") "\""))

(defn generate-keyword-block [^blockly/Block block]
  (str ":" (.getFieldValue block "keyword_name")))