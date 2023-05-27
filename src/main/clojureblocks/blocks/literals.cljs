(ns clojureblocks.blocks.literals)

(def colour "#0000cd") ;; mediumblue

(def string-block
  {:type "string_block"
   :message0 "string %1"
   :args0 [{:type "field_input"
            :name "string_text"
            :text "text"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def number-block
  {:type "number_block"
   :message0 "number %1"
   :args0 [{:type "field_number"
            :name "number"
            :value 42}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def symbol-block
  {:type "symbol_block"
   :message0 "symbol %1"
   :args0 [{:type "field_input"
            :name "symbol_name"
            :text "x"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def keyword-block
  {:type "keyword_block"
   :message0 "keyword %1"
   :args0 [{:type "field_input"
            :name "keyword_name"
            :text "a"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def all
  [string-block
   number-block
   symbol-block
   keyword-block])