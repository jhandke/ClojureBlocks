(ns clojureblocks.generator.misc 
  (:require [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-frequencies-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(frequencies " collection ")")))

(defn generate-partition-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        step (generator/generate-statement-code block "step")
        collection (generator/generate-statement-code block "collection")]
    (str "(partition " n " " (when-not (string/blank? step) (str step " ")) collection ")")))

(defn generate-partition-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (str "(partition-by " pred " " collection ")")))

(defn generate-group-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (str "(group-by " pred " " collection ")")))

(defn generate-distinct-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(distinct " collection ")")))

(defn generate-dedupe-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(dedupe " collection ")")))

(defn generate-interleave-block
  [block]
  (let [collection1 (generator/generate-statement-code block "collection1")
        collection2 (generator/generate-statement-code block "collection2")]
    (str "(interleave " collection1 " " collection2 ")")))

(defn generate-interpose-block
  [block]
  (let [seperator (generator/generate-statement-code block "seperator")
        collection (generator/generate-statement-code block "collection")]
    (str "(interpose " seperator " " collection ")")))