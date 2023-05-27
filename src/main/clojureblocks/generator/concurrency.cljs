(ns clojureblocks.generator.concurrency
  (:require [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-atom-block
  [block]
  (let [value (generator/generate-statement-code block "value")]
    (str "(atom" (when-not (string/blank? value) (str " " value)) ")")))

(defn generate-swap-block
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        function (generator/generate-statement-code block "function")
        arguments (generator/generate-statement-code block "arguments")]
    (str "(swap! " atom " " function (when-not (string/blank? arguments) (str " " arguments)) ")")))

(defn generate-reset-block 
  [block]
  (let [atom (generator/generate-statement-code block "atom")
        value (generator/generate-statement-code block "value")]
    (str "(reset! " atom " " value ")")))