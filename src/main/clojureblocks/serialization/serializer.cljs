(ns clojureblocks.serialization.serializer
  (:require ["blockly" :as blockly]
            [clojureblocks.serialization.localstorage :as localstorage]))

(def storage-key "clojureblocks-ws")
(def theme-key "clojureblocks-theme")

(defn save-workspace 
  "Saves the `workspace` to some storage"
  [workspace]
  (let [data (.. blockly -serialization -workspaces (save workspace))]
    (localstorage/set-item! storage-key (.stringify js/JSON data))))

(defn load-workspace 
  "Loads the saved workspace"
  [] 
  (let [data (localstorage/get-item storage-key)]
    (when-not (nil? data)
      (.parse js/JSON data))))

(defn save-theme
  "Saves the theme `id` to some storage"
  [id] 
  (localstorage/set-item! theme-key (.stringify js/JSON id)))

(defn load-theme
  "Loads the theme id from storage"
  []
  (let [theme-id (localstorage/get-item theme-key)
        parsed-id (.parse js/JSON theme-id)] 
    (when-not (nil? parsed-id)
      (get {":light" :light, ":dark" :dark} parsed-id :unreachable))))