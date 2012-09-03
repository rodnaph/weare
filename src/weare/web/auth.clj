
(ns weare.web.auth
  (:require [ring.util.response :as response]))

(def login-uri "/login")

(defn- ^{:doc "Indicates of the request has a valid session."}
  has-session? [req]
  (get-in req [:session :user_id] false))

;; Public
;; ------

(defn ^{:doc "Wrapper for requiring login to all URI's except login-uri"}
  wrap-login [handler]
  (fn [req]
    (if (has-session? req)
        (handler req)
        (response/redirect login-uri))))

