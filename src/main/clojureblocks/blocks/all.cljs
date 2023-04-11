(ns clojureblocks.blocks.all
  (:require
   ["blockly" :as blockly]
   [clojureblocks.blocks.seqs :as seqs]
   [clojureblocks.blocks.literals :as literals]))

(defn blocks []
  (merge []
         seqs/list-block
         seqs/vector-block
         seqs/map-block
         literals/string-block
         literals/number-block
         literals/symbol-block
         literals/keyword-block))

(defn define-blocks []
  ;; (. blockly (defineBlocksWithJsonArray blocks))
  (.defineBlocksWithJsonArray blockly (clj->js (blocks))))