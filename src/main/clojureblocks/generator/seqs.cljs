(ns clojureblocks.generator.seqs
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-count-block
  "Generates the code for a count block: `(count coll)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "count" collection)))

(defn generate-empty-q-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "empty?" collection)))

(defn generate-cons-block
  [block]
  (let [element (generator/generate-statement-code block "element")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "cons" element collection)))

(defn generate-conj-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")
        element (generator/generate-statement-code block "element")]
    (generator/expression "conj" collection element)))

(defn generate-into-block
  [block]
  (let [destination (generator/generate-statement-code block "destination")
        source (generator/generate-statement-code block "source")]
    (generator/expression "into" destination source)))


(defn generate-first-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "first" collection)))

(defn generate-rest-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "rest" collection)))

(defn generate-take-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "take" n collection)))

(defn generate-drop-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "drop" n collection)))

(defn generate-take-while-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "take-while" pred collection)))

(defn generate-drop-while-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "drop-while" pred collection)))

(defn generate-concat-block
  [block]
  (let [collections (generator/generate-statement-code block "collections")]
    (generator/expression "concat" collections)))

(defn generate-sort-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "sort" collection)))

(defn generate-sort-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "sort-by" pred collection)))

(defn generate-shuffle-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "shuffle" collection)))

(defn generate-reverse-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "reverse" collection)))