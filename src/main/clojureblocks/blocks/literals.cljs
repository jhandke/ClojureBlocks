(ns clojureblocks.blocks.literals)

(def colour "#4141fa") ;; mediumblue

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

(def list-block
  {:type "list_block"
   :message0 "( %1 %2 )"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "list-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def vector-block
  {:type "vector_block"
   :message0 "[ %1 %2 ]"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "vector-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def map-block
  {:type "map_block"
   :message0 "{ %1 %2 }"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "map-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour})

(def all
  [string-block
   number-block
   symbol-block
   keyword-block
   list-block
   vector-block
   map-block])