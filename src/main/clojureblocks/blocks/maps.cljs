(ns clojureblocks.blocks.maps)

(def colour 180)

(def assoc-block
  {:type "assoc_block"
   :message0 "(assoc %1 map %2 key %3 value %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "map"
            :align "RIGHT"}
           {:type "input_statement"
            :name "key"
            :align "RIGHT"}
           {:type "input_statement"
            :name "value"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/assoc"})

(def dissoc-block
  {:type "dissoc_block"
   :message0 "(dissoc %1 map %2 key %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "map"
            :align "RIGHT"}
           {:type "input_statement"
            :name "key"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/dissoc"})

(def get-block
  {:type "get_block"
   :message0 "(get %1 map %2 key %3 default %4)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "map"
            :align "RIGHT"}
           {:type "input_statement"
            :name "key"
            :align "RIGHT"}
           {:type "input_statement"
            :name "default"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/get"})

(def all 
  [assoc-block
   dissoc-block
   get-block])

