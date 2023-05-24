(ns clojureblocks.generator.hof
  (:require [clojureblocks.generator.clojure :as generator]
            [clojure.string :as string]))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [block]
  (let [function (string/join " " (generator/generate-statement-code block "hof-map-function"))
        collection (string/join " " (generator/generate-statement-code block "hof-map-collection"))]
    (str "(map " function " " collection ")")))

(defn generate-hof-filter-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(filter " pred " " collection ")")))

(defn generate-hof-reduce-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (str "(reduce " pred " " collection ")")))

(defn generate-hof-partial-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        arguments (string/join " " (generator/generate-statement-code block "arguments"))]
    (str "(partial " pred " " arguments ")")))

(defn generate-hof-apply-block
  [block]
  (let [pred (string/join " " (generator/generate-statement-code block "pred"))
        arguments (string/join " " (generator/generate-statement-code block "arguments"))]
    (str "(apply " pred " " arguments ")")))

(defn generate-hof-juxt-block
  [block]
  (let [preds (string/join " " (generator/generate-statement-code block "preds"))
        collection (string/join " " (generator/generate-statement-code block "collection"))]
    (if (empty? collection)
      (str "(juxt " preds ")")
      (str "((juxt " preds ") " collection ")"))))