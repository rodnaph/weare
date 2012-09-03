
(ns weare.web.auth
  (:require [ring.util.response :as response]))

(def ^{:dynamic true} *user* false)

(def login-uri "/login")

(defn ^{:doc "Returns the user from the session, or false"}
  user-in [req]
  (get-in req [:session :user] false))

;; Public
;; ------

(defn user [] *user*)

(defn ^{:doc "Wrapper for requiring login to all URI's except login-uri"}
  wrap-login [handler]
  (fn [req]
    (if (user-in req)
        (handler req)
        (response/redirect login-uri))))

(defn ^{:doc "Add binding for user in session."}
  wrap-user [handler]
  (fn [req]
    (binding [*user* (user-in req)]
      (handler req))))

