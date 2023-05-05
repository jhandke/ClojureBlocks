(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.all]))

(def map-block-preset
  "Block preset for map block with predicate and collection"
  {:kind "block"
   :type "hof_map_block"
   :inputs {:hof-map-function
            {:block {:type "symbol_block"
                     :fields {:symbol_name "inc"}}}
            :hof-map-collection 
            {:block {:type "list_block"
                     :inputs {:list-items
                              {:block {:type "symbol_block"
                                       :fields {:symbol_name "range"}
                                       :next
                                       {:block {:type "number_block"
                                                :fields {:number 10}}}}}}}}}})

(defn generate-toolbox 
  "Generates the toolbox structure"
  []
  (let [all-blocks (map
                    (fn [block]
                      (let [name (get block :type)]
                        {:kind "block"
                         :type name}))
                    clojureblocks.blocks.all/all-blocks)]
    {:kind "flyoutToolbox"
     :contents (conj (vec all-blocks) map-block-preset)}))