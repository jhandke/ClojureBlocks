(ns clojureblocks.hof-inspection
  (:require [clojureblocks.evaluator :as evaluator]
            [clojureblocks.generator.generator :as generator]))

(def number-previews (atom 20))

(defn format-output
  [lines amount]
  (if (< @number-previews amount)
    (conj (vec lines) "...")
    lines))

(defn map-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " @number-previews " " coll ")"))]
    (format-output
     (map
      (fn [element]
        (let [res (evaluator/evaluate-internal (str "(" pred " " element ")"))]
          (str "(" pred " " element ") => " res)))
      inspection-elements)
     coll-size)))

(defn filter-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " @number-previews " " coll ")"))] 
    (format-output (map
                    (fn [element]
                      (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))]
                        (str "(" pred " " element ") => " result)))
                    inspection-elements)
                   coll-size)))

(defn reduce-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " @number-previews " " coll ")"))]
    (format-output
     (loop [elements inspection-elements
            last-result nil
            result []]
       (if (empty? elements)
         result
         (let [current-element (first elements)
               current-result (evaluator/evaluate-internal (str "(" pred " " current-element " " last-result ")"))
               last-result-presentation (when last-result (str " " last-result))
               current-line (str "(" pred last-result-presentation " " current-element ") => " current-result)]
           (recur (rest elements) current-result (conj result current-line)))))
     coll-size)))