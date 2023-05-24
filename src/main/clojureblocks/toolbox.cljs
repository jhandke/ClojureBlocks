(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.concurrency :as concurrency]
            [clojureblocks.blocks.control :as control]
            [clojureblocks.blocks.function :as function]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.maps :as maps]
            [clojureblocks.blocks.seqs :as seqs]
            [clojureblocks.blocks.sets :as sets]
            [clojureblocks.blocks.misc :as misc]))

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
               :contents (concat (map block-to-toolbox seqs/top-blocks)
                                 [{:kind "category"
                                   :name "Access"
                                   :contents (map block-to-toolbox seqs/access-blocks)}
                                  {:kind "category"
                                   :name "Order"
                                   :contents (map block-to-toolbox seqs/order-blocks)}])}
              {:kind "category"
               :name "Maps"
               :contents (map block-to-toolbox maps/all)}
              {:kind "category"
               :name "Sets"
               :contents (map block-to-toolbox sets/all)}
              {:kind "category"
               :name "Control"
               :contents (map block-to-toolbox control/all)}
              {:kind "category"
               :name "HOF"
               :contents (conj (map block-to-toolbox hof/all) map-block-preset)}
              {:kind "category"
               :name "Concurrency"
               :contents (map block-to-toolbox concurrency/all)}
              {:kind "category"
               :name "Misc."
               :contents (map block-to-toolbox misc/all)}]})