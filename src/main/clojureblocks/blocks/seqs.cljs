(ns clojureblocks.blocks.seqs)

(def colour "#ff4500") ;; orangered

(def count-block
  {:type "count_block",
   :message0 "(count %1 coll: %2 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "collection"
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour,
   :helpUrl "https://clojuredocs.org/clojure.core/count"})

(def empty-q-block 
  {:type "empty_q_block",
   :message0 "(empty? %1 coll: %2 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "collection"
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour,
   :helpUrl "https://clojuredocs.org/clojure.core/empty_q"})

(def cons-block
  {:type "cons_block",
   :message0 "(cons %1 x: %2 coll: %3 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "element"
            :align "RIGHT"},
           {:type "input_statement",
            :name "collection",
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/cons"})

(def conj-block
  {:type "conj_block",
   :message0 "(conj %1 coll: %2 x: %3 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "collection"
            :align "RIGHT"},
           {:type "input_statement",
            :name "element",
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/conj"})

(def into-block
  {:type "into_block"
   :message0 "(into %1 to: %2 from: %3)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "destination"
            :align "RIGHT"}
           {:type "input_statement"
            :name "source"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/into"})

(def top-blocks
  [count-block
   empty-q-block
   cons-block
   conj-block
   into-block])

(def first-block
  {:type "first_block",
   :message0 "(first %1 coll: %2 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "collection"
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour,
   :helpUrl "https://clojuredocs.org/clojure.core/first"})

(def rest-block
  {:type "rest_block",
   :message0 "(rest %1 coll: %2 )",
   :args0 [{:type "input_dummy"}
           {:type "input_statement",
            :name "collection"
            :align "RIGHT"}],
   :previousStatement nil,
   :nextStatement nil,
   :colour colour,
   :helpUrl "https://clojuredocs.org/clojure.core/rest"})

(def take-block
  {:type "take_block"
   :message0 "(take %1 n: %2 coll: %3 )"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "n"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/take"})

(def drop-block
  {:type "drop_block"
   :message0 "(drop %1 n: %2 coll: %3 )"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "n"
            :align "RIGHT"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/drop"})

(def take-while-block
  {:type "take_while_block"
   :message0 "(take-while %1 pred: %2 coll: %3 )"
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
   :helpUrl "https://clojuredocs.org/clojure.core/take-while"})

(def drop-while-block
  {:type "drop_while_block"
   :message0 "(drop-while %1 pred: %2 coll: %3 )"
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
   :helpUrl "https://clojuredocs.org/clojure.core/drop-while"})

(def concat-block 
  {:type "concat_block"
   :message0 "(concat %1 colls: %2 )"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collections"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/concat"})

(def access-blocks
  [first-block
   rest-block
   take-block
   drop-block
   take-while-block
   drop-while-block
   concat-block])

(def sort-block
  {:type "sort_block"
   :message0 "(sort %1 coll: %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/sort"})

(def sort-by-block
  {:type "sort_by_block"
   :message0 "(sort-by %1 pred: %2 coll: %3)"
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
   :helpUrl "https://clojuredocs.org/clojure.core/sort-by"})

(def shuffle-block
  {:type "shuffle_block"
   :message0 "(shuffle %1 coll: %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/shuffle"})

(def reverse-block
  {:type "reverse_block"
   :message0 "(reverse %1 coll: %2)"
   :args0 [{:type "input_dummy"}
           {:type "input_statement"
            :name "collection"
            :align "RIGHT"}]
   :previousStatement nil
   :nextStatement nil
   :colour colour
   :helpUrl "https://clojuredocs.org/clojure.core/reverse"})

(def order-blocks 
  [sort-block
   sort-by-block
   shuffle-block
   reverse-block])

(def all
  [count-block
   empty-q-block
   cons-block 
   conj-block
   into-block
   first-block
   rest-block
   take-block
   drop-block
   take-while-block
   drop-while-block
   concat-block
   sort-block
   sort-by-block
   shuffle-block
   reverse-block])