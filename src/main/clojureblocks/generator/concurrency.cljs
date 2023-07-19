(ns clojureblocks.generator.concurrency
  (:require [clojureblocks.generator.clojure :as generator]))

(defn generate-atom-block
  "Generates the code for an atom block: `(atom value)`"
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (generator/expression "atom" value)))

(defn generate-deref-block
  "Generates the code for a deref block: `(deref ref)`"
  [block]
  (let [atom (generator/generate-statement-code block "atom")]
    (generator/expression "deref" atom)))

(defn generate-swap-block
  "Generates the code for a swap block: `(swap! atom f x y & args)`"
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        function (generator/generate-statement-code block "function")
        arguments (generator/generate-statement-code block "arguments")]
    (generator/expression "swap!" atom function arguments)))

(defn generate-reset-block
  "Generates the code for a reset block: (reset! atom value)"
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        value (generator/generate-statement-code block "value")]
    (generator/expression "reset!" atom value)))