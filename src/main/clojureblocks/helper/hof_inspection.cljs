(ns clojureblocks.helper.hof-inspection
  (:require [clojureblocks.evaluator.evaluate :as evaluator]
            [clojureblocks.generator.clojure :as generator]))

(def number-previews (atom 5))

(defn map-hof-inspection
  [block]
  (let [expression (get
                    (evaluator/evaluate-single
                     (str "'" (.blockToCode generator/clojure-generator block)))
                    :result)
        pred (fnext expression)
        coll (last expression)
        inspection-elements (get (evaluator/evaluate-single (str "(take " @number-previews " " coll ")")) :result)]
    (map
     (fn [element]
       (let [res (get (evaluator/evaluate-single (str "(" pred " " element ")")) :result)]
         (str "(" pred " " element ") => " res)))
     inspection-elements)))