(ns clojureblocks.blocks.all
  (:require [clojureblocks.blocks.concurrency :as concurrency]
            [clojureblocks.blocks.control :as control]
            [clojureblocks.blocks.function :as function]
            [clojureblocks.blocks.hof :as hof]
            [clojureblocks.blocks.literals :as literals]
            [clojureblocks.blocks.maps :as maps]
            [clojureblocks.blocks.misc :as misc]
            [clojureblocks.blocks.seqs :as seqs]
            [clojureblocks.blocks.sets :as sets]))

(def all-blocks
  (concat hof/all
          literals/all
          control/all
          seqs/all
          function/all
          concurrency/all
          maps/all
          sets/all
          misc/all))