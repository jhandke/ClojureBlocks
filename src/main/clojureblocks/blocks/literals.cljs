(ns clojureblocks.blocks.literals)

(def string-block
  {:type "string_block"
   :message0 "string %1"
   :args0 [{:type "field_input"
            :name "string_text"
            :text "text"}]
   :previousStatement nil
   :nextStatement nil
   :colour 90})

(def number-block
  {:type "number_block"
   :message0 "number %1"
   :args0 [{:type "field_number"
            :name "number"
            :value 42}]
   :previousStatement nil
   :nextStatement nil
   :colour 90})

(def symbol-block
  {:type "symbol_block"
   :message0 "symbol %1"
   :args0 [{:type "field_input"
            :name "symbol_name"
            :text "x"}]
   :previousStatement nil
   :nextStatement nil
   :colour 315})

(def keyword-block
  {:type "keyword_block"
   :message0 "keyword %1"
   :args0 [{:type "field_input"
            :name "keyword_name"
            :text "a"}]
   :previousStatement nil
   :nextStatement nil
   :colour 45})