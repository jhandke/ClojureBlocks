(ns clojureblocks.blocks.misc)

(def frequencies-block
  {:type "frequencies_block"
   :message0 "(frequencies %1 coll %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/frequencies"})

(def partition-block
  {:type "partition_block" 
   :message0 "(partition %1 n %2 step %3 coll %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "n"
            :align "RIGHT"}
           {:type "input_statement"
            :name "step"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/partition"})

(def partition-by-block
  {:type "partition_by_block"
   :message0 "(partition-by %1 f %2 coll %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/partition-by"})

(def group-by-block
  {:type "group_by_block"
   :message0 "(group-by %1 f %2 coll %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/group-by"})

(def distinct-block
  {:type "distinct_block"
   :message0 "(distinct %1 coll %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/distinct"})

(def dedupe-block
  {:type "dedupe_block"
   :message0 "(dedupe %1 coll %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/dedupe"})

(def interleave-block
  {:type "interleave_block"
   :message0 "(interleave %1 c1 %2 c2 %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection1"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection2"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/interleave"})

(def interpose-block
  {:type "interpose_block"
   :message0 "(interpose %1 sep %2 coll %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "seperator"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour 150
   :helpUrl "https://clojuredocs.org/clojure.core/interpose"})


(def all
  [frequencies-block
   partition-block
   partition-by-block
   group-by-block
   distinct-block
   dedupe-block
   interleave-block
   interpose-block])