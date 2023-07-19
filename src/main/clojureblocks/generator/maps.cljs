(ns clojureblocks.generator.maps
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-assoc-block
  "Generates the code for an assoc block: `(assoc map key value)`"
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        value (generator/generate-statement-code block "value")]
    (generator/expression "assoc" map key value)))

(defn generate-dissoc-block
  "Generates the code for a dissoc block: `(dissoc map key)`"
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")]
    (generator/expression "dissoc" map key)))

(defn generate-get-block
  "Generates the code for a get block: `(get map key)` or `(get map key default)`"
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        default (generator/generate-statement-code block "default")]
    (generator/expression "get" map key default)))