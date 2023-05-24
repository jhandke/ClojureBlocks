(ns clojureblocks.generator.sets 
  (:require [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-union-block
  [block]
  (let [set1 (string/join " " (generator/generate-statement-code block "set1"))
        set2 (string/join " " (generator/generate-statement-code block "set2"))]
    (str "(clojure.set/union " set1 " " set2 ")")))

(defn generate-intersection-block
  [block]
  (let [set1 (string/join " " (generator/generate-statement-code block "set1"))
        set2 (string/join " " (generator/generate-statement-code block "set2"))]
    (str "(clojure.set/intersection " set1 " " set2 ")")))

(defn generate-difference-block
  [block]
  (let [set1 (string/join " " (generator/generate-statement-code block "set1"))
        set2 (string/join " " (generator/generate-statement-code block "set2"))]
    (str "(clojure.set/difference " set1 " " set2 ")")))