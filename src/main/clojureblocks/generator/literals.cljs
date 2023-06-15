(ns clojureblocks.generator.literals
  (:require ["blockly" :as blockly]
            [clojureblocks.generator.clojure :as generator]))

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

(defn generate-list-block
  "Generates the code for a list block: `(e1 e2 ...)`."
  [block]
  (let [items (generator/generate-statement-code block "list-items")]
    (str "(" items ")")))

(defn generate-vector-block
  "Generates the code for a vector block: `[e1 e2 ...]`."
  [block]
  (let [items (generator/generate-statement-code block "vector-items")]
    (str "[" items "]")))

(defn generate-map-block
  "Generates the code for a map block: `{:a 1 :b 42 ...}`."
  [block]
  (let [items (generator/generate-statement-code block "map-items")]
    (str "{" items "}")))

(defn generate-set-block
  "Generates the code for a set block: `#{1 :b \"three\"}`"
  [block]
  (let [items (generator/generate-statement-code block "set-items")]
    (str "#{" items "}")))