(ns clojureblocks.generator.clojure
  (:require ["blockly" :as blockly]
            [clojure.string :as string]
            [clojureblocks.generator.literals :as literals]))

(def clojure-generator (blockly/Generator. "clojure"))

(defn generate-statement-code
  "Generates the code from `block`'s statement input with the name `identifier`."
  [block identifier]
  (loop [child (.getInputTargetBlock ^blockly/Block block identifier)
         statements []]
    (if child
      (let [statement (.blockToCode clojure-generator child)]
        (recur
         (.getNextBlock ^blockly/Block child)
         (conj statements statement)))
      statements)))

(defn generate-list-block
  "Generates the code for a list block: `(e1 e2 ...)`."
  [block]
  (str "("
       (string/join " "
                    (generate-statement-code block "list-items"))
       ")"))

(defn generate-vector-block
  "Generates the code for a vector block: `[e1 e2 ...]`."
  [block]
  (str "["
       (string/join " "
                    (generate-statement-code block "vector-items"))
       "]"))

(defn generate-map-block
  "Generates the code for a map block: `{:a 1 :b 42 ...}`."
  [block]
  (str "{"
       (string/join " "
                    (generate-statement-code block "map-items"))
       "}"))

(defn generate-hof-map-block
  "Generates the code for a hof map block: `(map pred coll)`."
  [block]
  (str "(map "
       (string/join " "
                    (generate-statement-code block "hof-map-function"))
       " "
       (string/join " "
                    (generate-statement-code block "hof-map-collection"))
       ")"))

(defn generate-defn-block
  "Generates the code for a defn block: `(defn fn-name [args] body)`"
  [^blockly/Block block]
  (str "(defn "
       (.getFieldValue block "function_name")
       " ["
       (string/join " "
                    (generate-statement-code block "function_arguments"))
       "] "
       (string/join " "
                    (generate-statement-code block "function_body"))
       ")"))

(defn generate-fn-call-block
  "Generates the code for a function call block: `(function arg1 arg2 ...)`"
  [^blockly/Block block]
  (let [arguments (generate-statement-code block "function_arguments")]
    (str "("
         (.getFieldValue block "function_name")
         (if (empty? arguments)
           ")"
           (str " " (string/join " " arguments) ")")))))

(defn generate-if-block
  "Generates the code for an if-block: `(if test then else)`"
  [^blockly/Block block]
  (let [condition (generate-statement-code block "condition")
        then-body (generate-statement-code block "then")
        else-body (generate-statement-code block "else")]
    (str "(if "
         (string/join " " condition)
         " "
         (string/join " " then-body)
         " "
         (string/join " " else-body)
         ")")))

(defn generate-when-block
  "Generates the code for a when-block: `(when test body)`"
  [^blockly/Block block]
  (let [condition (generate-statement-code block "condition")
        body (generate-statement-code block "body")]
    (str "(when "
         (string/join " " condition)
         " "
         (string/join " " body)
         ")")))

(set! (.. clojure-generator -number-block) literals/generate-number-block)
(set! (.. clojure-generator -string-block) literals/generate-string-block)
(set! (.. clojure-generator -keyword-block) literals/generate-keyword-block)
(set! (.. clojure-generator -symbol-block) literals/generate-symbol-block)
(set! (.. clojure-generator -list-block) generate-list-block)
(set! (.. clojure-generator -vector-block) generate-vector-block)
(set! (.. clojure-generator -map-block) generate-map-block)
(set! (.. clojure-generator -hof-map-block) generate-hof-map-block)
(set! (.. clojure-generator -defn-block) generate-defn-block)
(set! (.. clojure-generator -fn-call-block) generate-fn-call-block)
(set! (.. clojure-generator -if-block) generate-if-block)
(set! (.. clojure-generator -when-block) generate-when-block)