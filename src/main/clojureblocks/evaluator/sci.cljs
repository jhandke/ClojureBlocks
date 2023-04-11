(ns clojureblocks.evaluator.sci
  (:require [sci.core :as sci]))

(def sci-options {})
(def sci-context (atom (sci/init sci-options)))

(defn evaluate [expression]
  (sci/eval-string* @sci-context expression))

(defn reset-context []
  (swap! sci-context #(sci/init sci-options)))