(ns clojureblocks.generator.generator
  (:require [clojureblocks.generator.literals :as literals]
            [clojureblocks.generator.seqs :as seqs]
            [clojureblocks.generator.hof :as hof]
            [clojureblocks.generator.function :as function]
            [clojureblocks.generator.control :as control]
            [clojureblocks.generator.clojure :as clojuregenerator]))

(def generator clojuregenerator/clojure-generator)

(set! (.. clojuregenerator/clojure-generator -number-block) literals/generate-number-block)
(set! (.. clojuregenerator/clojure-generator -string-block) literals/generate-string-block)
(set! (.. clojuregenerator/clojure-generator -keyword-block) literals/generate-keyword-block)
(set! (.. clojuregenerator/clojure-generator -symbol-block) literals/generate-symbol-block)
(set! (.. clojuregenerator/clojure-generator -list-block) seqs/generate-list-block)
(set! (.. clojuregenerator/clojure-generator -vector-block) seqs/generate-vector-block)
(set! (.. clojuregenerator/clojure-generator -map-block) seqs/generate-map-block)
(set! (.. clojuregenerator/clojure-generator -hof-map-block) hof/generate-hof-map-block)
(set! (.. clojuregenerator/clojure-generator -defn-block) function/generate-defn-block)
(set! (.. clojuregenerator/clojure-generator -fn-call-block) function/generate-fn-call-block)
(set! (.. clojuregenerator/clojure-generator -if-block) control/generate-if-block)
(set! (.. clojuregenerator/clojure-generator -when-block) control/generate-when-block)
