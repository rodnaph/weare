
(ns weare.web.core
  (:use compojure.core
        [weare.web.auth :only [wrap-login]]
        (ring.middleware [reload :only [wrap-reload]]
                         [stacktrace :only [wrap-stacktrace]]
                         [params :only [wrap-params]]
                         [session :only [wrap-session]]))
  (:require (compojure [handler :as handler]
                       [route :as route])
            (weare.web [pages :as pages]
                       [actions :as actions])))

(defroutes app-routes

  (GET "/" [] pages/home)

  (GET "/login" [] pages/login)
  (POST "/login" [] actions/user-login)
  (ANY "/logout" [] actions/user-logout)

  (POST "/jobs" [] actions/job-create)

  (route/resources "/assets")
  (route/not-found "Not found")

)

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

