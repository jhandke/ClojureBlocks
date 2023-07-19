(ns clojureblocks.generator.sets
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-union-block
  "Generates the code for a union block: `(union set1 set2)`"
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/union" set1 set2)))

(defn generate-intersection-block
  "Generates the code for an intersection block: `(intersection set1 set2)`"
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/intersection" set1 set2)))

(defn generate-difference-block
  "Generates the code for a difference block: `(difference set1 set2)`"
  [block]
  (let [set1 (generator/generate-statement-code block "set1")
        set2 (generator/generate-statement-code block "set2")]
    (generator/expression "clojure.set/difference" set1 set2)))