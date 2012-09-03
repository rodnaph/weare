
(ns weare.web.actions
  (:require [weare.jobs.core :as jobs]
            [ring.util.response :as response]))

;; Public
;; ------

(defn job-create [req]
  (jobs/add! {:type "blah"})
  (response/redirect-after-post "/"))

(defn user-login [req]
  (assoc-in (response/redirect "/")
            [:session :user_id] 1))

(defn user-logout [req]
  (merge (response/redirect "/")
         {:session nil}))

