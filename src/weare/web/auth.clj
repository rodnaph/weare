
(ns weare.web.auth
  (:require [ring.util.response :as response]))

(def login-uri "/login")
(def logout-uri "/logout")
(def assets-uri "/assets")

(defn- starts-with [string substring]
  (= 0 (.indexOf (or string "") substring)))

(defn- is-asset-uri? [uri]
  (starts-with uri "/assets/")) 

(defn- ^{:doc "Indicates if the request is for one of the allowed 'login' URIs."}
  is-login-uri? [req]
  (let [uri (:uri req)]
    (or (= login-uri uri)
        (= logout-uri uri)
        (is-asset-uri? uri))))

(defn- ^{:doc "Indicates of the request has a valid session."}
  has-session? [req]
  (get-in req [:session :user_id]))

;; Public
;; ------

(defn ^{:doc "Wrapper for requiring login to all URI's except login-uri"}
  wrap-login [handler]
  (fn [req]
    (if (or (has-session? req)
            (is-login-uri? req))
        (handler req)
        (response/redirect login-uri))))

