(ns clojureblocks.code-formatter
  (:require [zprint.core :as zp]))

(def zprint-options {:style :rod
                     :width 40
                     :parse-string-all? true
                     :parse {:interpose "\n\n"}
                     :comment {:inline? true}})

(defn format-code
  [code]
  (zp/zprint-str code zprint-options))