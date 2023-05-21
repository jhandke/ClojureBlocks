(ns clojureblocks.generator.function
  (:require ["blockly" :as blockly]
            [clojure.string :as string]
            [clojureblocks.generator.clojure :as generator]))

(defn generate-defn-block
  "Generates the code for a defn block: `(defn fn-name [args] body)`"
  [^blockly/Block block]
  (str "(defn "
       (.getFieldValue block "function_name")
       " ["
       (string/join " "
                    (generator/generate-statement-code block "function_arguments"))
       "] "
       (string/join " "
                    (generator/generate-statement-code block "function_body"))
       ")"))

(defn generate-fn-call-block
  "Generates the code for a function call block: `(function arg1 arg2 ...)`"
  [^blockly/Block block]
  (let [arguments (generator/generate-statement-code block "function_arguments")]
    (str "("
         (.getFieldValue block "function_name")
         (if (empty? arguments)
           ")"
           (str " " (string/join " " arguments) ")")))))