(ns clojureblocks.blocks.math)

(def add-block 
  {:type "add_block"
   :message0 "+ %1 %2"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "items"}]
   :colour 230})