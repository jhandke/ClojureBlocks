(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.function :as function]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.seqs :as seqs]))

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

(defn block-to-toolbox
  [block]
  (let [name (get block :type)]
    {:kind "block"
     :type name}))

(defn generate-toolbox
  "Generates the toolbox structure"
  []
  {:kind "categoryToolbox"
   :contents [{:kind "category"
               :name "Functions"
               :contents (map block-to-toolbox function/all)}
              {:kind "category"
               :name "Literals"
               :contents (map block-to-toolbox literals/all)}
              {:kind "category"
               :name "Seqs"
               :contents (map block-to-toolbox seqs/all)}
              {:kind "category"
               :name "HOF"
               :contents (conj (map block-to-toolbox hof/all) map-block-preset)}]})