(ns clojureblocks.import-export)

(defn file-blob
  "Creates a JavaScript file blob with `datamap` and `mimetype`"
  [datamap mimetype]
  (js/Blob. [datamap] {"type" mimetype}))

(defn link-for-blob 
  "Creates a link element with link to `blob` and a `filename`"
  [blob filename]
  (doto (.createElement js/document "a")
    (set! -download filename)
    (set! -href (.createObjectURL js/URL blob))))

(defn click-and-remove-link 
  "Programmatically triggers the click event on a link"
  [link]
  (let [click-remove-callback
        (fn []
          (.dispatchEvent link (js/MouseEvent. "click"))
          (.removeChild (.-body js/document) link))]
    (.requestAnimationFrame js/window click-remove-callback)))

(defn add-link 
  "Adds a link to the DOM"
  [link]
  (.appendChild (.-body js/document) link))

(defn download-data
  "Downloads `data` with a `filename` and a `mimetype`"
  [data filename mimetype]
  (-> data
      (file-blob mimetype)
      (link-for-blob filename)
      add-link
      click-and-remove-link))

(defn read-file 
  "Reads a file with JavaScript's FileReader and calls `callback` with the result"
  [file callback]
  (let [js-file-reader (js/FileReader.)]
    (set! (.-onload js-file-reader)
          (fn [evt]
            (let [result (-> evt .-target .-result)]
              (callback result))))
    (.readAsText js-file-reader file)))

(defn file-input 
  "Creates an input element and programmatically trigger the click event on it"
  [callback]
  (let [input (.createElement js/document "input")]
    (set! input -type "file")
    (.addEventListener input "change"
                       (fn [e] (read-file (first (.. e -target -files)) callback)))
    (.click input)))