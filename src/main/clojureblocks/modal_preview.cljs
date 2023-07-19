(ns clojureblocks.modal-preview)

(def dialog-preview (atom (.getElementById js/document "dialog-preview")))
(def dialog-preview-content (atom (.getElementById js/document "dialog-preview-content")))

(defn display
  "Displays `content` in the modal and presents it"
  [content]
  (set! (.-innerText @dialog-preview-content) content)
  (.showModal @dialog-preview))

(defn set-dark-theme
  "Sets the dark theme if `dark?` is true, sets the light theme otherwise"
  [dark?]
  (if dark?
    (.. @dialog-preview -classList (add "dialog-dark"))
    (.. @dialog-preview -classList (remove "dialog-dark"))))

(defn dialog-close-button-action
  "Closes the dialog"
  [_]
  (.close @dialog-preview))

(defn clicked-outside?
  "Checks if click event happened outside of the modal"
  [event]
  (let [rect (.getBoundingClientRect @dialog-preview)
        top (.-top rect)
        left (.-left rect)
        height (.-height rect)
        width (.-width rect)
        clientX (.-clientX event)
        clientY (.-clientY event)]
    (not (and
          (<= top clientY)
          (<= clientY (+ top height))
          (<= left clientX)
          (<= clientX (+ left width))))))

(defn dialog-click-action
  "Handles click events while dialog is open. If click is outside of "
  [event]
  (when (clicked-outside? event)
    (.close @dialog-preview)))

(.addEventListener @dialog-preview "click" dialog-click-action)
(.addEventListener (.getElementById js/document "button-dialog-preview-close") "click" dialog-close-button-action)