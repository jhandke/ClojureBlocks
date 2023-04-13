(ns clojureblocks.evaluator.evaluate
  (:require [clojure.string :as string]
            [clojureblocks.evaluator.sci :as sci]))

(defn format-results [expression]
  {:expression expression
   :result (sci/evaluate expression)})

(defn split-and-evaluate [code]
  (map format-results (string/split-lines code)))

(defn evaluate-single [expression]
  (format-results expression))