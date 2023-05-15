(ns clojureblocks.blocks.all
  (:require [clojureblocks.blocks.control :as control]
            [clojureblocks.blocks.function :as function]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.seqs :as seqs]))

(def all-blocks
  (concat hof/all
          literals/all
          control/all
          seqs/all
          function/all))