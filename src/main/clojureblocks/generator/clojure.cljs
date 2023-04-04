(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]
            [goog.object :as g]
            [clojureblocks.generator.literals]
            [clojureblocks.generator.math]))

(def clojure-generator (blockly/Generator. "clojure"))

(set! (.. clojure-generator -number-block) clojureblocks.generator.literals/generate-number-literal)
(set! (.. clojure-generator  -add_block) clojureblocks.generator.math/generate-add-function)