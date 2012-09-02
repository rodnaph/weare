
(ns weare.web.auth
  (:require [ring.util.response :as response]))

(def login-uri "/login")
(def logout-uri "/logout")

(defn- is-login-uri? [req]
  (some #(= % (:uri req))
         [login-uri logout-uri]))

(defn- has-session? [req]
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

