(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.concurrency :as concurrency]
            [clojureblocks.blocks.control :as control]
            [clojureblocks.blocks.function :as function]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.maps :as maps]
            [clojureblocks.blocks.misc :as misc]
            [clojureblocks.blocks.mutlimethods :as multimethods]
            [clojureblocks.blocks.seqs :as seqs]
            [clojureblocks.blocks.sets :as sets]))

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
               :colour function/colour
               :contents (map block-to-toolbox function/all)}
              {:kind "category"
               :name "Literals"
               :colour literals/colour
               :contents (map block-to-toolbox literals/all)}
              {:kind "category"
               :name "Seqs"
               :colour seqs/colour
               :expanded true ;; hacky bug fix. see https://github.com/google/blockly/issues/6940
               :contents (concat (map block-to-toolbox seqs/top-blocks)
                                 [{:kind "category"
                                   :name "Access"
                                   :colour seqs/colour
                                   :contents (map block-to-toolbox seqs/access-blocks)}
                                  {:kind "category"
                                   :name "Order"
                                   :colour seqs/colour
                                   :contents (map block-to-toolbox seqs/order-blocks)}])}
              {:kind "category"
               :name "Maps"
               :colour maps/colour
               :contents (map block-to-toolbox maps/all)}
              {:kind "category"
               :name "Sets"
               :colour sets/colour
               :contents (map block-to-toolbox sets/all)}
              {:kind "category"
               :name "Control"
               :colour control/colour
               :contents (map block-to-toolbox control/all)}
              {:kind "category"
               :name "HOF"
               :colour hof/colour
               :contents (conj (map block-to-toolbox hof/all) map-block-preset)}
              {:kind "category"
               :name "Concurrency"
               :colour concurrency/colour
               :contents (map block-to-toolbox concurrency/all)}
              {:kind "category"
               :name "Multimethods"
               :colour multimethods/colour
               :contents (map block-to-toolbox multimethods/all)}
              {:kind "category"
               :name "Misc."
               :colour misc/colour
               :contents (map block-to-toolbox misc/all)}]})