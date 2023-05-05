(ns clojureblocks.helper.modal-view
  (:require ["/js/PreviewModal.js" :default PreviewModal]
            [clojure.string :as string]))

(def modal (atom nil))

(defn init [workspace]
  (swap! modal (fn [_] (new PreviewModal "Block inspection" workspace))))

(defn display [content]
  (.init @modal content)
  (.show @modal))

(defn display-lines [lines]
  (let [content (string/join "\n" lines)]
    (display content)))