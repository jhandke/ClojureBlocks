(ns clojureblocks.contextmenu
  (:require ["blockly" :refer (ContextMenuRegistry)]
            [clojureblocks.hof-inspection :as inspection]
            [clojureblocks.modal-view :as modal-view]))

(defn block-inspection [scope]
  (cond ;; multimethods!!!
    (= "hof_map_block" (.. scope -block -type))
    (modal-view/display-lines (inspection/map-hof-inspection (. scope -block)))))

(defn precondition [scope]
  (cond
    (= "hof_map_block" (.. scope -block -type))
    "enabled"
    :else
    "disabled"))

(def hof-inspection-contextmenu-item
  {:id "hof-inspection"
   :callback (fn [scope]
               (block-inspection scope))
   :scopeType (. (. ContextMenuRegistry -ScopeType) -BLOCK)
   :displayText (fn [] "Inspect")
   :preconditionFn precondition
   :weight 0})

(defn register-contextmenu []
  (.. ContextMenuRegistry -registry
      (register (clj->js hof-inspection-contextmenu-item))))