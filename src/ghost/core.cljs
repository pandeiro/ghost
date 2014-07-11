(ns ghost.core
  (:refer-clojure :exclude [repeat]))

;;
;; Utilities
;;
(defn ->js [x]
  (cond (string? x) x
        (keyword? x) (name x)
        (map? x) (.-strobj (reduce (fn [m [k v]] (assoc m (->js k) (->js v))) {} x))
        (coll? x) (apply array (map ->js x))
        :else x))

(def require-js js/require)

;;
;; Casper instance
;;
(def ^{:doc "Default casper instance, can be overridden by using set-casper-options!"
       :dynamic true}
  *casper* (.create (require-js "casper")))

(defn set-casper-options!
  "Redefine *casper* with specific options: http://casperjs.org/api.html#casper.options"
  [opts]
  (set! *casper* (.create (require-js "casper") (->js opts))))

;;
;; Casper API
;;
(defn back [] (.back *casper*))

(defn base64-encode
  ([s] (.base64encode *casper* s))
  ([s method data] (.base64encode *casper* s (name method) (->js data))))

(defn click [sel] (.click *casper* (name sel)))

(defn click-label
  ([s] (.clickLabel *casper* s))
  ([s tag] (.clickLabel *casper* s (name tag))))

(defn capture [path rect] (.capture *casper* path (->js rect)))

(defn capture-base64
  ([fmt] (.captureBase64 *casper* (name fmt)))
  ([fmt part] (.captureBase64 *casper* (name fmt) (if (string? part) part (->js part)))))

(defn capture-selector [out sel] (.captureSelector *casper* out (name sel)))

(defn clear [] (.clear *casper*))

(defn debug-html [] (.debugHTML *casper*))

(defn debug-page [] (.debugPage *casper*))

(defn die
  ([s] (.die *casper* s))
  ([s status] (.die *casper* s status)))

(defn download
  ([url out] (.download *casper* url out))
  ([url out method data] (.download *casper* url out (name method) (->js data))))

(defn each [a f] (.each *casper* a f))

(defn echo
  ([s] (.echo *casper* s))
  ([s style] (.echo *casper* s style)))

(defn evaluate
  ([f] (.evaluate *casper* f))
  ([f ctx] (.evaluate *casper* f (->js ctx))))

(defn evaluate-or-die
  ([f] (.evaluateOrDie *casper* f))
  ([f s] (.evaluateOrDie *casper* f s)))

(defn exit
  ([] (.exit *casper*))
  ([status] (.exit *casper* status)))

(defn exists? [sel] (.exists *casper* (name sel)))

(defn fetch-text [sel] (.fetchText *casper* (name sel)))

(defn forward [] (.forward *casper*))

(defn log
  ([s] (.log *casper* s))
  ([s level] (.log *casper* s (name level)))
  ([s level space] (.log *casper* s (name level) space)))

(defn fill
  ([sel data] (.fill *casper* (name sel) (->js data)))
  ([sel data submit?] (.fill *casper* (name sel) (->js data) submit?)))

(defn get-current-url [] (.getCurrentUrl *casper*))

(defn get-element-attribute [sel attr] (.getElementAttribute *casper* (name sel) (name attr)))

(defn get-element-bounds [sel]
  (js->clj (.getElementBounds *casper* (name sel)) :keywordize-keys true))

(defn get-global [name] (.getGlobal *casper* name))

(defn get-page-content [] (.getPageContent *casper*))

(defn get-title [] (.getTitle *casper*))

(defn mouse-event [type sel] (.mouseEvent *casper* (name type) (name sel)))

(defn open
  ([url] (.open *casper* url))
  ([url opts] (.open *casper* url (->js opts))))

(defn reload [f] (.reload *casper* f))

(defn repeat [n f] (.repeat *casper* n f))

(defn resource-exists? [url] (.resourceExists *casper* url))

(defn run
  ([] (.run *casper*))
  ([f] (.run *casper* f)))

(defn set-http-auth [u p] (.setHttpAuth *casper* u p))

(defn start
  ([url] (.start *casper* url))
  ([url f] (.start *casper* url f)))

(defn then [f] (.then *casper* f))

(defn then-evaluate
  ([f] (.thenEvaluate *casper* f))
  ([f ctx] (.thenEvaluate *casper* f (->js ctx))))

(defn then-open
  ([url] (.thenOpen *casper* url))
  ([url f] (.thenOpen *casper* url f))
  ([url opts f] (.thenOpen *casper* url (->js opts) f)))

(defn then-open-and-evaluate
  ([url] (.thenOpenAndEvaluate *casper* url))
  ([url f] (.thenOpenAndEvaluate *casper* url f))
  ([url f ctx] (.thenOpenAndEvaluate *casper* url f (->js ctx))))

(defn user-agent [s] (.userAgent *casper* s))

(defn viewport [w h] (.viewport *casper* w h))

(defn visible? [sel] (.visible *casper* (name sel)))

(defn wait
  ([ms] (.wait *casper* ms))
  ([ms f] (.wait *casper* ms f)))

(defn wait-for
  ([pred] (.waitFor *casper* pred))
  ([pred f] (.waitFor *casper* pred f))
  ([pred f on-timeout] (.waitFor *casper* pred f on-timeout))
  ([pred f on-timeout time] (.waitFor *casper* pred f on-timeout time)))

(defn wait-for-selector
  ([sel] (.waitForSelector *casper* (name sel)))
  ([sel f] (.waitForSelector *casper* (name sel) f))
  ([sel f on-timeout] (.waitForSelector *casper* (name sel) f on-timeout))
  ([sel f on-timeout time] (.waitForSelector *casper* (name sel) f on-timeout time)))

(defn wait-while-selector
  ([sel] (.waitWhileSelector *casper* (name sel)))
  ([sel f] (.waitWhileSelector *casper* (name sel) f))
  ([sel f on-timeout] (.waitWhileSelector *casper* (name sel) f on-timeout))
  ([sel f on-timeout time] (.waitWhileSelector *casper* (name sel) f on-timeout time)))

(defn wait-for-popup
  ([url] (.waitForPopup *casper* url))
  ([url f] (.waitForPopup *casper* url f))
  ([url f on-timeout] (.waitForPopup *casper* url f on-timeout))
  ([url f on-timeout time] (.waitForPopup *casper* url f on-timeout time)))

(defn wait-for-resource
  ([url] (.waitForResource *casper* url))
  ([url f] (.waitForResource *casper* url f))
  ([url f on-timeout] (.waitForResource *casper* url f on-timeout))
  ([url f on-timeout time] (.waitForResource *casper* url f on-timeout time)))

(defn wait-until-visible
  ([sel] (.waitUntilVisible *casper* (name sel)))
  ([sel f] (.waitUntilVisible *casper* (name sel) f))
  ([sel f on-timeout] (.waitUntilVisible *casper* (name sel) f on-timeout))
  ([sel f on-timeout time] (.waitUntilVisible *casper* (name sel) f on-timeout time)))

(defn wait-while-visible
  ([sel] (.waitWhileVisible *casper* (name sel)))
  ([sel f] (.waitWhileVisible *casper* (name sel) f))
  ([sel f on-timeout] (.waitWhileVisible *casper* (name sel) f on-timeout))
  ([sel f on-timeout time] (.waitWhileVisible *casper* (name sel) f on-timeout time)))

(defn warn [s] (.warn *casper* s))

(defn zoom [n] (.zoom *casper* n))

