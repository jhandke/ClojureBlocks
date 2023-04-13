(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.all]))

(def map-block-preset
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

(defn generate-toolbox []
  (let [all-blocks (map
                    (fn [block]
                      (let [name (get block :type)]
                        {:kind "block"
                         :type name}))
                    (clojureblocks.blocks.all/blocks))]
    {:kind "flyoutToolbox"
     :contents (conj (vec all-blocks) map-block-preset)}))