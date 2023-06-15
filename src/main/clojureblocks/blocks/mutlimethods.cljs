(ns clojureblocks.blocks.mutlimethods)

(def colour "#dfa6f5")

(def defmulti-block
  {:type "defmulti_block"
   :message0 "(defmulti %1 %2 dispatch-fn: %3)"
   :args0 [{:type "field_input"
            :name "function_name"
            :text "my-mm"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "dispatch_function"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :helpUrl "https://clojuredocs.org/clojure.core/defmulti"
   :colour colour})

(def defmethod-block
  {:type "defmethod_block"
   :message0 "(defmethod %1 %2 dispatch-val: %3 fn-tail: %4)"
   :args0 [{:type "field_input"
            :name "function_name"
            :text "my-mm"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "dispatch_value"
            :align "RIGHT"}
           {:type "input_statement"
            :name "function_tail"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :helpUrl "https://clojuredocs.org/clojure.core/defmethod"
   :colour colour})

(def all
  [defmulti-block
   defmethod-block])