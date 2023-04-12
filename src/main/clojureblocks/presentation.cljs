(ns clojureblocks.presentation
  (:require [clojure.string :as string]))

;; {$linenumber => {:expression "(+ 3 4)"
;;                  :result 7, 
;;                  :error nil}, 
;;  :evaluated false}
(def output-state (atom {}))

(defn init-from-code-lines [lines]
  (string/split-lines lines))

(defn generate-state [lines]
  (map-indexed (fn [index line]
                 {:a "asdf"}))
  lines)