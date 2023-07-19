(ns clojureblocks.storage)

(defn set-item!
  "Sets `key` in browser's localStorage to `val`."
  [key val]
  (.setItem (.-localStorage js/window) key val))

(defn get-item
  "Returns value of `key` from browser's localStorage."
  [key]
  (.getItem (.-localStorage js/window) key))