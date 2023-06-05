(ns clojureblocks.generator.hof
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [block]
  (let [function (generator/generate-statement-code block "hof-map-function")
        collection (generator/generate-statement-code block "hof-map-collection")]
    (generator/expression "map" function collection)))

(defn generate-hof-filter-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "filter" pred collection)))

(defn generate-hof-remove-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "remove" pred collection)))

(defn generate-hof-reduce-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        value (generator/generate-statement-code block "value")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "reduce" pred value collection)))

(defn generate-hof-partial-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "partial" pred arguments)))

(defn generate-hof-apply-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "apply" pred arguments)))

(defn generate-hof-juxt-block
  [block]
  (let [preds (generator/generate-statement-code block "preds")]
    (generator/expression "juxt" preds)))