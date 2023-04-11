(ns clojureblocks.blocks.seqs)

(def list-block 
  {:type "list_block"
   :message0 "( %1 %2 )"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "list-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour 230})

(def vector-block 
  {:type "vector_block"
   :message0 "[ %1 %2 ]"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "vector-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour 230})

(def map-block 
  {:type "map_block"
   :message0 "{ %1 %2 }"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "map-items"}]
   :previousStatement nil
   :nextStatement nil
   :colour 230})