(ns clojureblocks.generator.seqs
  (:require [clojureblocks.generator.clojure :as generator]
            [clojure.string :as string]))

(defn generate-list-block
  "Generates the code for a list block: `(e1 e2 ...)`."
  [block]
  (let [items (string/join " " (generator/generate-statement-code block "list-items"))]
    (str "(" items ")")))

(defn generate-vector-block
  "Generates the code for a vector block: `[e1 e2 ...]`."
  [block]
  (let [items (string/join " " (generator/generate-statement-code block "vector-items"))]
    (str "[" items "]")))

(defn generate-map-block
  "Generates the code for a map block: `{:a 1 :b 42 ...}`."
  [block]
  (let [items (string/join " " (generator/generate-statement-code block "map-items"))]
    (str "{" items "}")))

(defn generate-count-block
  "Generates the code for a count block: `(count coll)`"
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(count " collection ")")))

(defn generate-empty-q-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(empty? " collection ")")))

(defn generate-cons-block
  [block]
  (let [element (string/join " " (generator/generate-statement-code block "element"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(cons " element " " collection ")")))

(defn generate-conj-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))
        element (string/join " " (generator/generate-statement-code block "element"))]
    (str "(conj " collection " " element ")")))

(defn generate-first-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(first " collection ")")))

(defn generate-rest-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(rest " collection ")")))

(defn generate-take-block
  [block]
  (let [n (string/join " " (generator/generate-statement-code block "n"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(take " n " " collection ")")))

(defn generate-drop-block
  [block]
  (let [n (string/join " " (generator/generate-statement-code block "n"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(drop " n " " collection ")")))

(defn generate-take-while-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(take-while " pred " " collection ")")))

(defn generate-drop-while-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(drop-while " pred " " collection ")")))

(defn generate-concat-block
  [block]
  (let [collections (string/join " " (generator/generate-statement-code block "collections"))]
    (str "(concat " collections ")")))

(defn generate-sort-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(sort " collection ")")))

(defn generate-sort-by-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(sort-by " pred " " collection ")")))

(defn generate-shuffle-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(shuffle " collection ")")))

(defn generate-reverse-block
  [block]
  (let [collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(reverse " collection ")")))