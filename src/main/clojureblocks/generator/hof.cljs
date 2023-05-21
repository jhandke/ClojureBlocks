(ns clojureblocks.generator.hof
  (:require [clojureblocks.generator.clojure :as generator]
            [clojure.string :as string]))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [block]
  (str "(map "
       (string/join " "
                    (generator/generate-statement-code block "hof-map-function"))
       " "
       (string/join " "
                    (generator/generate-statement-code block "hof-map-collection"))
       ")"))