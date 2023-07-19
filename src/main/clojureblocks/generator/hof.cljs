(ns clojureblocks.generator.hof
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [block]
  (let [function (generator/generate-statement-code block "hof-map-function")
        collection (generator/generate-statement-code block "hof-map-collection")]
    (generator/expression "map" function collection)))

(defn generate-hof-filter-block
  "Generates the code for a filter block: `(filter pred coll)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "filter" pred collection)))

(defn generate-hof-remove-block
  "Generates the code for a remove block: `(remove pred coll)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "remove" pred collection)))

(defn generate-hof-reduce-block
  "Generates the code for a reduce block: `(reduce pred value coll)` or `(reduce pred coll)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        value (generator/generate-statement-code block "value")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "reduce" pred value collection)))

(defn generate-hof-partial-block
  "Generates the code for a partial block: `(partial f & args)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "partial" pred arguments)))

(defn generate-hof-apply-block
  "Generates the code for an apply block: `(apply f args coll)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "apply" pred arguments)))

(defn generate-hof-juxt-block
  "Generates the code for a juxt block: `(juxt f g h & fns)`"
  [block]
  (let [preds (generator/generate-statement-code block "preds")]
    (generator/expression "juxt" preds)))