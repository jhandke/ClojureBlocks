(ns clojureblocks.helper.modal-view
  (:require ["/js-extensions/PreviewModal.js" :default PreviewModal]
            [clojure.string :as string]))

(def modal (atom nil))

(defn init [workspace]
  (println "we innit yea" workspace)
  (swap! modal (fn [_] (new PreviewModal "Block inspection" workspace))))

(defn display [content]
  (println modal)
  (.init @modal content)
  (.show @modal))

(defn display-lines [lines]
  (let [content (string/join "\n" lines)]
    (display content)))