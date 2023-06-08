(ns clojureblocks.blocks.function)

(def colour "#006400") ;; darkgreen

(def defn-block
  {:type "defn_block"
   :message0 "(defn %1 %2 args: [ %3 ] %4 body: %5)"
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
   :colour colour
   :tooltip "Defines a function. Short for (def my-fn (fn args body))."
   :helpUrl "https://clojuredocs.org/clojure.core/defn"})

(def fn-block
  {:type "fn_block"
   :message0 "(fn %1 args: [ %2 ] %3 body: %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "args"
            :align "RIGHT"}
           {:type "input_dummy"
            :align "RIGHT"}
           {:type "input_statement"
            :name "body"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/fn"})

(def fn-call-block
  {:type "fn_call_block"
   :message0 "(%1 %2 args: %3 )"
   :args0 [{:type "field_input"
            :name "function_name"
            :text "my-fn"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "function_arguments"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :tooltip "Calls a function with the given arguments."})

(def def-block
  {:type "def_block"
   :message0 "(def %1 %2 body %3)"
   :args0 [{:type "field_input"
            :name "symbol_name"
            :text "my-val"}
           {:type "input_dummy"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/def"})

(def all
  [defn-block
   fn-block
   fn-call-block
   def-block])