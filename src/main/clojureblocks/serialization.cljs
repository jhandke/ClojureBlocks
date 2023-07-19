(ns clojureblocks.serialization
  (:require ["blockly" :as blockly]
            [clojureblocks.storage :as storage]))

(def storage-key "clojureblocks-ws")
(def theme-key "clojureblocks-theme")
(def settings-key "clojureblocks-settings")

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

(defn save-settings
  "Saves the `settings`-map to some storage"
  [settings]
  (storage/set-item! settings-key (.stringify js/JSON (clj->js settings))))

(defn load-settings
  "Loads the settings from storage"
  []
  (js->clj (.parse js/JSON (storage/get-item settings-key)) :keywordize-keys true))