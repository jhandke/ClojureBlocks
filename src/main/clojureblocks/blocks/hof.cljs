(ns clojureblocks.blocks.hof)

(def colour "#ff1493") ;; deeppink

(def hof-map-block
  {:type "hof_map_block"
   :message0 "(map fn %1 seq %2 )"
   :args0 [{:type "input_statement"
            :name "hof-map-function"
            :align "RIGHT"}
           {:type "input_statement"
            :name "hof-map-collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/map"})

(def hof-filter-block
  {:type "hof_filter_block"
   :message0 "(filter %1 fn %2 coll %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/filter"})

(def hof-remove-block
  {:type "hof_remove_block"
   :message0 "(remove %1 fn %2 coll %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/remove"})

(def hof-reduce-block
  {:type "hof_reduce_block"
   :message0 "(reduce %1 fn %2 val? %3 coll %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/reduce"})

(def hof-partial-block
  {:type "hof_partial_block"
   :message0 "(partial %1 fn %2 args %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "arguments"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/partial"})

(def hof-apply-block
  {:type "hof_apply_block"
   :message0 "(apply %1 fn %2 args %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "pred"
            :align "RIGHT"}
           {:type "input_statement"
            :name "arguments"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/apply"})

(def hof-juxt-block
  {:type "hof_juxt_block"
   :message0 "(juxt %1 fns %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "preds"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/juxt"})

(def all
  [hof-map-block
   hof-filter-block
   hof-remove-block
   hof-reduce-block
   hof-partial-block
   hof-apply-block
   hof-juxt-block])