(ns clojureblocks.hof-inspection
  (:require [clojureblocks.evaluator :as evaluator]
            [clojureblocks.generator.generator :as generator]
            [clojureblocks.hof-inspection :as inspection]
            [clojure.string :as string]))

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
                      (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))
                            comment (when-not result " ;; removed")]
                        (str "(" pred " " element ") => " result comment)))
                    inspection-elements)
                   coll-size)))

(defn reduce-steps
  [elements start-value pred]
  (loop [elements elements
         last-result start-value
         result []]
    (if (empty? elements)
      result
      (let [current-element (first elements)
            current-result (evaluator/evaluate-internal (str "(" pred " " current-element " " last-result ")"))
            last-result-presentation (when last-result (str " " last-result))
            current-line (str "(" pred last-result-presentation " " current-element ") => " current-result)]
        (recur (rest elements) current-result (conj result current-line))))))

(defn reduce-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        value (when-not (= coll (second (next expression)))
                (second (next expression)))
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " @number-previews " " coll ")"))]
    (format-output
     (if value
       (reduce-steps inspection-elements value pred)
       (reduce-steps (rest inspection-elements) (first inspection-elements) pred))
     coll-size)))

(defn partial-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        function (fnext expression)
        args (rest (rest expression))]
    (str "(fn [x] (" function " " (string/join " " args) " x))")))

(defn apply-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        function (fnext expression)
        args (rest (rest expression))
        evaluated-args (map #(evaluator/evaluate-internal (str %)) args)]
    (str "(" function " " (string/join " " (flatten evaluated-args)) ")")))

(defn juxt-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        functions (next expression)]
    (str "(fn [x] [" (string/join " " (map #(str "(" % " x)") functions)) "])")))