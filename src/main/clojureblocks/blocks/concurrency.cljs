(ns clojureblocks.blocks.concurrency)

(def colour "#FF0000")

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

(def agent-block 
  {:type "agent_block"
   :message0 "(agent %1 value %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/agent"})

(def ref-block
  {:type "ref_block"
   :message0 "(ref %1 value %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/ref"})

(def all 
  [atom-block
   agent-block
   ref-block])