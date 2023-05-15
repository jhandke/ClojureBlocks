(ns clojureblocks.sci-wrapper
  (:require [sci.core :as sci]))

(def sci-options {})
(def sci-context (atom (sci/init sci-options)))

(defn emulate-print 
  "Surrounds `expression` with (pr-str ...) for print-like behaivour."
  [expression] 
  (str "(pr-str " expression ")"))

(defn evaluate 
  "Evaluates a single `expression`."
  [expression] 
  (sci/binding [sci/print-length 12]
    {:expression expression
     :result (sci/eval-string* @sci-context (emulate-print expression))}))

(defn evaluate-with-error 
  "Evaluates a single `expression` and return error."
  [expression]
  (sci/binding [sci/print-length 12]
    (try 
      (hash-map :expression expression
                :result (sci/eval-string* @sci-context (emulate-print expression)))
      (catch js/Error e 
        (hash-map :expression expression
                  :error (. e -message)))
      )))

(defn evaluate-inspection
  "Evaluates a single `expression` for internal use."
  [expression]
  (sci/eval-string* @sci-context expression))

(defn reset-context 
  "Resets the sci-context (like restarting a repl)."
  []
  (swap! sci-context (fn [_] (sci/init sci-options))))