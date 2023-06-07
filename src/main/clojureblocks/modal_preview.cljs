(ns clojureblocks.modal-preview)

(def dialog-preview (atom (.getElementById js/document "dialog-preview")))
(def dialog-preview-content (atom (.getElementById js/document "dialog-preview-content")))

(defn display
  [content]
  (set! (.-innerText @dialog-preview-content) content)
  (.showModal @dialog-preview))

(defn set-dark-theme
  [dark?]
  (if dark?
    (.. @dialog-preview -classList (add "dialog-dark"))
    (.. @dialog-preview -classList (remove "dialog-dark"))))

(defn dialog-close-button-action
  [_]
  (.close @dialog-preview))

(defn dialog-click-action
  [event]
  (let [rect (.getBoundingClientRect @dialog-preview)
        top (.-top rect)
        left (.-left rect)
        height (.-height rect)
        width (.-width rect)
        clientX (.-clientX event)
        clientY (.-clientY event)]
    (println top clientY height)
    (when-not (and
           (<= top clientY)
           (<= clientY (+ top height))
           (<= left clientX) 
           (<= clientX (+ left width))
           )
      (.close @dialog-preview))))

(.addEventListener @dialog-preview "click" dialog-click-action)
(.addEventListener (.getElementById js/document "button-dialog-preview-close") "click" dialog-close-button-action)