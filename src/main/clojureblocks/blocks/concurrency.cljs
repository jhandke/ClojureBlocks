(ns clojureblocks.blocks.concurrency)

(def colour "#1e90ff") ;; dodgerblue

(def atom-block
  {:type "atom_block"
   :message0 "(atom %1 value %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/atom"})

(def deref-block
  {:type "deref_block"
   :message0 "(deref %1 atom %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "atom"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/deref"})

(def swap-block 
  {:type "swap_block"
   :message0 "(swap! %1 atom %2 fn %3 args %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "atom"
            :align "RIGHT"}
           {:type "input_statement"
            :name "function"
            :align "RIGHT"}
           {:type "input_statement"
            :name "arguments"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/swap!"})

(def reset-block 
  {:type "reset_block"
   :message0 "(reset! %1 atom %2 value %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "atom"
            :align "RIGHT"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/reset!"})

(def all 
  [atom-block
   deref-block
   swap-block
   reset-block])