(ns clojureblocks.generator.maps
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-assoc-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        value (generator/generate-statement-code block "value")]
    (generator/expression "assoc" map key value)))

(defn generate-dissoc-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")]
    (generator/expression "dissoc" map key)))

(defn generate-get-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        default (generator/generate-statement-code block "default")]
    (generator/expression "get" map key default)))