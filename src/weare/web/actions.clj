
(ns weare.web.actions
  (:require [weare.jobs.core :as jobs]
            [ring.util.response :as response]))

;; Public
;; ------

(defn job-create [req]
  (jobs/add! {:type "blah"})
  (response/redirect-after-post "/"))

(defn user-login [req]
  req)

(defn user-logout [req]
  req)

