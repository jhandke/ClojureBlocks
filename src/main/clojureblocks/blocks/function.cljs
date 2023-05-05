(ns clojureblocks.blocks.function)

(def defn-block 
  {:type "defn_block"
   :message0 "(defn %1 %2 args [ %3 ] %4 body %5)"
   :args0 [{:type "field_input"
            :name "function_name"
            :text "my-fn"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "function_arguments"}
           {:type "input_dummy"
            :align "RIGHT"}
           {:type "input_statement"
            :name "function_body"}]
   :colour 45
   :tooltip "Defines a function. Short for (def my-fn (fn args body))."
   :helpUrl "https://clojuredocs.org/clojure.core/defn"})

(def fn-call-block
  {:type "fn_call_block"
   :lastDummyAlign0 "RIGHT"
   :message0 "(call %1 %2 args %3 )"
   :args0 [{:type "field_input"
            :name "function_name"
            :text "my-fn"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "function_arguments"}]
   :previousStatement nil
   :nextStatement nil
   :colour 180
   :tooltip "Calls a function with the given arguments."})