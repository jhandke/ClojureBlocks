(ns clojureblocks.contextmenu
  (:require ["blockly" :refer (ContextMenuRegistry)]
            [clojureblocks.hof-inspection :as inspection]
            [clojureblocks.modal-view :as modal-view]))

(defmulti context-menu-action (fn [scope] (.. scope -block -type)))

(defmethod context-menu-action "hof_map_block"
  [scope]
  (modal-view/display-lines (inspection/map-inspection (. scope -block))))

(defmethod context-menu-action "hof_reduce_block"
  [scope]
  (modal-view/display-lines (inspection/reduce-inspection (. scope -block))))

(defmethod context-menu-action "hof_filter_block"
  [scope]
  (modal-view/display-lines (inspection/filter-inspection (. scope -block))))

(defn precondition [scope]
  (if (contains? #{"hof_map_block" "hof_reduce_block" "hof_filter_block"} (.. scope -block -type))
    "enabled"
    "disabled"))

(def hof-inspection-contextmenu-item
  {:id "hof-inspection"
   :callback (fn [scope]
               (context-menu-action scope))
   :scopeType (. (. ContextMenuRegistry -ScopeType) -BLOCK)
   :displayText (fn [] "Inspect")
   :preconditionFn precondition
   :weight 0})

(defn register-contextmenu []
  (.. ContextMenuRegistry -registry
      (register (clj->js hof-inspection-contextmenu-item))))