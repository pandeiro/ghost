(ns ghost.tester
  :import [ghost.core :refer *casper*]
  (:refer-clojure :exclude [repeat]))

(def require-js js/require)

;;
;; Casper instance
;;
(def ^{:doc "Default tester instance"
       :dynamic true}
  *tester* (.create (require-js "tester")))

;;
;; Tester API
;;

;;abort
(defn abort
  ([] (.abort *tester*))
  ([message] (.abort *tester* message)))

(defn skip [num-tests message] (.skip *tester* num-tests message))

(defn assert
  ([subject message] (.assert *tester* subject message))
  ([subject message context] (.assert *tester* subject message context)))
(defn assert-true assert)

(defn assert-equals
  ([subject expected] (.assertEquals *tester* subject expected))
  ([subject expected message] (.assertEquals *tester* subject expected message)))

(defn assert-not-equals
  ([subject shouldnt] (.assertNotEquals *tester* subject shouldnt))
  ([subject shouldnt message] (.assertNotEquals *tester* subject shouldnt message)))

(defn assert-element-count
  ([sel cnt] (.assertElementCount *tester* sel cnt))
  ([sel cnt message] (.assertElementCount *tester* sel cnt message)))

(defn assert-eval [] ())

(defn assert-evaluate [] ())

(defn assert-eval-equals [] ())

(defn assert-fail [] ())

(defn assert-field [] ())

(defn assert-field-css [] ())

(defn assert-field-xpath [] ())

;; assert-exists
;; assert-exist
;; assert-selector-exists
;; assert-selector-exist
(defn assert-exists [] ())

;; assert-doesnt-exist
(defn assert-doesnt-exists)

(defn assert-http-status [] ())

(defn assert-match [] ())

(defn assert-false)
(defn assert-not [] ())

(defn assert-not-visible [] ())

;;assert-raise
;;assert-throws
(defn assert-raises [] ())

;; assert-resource-exist
(defn assert-resource-exists [] ())

(defn assert-text-doesnt-exist [] ())

(defn assert-text-exists [] ())
;;(defn assert-text-exist)

(defn assert-truthy [] ())

(defn assert-falsy [] ())

;;(defn assert-selector-contains [] ())
(defn assert-selector-has-text [] ())


;;
;;Tester.prototype.assertSelectorDoesntHaveText =
;;Tester.prototype.assertSelectorDoesntContain = function assertSelectorDoesntHaveText(selector, text, message)

;;assertTitle

;;assertTitleMatch assertTitleMatches

;;assertType

;;assertInstanceOf

;;assertUrlMatch assertUrlMatches

;;assertVisible

(defn bar [text style] (.bar *tester* text style))

;;setup

;;teardown

(defn begin [] (.begin *tester*))

(defn comment [message] (.comment *tester* message))

(defn done [] (.done *tester*))

(defn error [message] (.error *tester* message))

;;exec

;;fail

;;info

;;pass

;;runSuites

;;runTest

;;terminate

;;saveResults



;;calculateDuration


