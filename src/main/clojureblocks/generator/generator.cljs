(ns clojureblocks.generator.generator
  (:require [clojureblocks.generator.clojure :as clojuregenerator]
            [clojureblocks.generator.concurrency :as concurrency]
            [clojureblocks.generator.control :as control]
            [clojureblocks.generator.function :as function]
            [clojureblocks.generator.hof :as hof]
            [clojureblocks.generator.literals :as literals]
            [clojureblocks.generator.maps :as maps]
            [clojureblocks.generator.misc :as misc]
            [clojureblocks.generator.multimethods :as multimethods]
            [clojureblocks.generator.seqs :as seqs]
            [clojureblocks.generator.sets :as sets]))

(def generator clojuregenerator/clojure-generator)

(set! (.. clojuregenerator/clojure-generator -number-block) literals/generate-number-block)
(set! (.. clojuregenerator/clojure-generator -string-block) literals/generate-string-block)
(set! (.. clojuregenerator/clojure-generator -keyword-block) literals/generate-keyword-block)
(set! (.. clojuregenerator/clojure-generator -symbol-block) literals/generate-symbol-block)
(set! (.. clojuregenerator/clojure-generator -list-block) literals/generate-list-block)
(set! (.. clojuregenerator/clojure-generator -vector-block) literals/generate-vector-block)
(set! (.. clojuregenerator/clojure-generator -map-block) literals/generate-map-block)
(set! (.. clojuregenerator/clojure-generator -set-block) literals/generate-set-block)

(set! (.. clojuregenerator/clojure-generator -count-block) seqs/generate-count-block)
(set! (.. clojuregenerator/clojure-generator -empty-q-block) seqs/generate-empty-q-block)
(set! (.. clojuregenerator/clojure-generator -cons-block) seqs/generate-cons-block)
(set! (.. clojuregenerator/clojure-generator -conj-block) seqs/generate-conj-block)
(set! (.. clojuregenerator/clojure-generator -into-block) seqs/generate-into-block)

(set! (.. clojuregenerator/clojure-generator -first-block) seqs/generate-first-block)
(set! (.. clojuregenerator/clojure-generator -rest-block) seqs/generate-rest-block)
(set! (.. clojuregenerator/clojure-generator -take-block) seqs/generate-take-block)
(set! (.. clojuregenerator/clojure-generator -drop-block) seqs/generate-drop-block)
(set! (.. clojuregenerator/clojure-generator -take-while-block) seqs/generate-take-while-block)
(set! (.. clojuregenerator/clojure-generator -drop-while-block) seqs/generate-drop-while-block)
(set! (.. clojuregenerator/clojure-generator -concat-block) seqs/generate-concat-block)

(set! (.. clojuregenerator/clojure-generator -sort-block) seqs/generate-sort-block)
(set! (.. clojuregenerator/clojure-generator -sort-by-block) seqs/generate-sort-by-block)
(set! (.. clojuregenerator/clojure-generator -shuffle-block) seqs/generate-shuffle-block)
(set! (.. clojuregenerator/clojure-generator -reverse-block) seqs/generate-reverse-block)

(set! (.. clojuregenerator/clojure-generator -hof-map-block) hof/generate-hof-map-block)
(set! (.. clojuregenerator/clojure-generator -hof-filter-block) hof/generate-hof-filter-block)
(set! (.. clojuregenerator/clojure-generator -hof-remove-block) hof/generate-hof-remove-block)
(set! (.. clojuregenerator/clojure-generator -hof-reduce-block) hof/generate-hof-reduce-block)
(set! (.. clojuregenerator/clojure-generator -hof-partial-block) hof/generate-hof-partial-block)
(set! (.. clojuregenerator/clojure-generator -hof-apply-block) hof/generate-hof-apply-block)
(set! (.. clojuregenerator/clojure-generator -hof-juxt-block) hof/generate-hof-juxt-block)

(set! (.. clojuregenerator/clojure-generator -defn-block) function/generate-defn-block)
(set! (.. clojuregenerator/clojure-generator -fn-call-block) function/generate-fn-call-block)
(set! (.. clojuregenerator/clojure-generator -fn-block) function/generate-fn-block)
(set! (.. clojuregenerator/clojure-generator -def-block) function/generate-def-block)

(set! (.. clojuregenerator/clojure-generator -if-block) control/generate-if-block)
(set! (.. clojuregenerator/clojure-generator -when-block) control/generate-when-block)

(set! (.. clojuregenerator/clojure-generator -assoc-block) maps/generate-assoc-block)
(set! (.. clojuregenerator/clojure-generator -dissoc-block) maps/generate-dissoc-block)
(set! (.. clojuregenerator/clojure-generator -get-block) maps/generate-get-block)

(set! (.. clojuregenerator/clojure-generator -atom-block) concurrency/generate-atom-block)
(set! (.. clojuregenerator/clojure-generator -deref-block) concurrency/generate-deref-block)
(set! (.. clojuregenerator/clojure-generator -swap-block) concurrency/generate-swap-block)
(set! (.. clojuregenerator/clojure-generator -reset-block) concurrency/generate-reset-block)

(set! (.. clojuregenerator/clojure-generator -union-block) sets/generate-union-block)
(set! (.. clojuregenerator/clojure-generator -intersection-block) sets/generate-intersection-block)
(set! (.. clojuregenerator/clojure-generator -difference-block) sets/generate-difference-block)

(set! (.. clojuregenerator/clojure-generator -quote-block) misc/generate-quote-block)
(set! (.. clojuregenerator/clojure-generator -frequencies-block) misc/generate-frequencies-block)
(set! (.. clojuregenerator/clojure-generator -partition-block) misc/generate-partition-block)
(set! (.. clojuregenerator/clojure-generator -partition-by-block) misc/generate-partition-by-block)
(set! (.. clojuregenerator/clojure-generator -group-by-block) misc/generate-group-by-block)
(set! (.. clojuregenerator/clojure-generator -distinct-block) misc/generate-distinct-block)
(set! (.. clojuregenerator/clojure-generator -dedupe-block) misc/generate-dedupe-block)
(set! (.. clojuregenerator/clojure-generator -interleave-block) misc/generate-interleave-block)
(set! (.. clojuregenerator/clojure-generator -interpose-block) misc/generate-interpose-block)

(set! (.. clojuregenerator/clojure-generator -defmulti-block) multimethods/generate-defmulti-block)
(set! (.. clojuregenerator/clojure-generator -defmethod-block) multimethods/generate-defmethod-block)