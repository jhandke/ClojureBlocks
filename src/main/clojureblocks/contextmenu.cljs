(ns clojureblocks.contextmenu
  (:require ["blockly" :refer (ContextMenuRegistry)]
            [clojureblocks.hof-inspection :as inspection]
            [clojureblocks.modal-preview :as modal-preview]))

(defmulti context-menu-action (fn [scope] (.. scope -block -type)))

(defmethod context-menu-action "hof_map_block"
  [scope]
  (modal-preview/display (inspection/map-inspection (. scope -block))))

(defmethod context-menu-action "hof_reduce_block"
  [scope]
  (modal-preview/display (inspection/reduce-inspection (. scope -block))))

(defmethod context-menu-action "hof_filter_block"
  [scope]
  (modal-preview/display (inspection/filter-inspection (. scope -block))))

(defmethod context-menu-action "hof_remove_block"
  [scope]
  (modal-preview/display (inspection/remove-inspection (. scope -block))))

(defmethod context-menu-action "hof_apply_block"
  [scope]
  (modal-preview/display (inspection/apply-inspection (. scope -block))))

(defmethod context-menu-action "hof_partial_block"
 [scope]
  (modal-preview/display (inspection/partial-inspection (. scope -block))))

(defmethod context-menu-action "hof_juxt_block"
 [scope]
 (modal-preview/display (inspection/juxt-inspection (. scope -block))))

(defn precondition [scope]
  (if (contains? #{"hof_map_block"
                   "hof_reduce_block"
                   "hof_filter_block"
                   "hof_remove_block"
                   "hof_apply_block"
                   "hof_partial_block" 
                   "hof_juxt_block"} 
                 (.. scope -block -type))
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
      (register (clj->js hof-inspection-contextmenu-item))) 
  
  (doseq [ctx-menu-item #{"blockCollapseExpand"
                          "blockDisable"
                          "workspaceDelete"
                          "collapseWorkspace" 
                          "expandWorkspace"}]
    (.. ContextMenuRegistry -registry (unregister ctx-menu-item))))