(ns clojureblocks.serialization
  (:require ["blockly" :as blockly]
            [clojureblocks.storage :as storage]))

(def storage-key "clojureblocks-ws")
(def theme-key "clojureblocks-theme")

(defn serialize
  "Serializes the `workspace`."
  [workspace]
  (.stringify js/JSON
              (.. blockly -serialization -workspaces (save workspace))))

(defn save-workspace
  "Saves the `workspace-data` to some storage"
  [workspace-data]
  (storage/set-item! storage-key
                          (serialize workspace-data)))

(defn load-workspace
  "Loads the saved workspace"
  []
  (let [data (storage/get-item storage-key)]
    (when-not (nil? data)
      (.parse js/JSON data))))

(defn save-theme
  "Saves the theme `id` to some storage"
  [id]
  (storage/set-item! theme-key (.stringify js/JSON id)))

(defn load-theme
  "Loads the theme id from storage"
  []
  (let [theme-id (storage/get-item theme-key)
        parsed-id (.parse js/JSON theme-id)]
    (when-not (nil? parsed-id)
      (get {":light" :light, ":dark" :dark} parsed-id :unreachable))))