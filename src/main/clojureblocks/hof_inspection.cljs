(ns clojureblocks.hof-inspection
  (:require [clojureblocks.evaluator :as evaluator]
            [clojureblocks.generator :as generator]))

(def number-previews (atom 5))

(defn map-hof-inspection
  [block]
  (let [code (.blockToCode generator/clojure-generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        inspection-elements (evaluator/evaluate-internal (str "(take " @number-previews " " coll ")"))]
    (map
     (fn [element]
       (let [res (evaluator/evaluate-internal (str "(" pred " " element ")"))]
         (str "(" pred " " element ") => " res)))
     inspection-elements)))