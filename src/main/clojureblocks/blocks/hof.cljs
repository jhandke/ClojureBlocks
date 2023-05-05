(ns clojureblocks.blocks.hof)

(def hof-map-block
  {:type "hof_map_block"
   :message0 "(map fn %1 seq %2 )"
   :args0 [{:type "input_statement"
            :name "hof-map-function"
            :align "RIGHT"}
           {:type "input_statement"
            :name "hof-map-collection"
            :align "RIGHT"}]
   :colour 0})