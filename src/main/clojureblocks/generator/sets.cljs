(ns clojureblocks.generator.sets
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-union-block
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/union" set1 set2)))

(defn generate-intersection-block
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/intersection" set1 set2)))

(defn generate-difference-block
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/difference" set1 set2)))