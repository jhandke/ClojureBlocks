(ns clojureblocks.blocks.all
  (:require [clojureblocks.blocks.seqs]
            [clojureblocks.blocks.math]
            [clojureblocks.blocks.literals]))

(defn blocks []
  (clj->js (merge []
                  clojureblocks.blocks.seqs/list-block
                  clojureblocks.blocks.math/add-block
                  clojureblocks.blocks.literals/number-block)))