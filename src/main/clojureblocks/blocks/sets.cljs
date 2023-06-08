(ns clojureblocks.blocks.sets)

(def colour "#00dd00") ;; lime

(def union-block
  {:type "union_block"
   :message0 "(union %1 s1 %2 s2 %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "set1"
            :align "RIGHT"}
           {:type "input_statement"
            :name "set2"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.set/union"})

(def intersection-block
  {:type "intersection_block"
   :message0 "(intersection %1 s1 %2 s2 %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "set1"
            :align "RIGHT"}
           {:type "input_statement"
            :name "set2"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.set/intersection"})

(def difference-block
  {:type "difference_block"
   :message0 "(difference %1 s1 %2 s2 %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "set1"
            :align "RIGHT"}
           {:type "input_statement"
            :name "set2"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.set/difference"})



(def all
  [union-block
   intersection-block
   difference-block])