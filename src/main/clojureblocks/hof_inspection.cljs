(ns clojureblocks.hof-inspection
  (:require [clojure.string :as string]
            [clojureblocks.evaluator :as evaluator]
            [clojureblocks.generator.generator :as generator]
            [clojureblocks.hof-inspection :as inspection]))

(def preview-length (atom nil))

(defn map-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        num-previews @preview-length
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str (string/join "\n"
                      (map
                       (fn [element]
                         (let [res (evaluator/evaluate-internal (str "(" pred " " element ")"))]
                           (str "(" pred " " element ") ;; => " res)))
                       inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn filter-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str (string/join "\n" (map
                            (fn [element]
                              (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))
                                    comment (when-not result " (removed)")]
                                (str "(" pred " " element ") ; => " result comment)))
                            inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn remove-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        pred (fnext expression)
        coll (last expression)
        coll-size (evaluator/evaluate-internal (str "(count " coll ")"))
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str (string/join "\n" (map
                            (fn [element]
                              (let [result (evaluator/evaluate-internal (str "(" pred " " element ")"))
                                    comment (when result " (removed)")]
                                (str "(" pred " " element ") ; => " result comment)))
                            inspection-elements))
         (when (> coll-size num-previews)
           "\n; ..."))))

(defn reduce-steps
  [elements start-value pred]
  (loop [elements elements
         last-result start-value
         result []]
    (if (empty? elements)
      (string/join "\n" result)
      (let [current-element (first elements)
            current-result (evaluator/evaluate-internal (str "(" pred " " current-element " " last-result ")"))
            last-result-presentation (when last-result (str " " last-result))
            current-line (str "(" pred last-result-presentation " " current-element ") ;; => " current-result)]
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
        num-previews @preview-length
        inspection-elements (evaluator/evaluate-internal (str "(take " num-previews " " coll ")"))]
    (str
     (if value
       (reduce-steps inspection-elements value pred)
       (reduce-steps (rest inspection-elements) (first inspection-elements) pred))
     (when (> coll-size num-previews)
       "\n; ..."))))

(defn partial-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        function (fnext expression)
        partial-args (rest (rest expression))]
    (str "(fn [& args] (apply " function " " (string/join " " partial-args) " args))")))

(defn apply-rows
  [coll prefix-length]
  (string/join
   (apply str "\n" (repeat prefix-length " "))
   (map
    (fn [row] (string/join " " row))
    coll)))

(defn apply-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        function (fnext expression)
        args (rest (rest (butlast expression)))
        coll (last expression)
        evaluated-args (map #(evaluator/evaluate-internal (str %)) args)
        evaluated-coll (evaluator/evaluate-internal (str "(partition 20 " coll ")"))
        prefix (str "(" function (when-not (empty? args) (str " " (string/join " " evaluated-args))) " ")
        prefix-length (count prefix)]
    (str prefix (apply-rows evaluated-coll prefix-length) ")")))

(defn juxt-inspection
  [block]
  (let [code (.blockToCode generator/generator block)
        expression (evaluator/evaluate-internal
                    (str "'" code))
        functions (next expression)]
    (str "(fn [& args] [" (string/join "\n              " (map #(str "(apply " % " args)") functions)) "])")))