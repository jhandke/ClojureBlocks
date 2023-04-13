(ns clojureblocks.blocks.all
  (:require ["blockly" :as blockly]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.seqs :as seqs]))

(defn blocks []
  (merge []
         seqs/list-block
         seqs/vector-block
         seqs/map-block
         literals/string-block
         literals/number-block
         literals/symbol-block
         literals/keyword-block
         hof/hof-map-block))

(defn define-blocks []
  (.defineBlocksWithJsonArray blockly (clj->js (blocks))))