
(ns weare.web.core
  (:use compojure.core
        [ring.middleware.reload :only [wrap-reload]]
        [ring.middleware.stacktrace :only [wrap-stacktrace]]
        [ring.middleware.params :only [wrap-params]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [weare.web.pages :as pages]
            [weare.web.actions :as actions])
  (:gen-class :main true))

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
    (wrap-if dev? wrap-stacktrace)
    (wrap-if dev? wrap-reload)
    (wrap-params)))

