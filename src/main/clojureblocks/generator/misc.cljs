(ns clojureblocks.generator.misc
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-quote-block
  [block]
  (let [body (generator/generate-statement-code block "body")]
    (generator/expression "quote" body)))

(defn generate-frequencies-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "frequencies" collection)))

(defn generate-partition-block
  [block]
  (let [n (generator/generate-statement-code block "n")
        step (generator/generate-statement-code block "step")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "partition" n step collection)))

(defn generate-partition-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "partition-by" pred collection)))

(defn generate-group-by-block
  [block]
  (let [pred (generator/generate-statement-code block "pred")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "group-by" pred collection)))

(defn generate-distinct-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "distinct" collection)))

(defn generate-dedupe-block
  [block]
  (let [collection (generator/generate-statement-code block "collection")]
    (generator/expression "dedupe" collection)))

(defn generate-interleave-block
  [block]
  (let [collection1 (generator/generate-statement-code block "collection1")
        collection2 (generator/generate-statement-code block "collection2")]
    (generator/expression "interleave" collection1 collection2)))

(defn generate-interpose-block
  [block]
  (let [seperator (generator/generate-statement-code block "seperator")
        collection (generator/generate-statement-code block "collection")]
    (generator/expression "interpose" seperator collection)))