(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]
            [clojure.string :as string]))

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
      (string/join " " statements))))

(defn expression
  [& args]
  (str "(" (string/trim (string/join " " (remove string/blank? args))) ")"))