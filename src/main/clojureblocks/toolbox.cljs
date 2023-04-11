(ns clojureblocks.toolbox
  (:require [clojureblocks.blocks.all]))

(defn generate-toolbox []
  {:kind "flyoutToolbox"
   :contents (map
               (fn [block]
                 (let [name (get block :type)]
                   {:kind "block"
                    :type name}))
               (clojureblocks.blocks.all/blocks))})

