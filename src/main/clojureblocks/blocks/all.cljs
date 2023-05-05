(ns clojureblocks.blocks.all
  (:require [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.seqs :as seqs]
            [clojureblocks.blocks.function :as function]))

(def all-blocks
  (vector function/defn-block
          function/fn-call-block
          seqs/list-block
          seqs/vector-block
          seqs/map-block
          literals/string-block
          literals/number-block
          literals/symbol-block
          literals/keyword-block
          hof/hof-map-block))