(ns clojureblocks.blocks.control)

(def colour "#08c49c") ;; aqua

(def if-block
  {:type "if_block"
   :message0 "if %1 then %2 else %3"
   :args0 [{:type "input_statement"
            :name "condition"}
           {:type "input_statement"
            :name "then"}
           {:type "input_statement"
            :name "else"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/if"})

(def when-block
  {:type "when_block"
   :message0 "when %1 then %2"
   :args0 [{:type "input_statement"
            :name "condition"}
           {:type "input_statement"
            :name "body"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/when"})

(def all
  [if-block
   when-block])
