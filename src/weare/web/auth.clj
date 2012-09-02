
(ns weare.web.auth
  (:require [ring.util.response :as response]))

(def login-uri "/login")

;; Public
;; ------

(defn ^{:doc "Wrapper for requiring login to all URI's except login-uri"}
  wrap-login [handler]
  (fn [req]
    (if (or (get-in req [:session :user_id])
            (= login-uri (:uri req)))
        (handler req)
        (response/redirect login-uri))))

