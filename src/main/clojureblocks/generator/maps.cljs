(ns clojureblocks.generator.maps 
  (:require [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-assoc-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        value (generator/generate-statement-code block "value")]
    (str "(assoc " map " " key " " value ")")))

(defn generate-dissoc-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")]
    (str "(dissoc " map " " key ")")))

(defn generate-get-block
  [block]
  (let [map (generator/generate-statement-code block "map")
        key (generator/generate-statement-code block "key")
        default (generator/generate-statement-code block "default")]
    (str "(get " map " " key 
         (when-not (string/blank? default) (str " " default))
         ")")))