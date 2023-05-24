(ns clojureblocks.generator.concurrency
  (:require [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-atom-block
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (str "(atom" (when-not (string/blank? value) (str " " value)) ")")))

(defn generate-agent-block
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (str "(agent" (when-not (string/blank? value) (str " " value)) ")")))

(defn generate-ref-block
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (str "(ref" (when-not (string/blank? value) (str " " value)) ")")))