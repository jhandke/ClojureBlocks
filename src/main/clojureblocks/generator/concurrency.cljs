(ns clojureblocks.generator.concurrency
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-atom-block
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (generator/expression "atom" value)))

(defn generate-deref-block
  [block]
  (let [atom (generator/generate-statement-code block "atom")]
    (generator/expression "deref" atom)))

(defn generate-swap-block
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        function (generator/generate-statement-code block "function")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "swap!" atom function arguments)))

(defn generate-reset-block
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        value (generator/generate-statement-code block "value")]
    (generator/expression "reset!" atom value)))