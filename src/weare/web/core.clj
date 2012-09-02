
(ns weare.web.core
  (:use compojure.core
        (ring.middleware [reload :only [wrap-reload]]
                         [stacktrace :only [wrap-stacktrace]]
                         [params :only [wrap-params]]
                         [session :only [wrap-session]]))
  (:require (compojure [handler :as handler]
                       [route :as route])
            (weare.web [pages :as pages]
                       [actions :as actions])))

(defn wrap-login [handler]
  (fn [req]
    (if (get-in req [:session :user_id])
        (handler req)
        {:status 301})))

(defroutes app-routes
  (GET "/" [] pages/home)
  (POST "/jobs" [] actions/job-create)
  (route/resources "/")
  (route/not-found "Not found"))

(def dev? true)

(defn wrap-if [handler pred wrapper & args]
  (if pred
    (apply wrapper handler args)
    handler))

(def app
  (-> app-routes
    (handler/site)
    (wrap-session)
    (wrap-login)
    (wrap-if dev? wrap-stacktrace)
    (wrap-if dev? wrap-reload)
    (wrap-params)))

