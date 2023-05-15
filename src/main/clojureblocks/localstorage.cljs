(ns clojureblocks.localstorage)

(comment "Code stolen from https://gist.github.com/daveliepmann/cf923140702c8b1de301")

(defn set-item!
  "Set `key` in browser's localStorage to `val`."
  [key val]
  (.setItem (.-localStorage js/window) key val))

(defn get-item
  "Returns value of `key` from browser's localStorage."
  [key]
  (.getItem (.-localStorage js/window) key))