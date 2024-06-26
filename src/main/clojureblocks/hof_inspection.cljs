(ns clojureblocks.hof-inspection
  (:require [clojure.string :as string]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.generator.generator :as generator]
            [clojureblocks.hof-inspection :as inspection]))

(def preview-length (atom nil))

(defn map-inspection
  "Creates the inspection text for a given map `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        evaluated-expression (evaluator/evaluate-internal
                              (str expression)
                              true)
        pred (fnext expression)
        coll (last expression)
        num-previews @preview-length
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))] 
    (str expression "\n; => " evaluated-expression "\n\n"
         (string/join "\n"
                      (map
                       (fn [element]
                         (let [res (evaluator/evaluate-internal (str "(" pred " " element ")"))]
                           (str "(" pred " " element ") ; => " res)))
                       inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn filter-inspection 
  "Creates the inspection text for a given filter `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        evaluated-expression (evaluator/evaluate-internal
                              (str expression)
                              true)
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str expression "\n; => " evaluated-expression "\n\n"
         (string/join "\n" (map
                            (fn [element]
                              (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))
                                    comment (when-not result " (removed)")]
                                (str "(" pred " " element ") ; => " result comment)))
                            inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn remove-inspection 
  "Creates the inspection text for a given remove `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        evaluated-expression (evaluator/evaluate-internal
                              (str expression)
                              true)
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str expression "\n; => " evaluated-expression "\n\n"
         (string/join "\n" (map
                            (fn [element]
                              (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))
                                    comment (when result " (removed)")]
                                (str "(" pred " " element ") ; => " result comment)))
                            inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn reduce-steps
  "Creates the steps for a reduce inspection"
  [elements start-value pred]
  (loop [elements elements
         last-result start-value
         result []]
    (if (empty? elements)
      (string/join "\n" result)
      (let [current-element (first elements)
            current-result (evaluator/evaluate-internal (str "(" pred " " current-element " " last-result ")"))
            last-result-presentation (when last-result (str " " last-result))
            current-line (str "(" pred last-result-presentation " " current-element ") ; => " current-result)]
        (recur (rest elements) current-result (conj result current-line))))))

(defn reduce-inspection 
  "Creates the inspection text for a given reduce `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        evaluated-expression (evaluator/evaluate-internal
                              (str expression)
                              true)
        pred (fnext expression)
        coll (last expression)
        value (when-not (= coll (second (next expression)))
                (second (next expression)))
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str expression "\n; => " evaluated-expression "\n\n"
         (if value
           (reduce-steps inspection-elements value pred)
           (reduce-steps (rest inspection-elements) (first inspection-elements) pred))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn partial-inspection
  "Creates the inspection text for a given partial `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        function (fnext expression)
        partial-args (rest (rest expression))]
    (str expression "\n\n"
         "(fn [& args] (apply " function " " (string/join " " partial-args) " args))")))

(defn string-representation
  "Replaces quotes inside `maybe-string` with escaped quotes"
  [maybe-string]
  (if (string? maybe-string)
    (str "\"" maybe-string "\"") 
    maybe-string))

(defn apply-rows
  "Creates the rows with padding for the apply inspection"
  [coll prefix-length] 
  (string/join
   (apply str "\n" (repeat prefix-length " "))
   (map
    (fn [row] (string/join " " 
                           (map string-representation row)))
    coll)))

(defn apply-inspection 
  "Creates the inspection text for a given apply `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        evaluated-expression (evaluator/evaluate-internal
                              (str expression)
                              true)
        function (fnext expression)
        args (rest (rest (butlast expression)))
        coll (last expression)
        evaluated-coll (evaluator/evaluate-internal (str "(partition-all 20 " coll ")"))
        prefix (str "(" function (when-not (empty? args) (str " " (string/join " " (map string-representation args)))) " ")
        prefix-length (count prefix)]
    (str expression "\n; => " evaluated-expression "\n\n"
         prefix (apply-rows evaluated-coll prefix-length) ")")))

(defn juxt-inspection
  "Creates the inspection text with padding for a given juxt `block`"
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        functions (next expression)]
    (str expression "\n\n"
         "(fn [& args] [" (string/join "\n              " (map #(str "(apply " % " args)") functions)) "])")))