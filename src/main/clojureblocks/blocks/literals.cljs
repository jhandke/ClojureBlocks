(ns clojureblocks.blocks.literals)

(def number-block
  {:type "number_block"
   :message0 "%1"
   :args0 [{:type "field_number"
            :name "number"
            :value 42}]
   :previousStatement nil
   :nextStatement nil
   :colour 180})