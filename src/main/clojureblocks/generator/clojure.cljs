(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]
            [clojure.string :as string]
            [clojureblocks.generator.literals]
            [clojureblocks.generator.math]
            [clojureblocks.generator.seqs]))

(def clojure-generator (blockly/Generator. "clojure"))

(defn generate-statement-code [block identifier]
  (loop [child (.getInputTargetBlock ^js/blockly.Block block identifier)
         statements []]
    (if child
      (let [statement (.blockToCode clojure-generator child)]
        (recur (.getNextBlock ^js/blockly.Block child) (conj statements statement)))
      statements)))

(defn generate-add-function [block]
  (str (generate-statement-code block "items")))
  
(defn generate-list-block [block]
  (str "(" (string/join " " (generate-statement-code block "list-items")) ")"))

(defn generate-vector-block [block]
  (str "[" (string/join " " (generate-statement-code block "vector-items")) "]"))

(defn generate-map-block [block]
  (str "{" (string/join " " (generate-statement-code block "map-items")) "}"))

(set! (.. clojure-generator -number-block) clojureblocks.generator.literals/generate-number-block)
(set! (.. clojure-generator -string-block) clojureblocks.generator.literals/generate-string-block)
(set! (.. clojure-generator -keyword-block) clojureblocks.generator.literals/generate-keyword-block)
(set! (.. clojure-generator -symbol-block) clojureblocks.generator.literals/generate-symbol-block)
(set! (.. clojure-generator -list-block) generate-list-block)
(set! (.. clojure-generator -vector-block) generate-vector-block)
(set! (.. clojure-generator -map-block) generate-map-block)