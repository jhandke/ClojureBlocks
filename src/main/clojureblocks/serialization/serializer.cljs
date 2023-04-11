(ns clojureblocks.serialization.serializer
  (:require ["blockly" :as blockly]
            [clojureblocks.serialization.localstorage :as localstorage]
            [clojureblocks.serialization.serializer :as serialization]))

(def storageKey "clojureblocks-ws")

(defn save-workspace [workspace]
  (let [data (.. blockly -serialization -workspaces (save workspace))]
    (localstorage/set-item! storageKey (.stringify js/JSON data))))

(defn load-workspace [workspace]
  (let [data (localstorage/get-item storageKey)]
    (when-not (nil? data)
      (let [parsedData (.parse js/JSON data)]
        (.. blockly -serialization -workspaces (load parsedData workspace false))))))