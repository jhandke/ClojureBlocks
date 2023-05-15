(ns clojureblocks.evaluator
  (:require [clojure.string :as string]
            [clojureblocks.sci-wrapper :as sci]))

(defn split-and-evaluate
  "Splits `code` into lines and returns formatted evaluation results"
  [code]
  (let [expressions (string/split-lines code)]
    (map sci/evaluate-with-error expressions)))

(defn evaluate-internal
  "Evaluates single expression for internal use"
  [expression]
  (sci/evaluate-inspection expression))

(defn reset-evaluation-context
  "Resets the evaluation context."
  []
  (sci/reset-context))