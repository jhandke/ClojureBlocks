(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]))

(def clojure-generator (blockly/Generator. "clojure"))

(defn generate-statement-code
  "Generates the code from `block`'s statement input with the name `identifier`."
  [block identifier]
  (loop [child (.getInputTargetBlock ^blockly/Block block identifier)
         statements []]
    (if child
      (let [statement (.blockToCode clojure-generator child)]
        (recur
         (.getNextBlock ^blockly/Block child)
         (conj statements statement)))
      statements)))

