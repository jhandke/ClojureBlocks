(ns clojureblocks.code-formatter
  (:require [zprint.core :as zp]))

(def zprint-options {:style :rod
                     :width 40
                     :parse-string-all? true
                     :comment {:inline? true}})

(defn format-code
  [code]
  code
  ; do nothing for now... (zp/zprint-str code zprint-options)
  )