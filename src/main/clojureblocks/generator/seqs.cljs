(ns clojureblocks.generator.seqs
  (:require [clojureblocks.generator.clojure :as generator]
            [clojure.string :as string]))

(defn generate-list-block
  "Generates the code for a list block: `(e1 e2 ...)`."
  [block]
  (str "("
       (string/join " "
                    (generator/generate-statement-code block "list-items"))
       ")"))

(defn generate-vector-block
  "Generates the code for a vector block: `[e1 e2 ...]`."
  [block]
  (str "["
       (string/join " "
                    (generator/generate-statement-code block "vector-items"))
       "]"))

(defn generate-map-block
  "Generates the code for a map block: `{:a 1 :b 42 ...}`."
  [block]
  (str "{"
       (string/join " "
                    (generator/generate-statement-code block "map-items"))
       "}"))