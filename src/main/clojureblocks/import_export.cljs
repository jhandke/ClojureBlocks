(ns clojureblocks.import-export)

(defn file-blob [datamap mimetype]
  (js/Blob. [datamap] {"type" mimetype}))

(defn link-for-blob [blob filename]
  (doto (.createElement js/document "a")
    (set! -download filename)
    (set! -href (.createObjectURL js/URL blob))))

(defn click-and-remove-link [link]
  (let [click-remove-callback
        (fn []
          (.dispatchEvent link (js/MouseEvent. "click"))
          (.removeChild (.-body js/document) link))]
    (.requestAnimationFrame js/window click-remove-callback)))

(defn add-link [link]
  (.appendChild (.-body js/document) link))

(defn download-data [data filename mimetype]
  (-> data
      (file-blob mimetype)
      (link-for-blob filename)
      add-link
      click-and-remove-link))

(defn read-file [file callback]
  (let [js-file-reader (js/FileReader.)]
    (set! (.-onload js-file-reader)
          (fn [evt]
            (let [result (-> evt .-target .-result)]
              (callback result))))
    (.readAsText js-file-reader file)))

(defn file-input [callback]
  (let [input (.createElement js/document "input")]
    (set! input -type "file")
    (.addEventListener input "change"
                       (fn [e] (read-file (first (.. e -target -files)) callback)))
    (.click input)))