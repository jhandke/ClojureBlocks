(ns clojureblocks.generator.seqs
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-count-block
  "Generates the code for a count block: `(count collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "count" collection)))

(defn generate-empty-q-block
  "Generates the code for a empty? block: `(empty? collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "empty?" collection)))

(defn generate-cons-block
  "Generates the code for a cons block: `(cons element collection)`"
  [block]
  (let [element (generator/generate-statement-code block "element")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "cons" element collection)))

(defn generate-conj-block
  "Generates the code for a conj block: `(conj collection element)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")
        element (generator/generate-statement-code block "element")]
    (generator/expression "conj" collection element)))

(defn generate-into-block
  "Generates the code for a into block: `(into to from)`"
  [block]
  (let [destination (generator/generate-statement-code block "destination")
        source (generator/generate-statement-code block "source")]
    (generator/expression "into" destination source)))

(defn generate-first-block
  "Generates the code for a first block: `(first collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "first" collection)))

(defn generate-rest-block
  "Generates the code for a rest block: `(rest collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "rest" collection)))

(defn generate-take-block
  "Generates the code for a take block: `(take n collection)`"
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "take" n collection)))

(defn generate-drop-block
  "Generates the code for a drop block: `(drop n collection)`"
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "drop" n collection)))

(defn generate-take-while-block
  "Generates the code for a take-while block: `(take-while pred collection)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "take-while" pred collection)))

(defn generate-drop-while-block
  "Generates the code for a drop-while block: `(drop-while pred collection)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "drop-while" pred collection)))

(defn generate-concat-block
  "Generates the code for a concat block: `(concat & colls)`"
  [block]
  (let [collections (generator/generate-statement-code block "collections")]
    (generator/expression "concat" collections)))

(defn generate-sort-block
  "Generates the code for a sort block: `(sort collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "sort" collection)))

(defn generate-sort-by-block
  "Generates the code for a sort-by block: `(sort-by pred collection)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "sort-by" pred collection)))

(defn generate-shuffle-block
  "Generates the code for a shuffle block: `(shuffle collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "shuffle" collection)))

(defn generate-reverse-block
  "Generates the code for a reverse block: `(reverse collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "reverse" collection)))