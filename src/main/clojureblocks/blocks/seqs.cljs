(ns clojureblocks.blocks.seqs)

(def list-block 
  {:type "list_block"
   :message0 "( %1 %2 )"
   :args0 [
           {:type "input_dummy"}
           {:type "input_statement"
            :name "list-items"}
   ]
   :colour 230
   :tooltop "list block yea"
   :helpUrl ""})