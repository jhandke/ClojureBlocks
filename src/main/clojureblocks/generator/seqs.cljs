(ns clojureblocks.generator.seqs
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-count-block
  "Generates the code for a count block: `(count coll)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(count " collection ")")))

(defn generate-empty-q-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(empty? " collection ")")))

(defn generate-cons-block
  [block]
  (let [element (generator/generate-statement-code block "element")
        collection (generator/generate-statement-code block "collection")]
    (str "(cons " element " " collection ")")))

(defn generate-conj-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")
        element (generator/generate-statement-code block "element")]
    (str "(conj " collection " " element ")")))

(defn generate-into-block
  [block]
  (let [destination (generator/generate-statement-code block "destination")
        source (generator/generate-statement-code block "source")]
    (str "(into " destination " " source ")")))


(defn generate-first-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(first " collection ")")))

(defn generate-rest-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(rest " collection ")")))

(defn generate-take-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (str "(take " n " " collection ")")))

(defn generate-drop-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        collection (generator/generate-statement-code block "collection")]
    (str "(drop " n " " collection ")")))

(defn generate-take-while-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (str "(take-while " pred " " collection ")")))

(defn generate-drop-while-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (str "(drop-while " pred " " collection ")")))

(defn generate-concat-block
  [block]
  (let [collections (generator/generate-statement-code block "collections")]
    (str "(concat " collections ")")))

(defn generate-sort-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(sort " collection ")")))

(defn generate-sort-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (str "(sort-by " pred " " collection ")")))

(defn generate-shuffle-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(shuffle " collection ")")))

(defn generate-reverse-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (str "(reverse " collection ")")))