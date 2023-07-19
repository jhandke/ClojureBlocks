(ns clojureblocks.generator.misc
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-quote-block
  "Generates the code for a quote block: `(quote body)`"
  [block]
  (let [body (generator/generate-statement-code block "body")]
    (generator/expression "quote" body)))

(defn generate-frequencies-block
  "Generates the code for a frequencies block: `(frequencies collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "frequencies" collection)))

(defn generate-partition-block
  "Generates the code for a partition block: `(partition n step collection)`"
  [block]
  (let [n (generator/generate-statement-code block "n")
        step (generator/generate-statement-code block "step")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "partition" n step collection)))

(defn generate-partition-by-block
  "Generates the code for a partition-by block: `(partition-by pred collection)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "partition-by" pred collection)))

(defn generate-group-by-block
  "Generates the code for a group-by block: `(group-by pred collection)`"
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "group-by" pred collection)))

(defn generate-distinct-block
  "Generates the code for a distinct block: `(distinct collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "distinct" collection)))

(defn generate-dedupe-block
  "Generates the code for a dedupe block: `(dedupe collection)`"
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "dedupe" collection)))

(defn generate-interleave-block
  "Generates the code for an interleave block: `(interleave coll1 coll2)`"
  [block]
  (let [collection1 (generator/generate-statement-code block "collection1")
        collection2 (generator/generate-statement-code block "collection2")]
    (generator/expression "interleave" collection1 collection2)))

(defn generate-interpose-block
  "Generates the code for an interpose block: `(interpose seperator collection)`"
  [block]
  (let [seperator (generator/generate-statement-code block "seperator")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "interpose" seperator collection)))