(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]
            [goog.object :as g]
            [clojureblocks.generator.literals]))

(def clojure-generator (blockly/Generator. "clojure"))

(set! (.. clojure-generator "number_block") clojureblocks.generator.literals/generate-number-literal)
(set! (.. clojure-generator "add_block") clojureblocks.generator.math/generate-adda-function)