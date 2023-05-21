(ns clojureblocks.generator.hof
  (:require ["blockly" :as blockl]
            [clojureblocks.generator.clojure :as generator]
            [clojure.string :as string]))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [^blockly/Block block]
  (let [function (string/join " " (generator/generate-statement-code block "hof-map-function"))
        collection (string/join " " (generator/generate-statement-code block "hof-map-collection"))]
    (str "(map " function " " collection ")")))