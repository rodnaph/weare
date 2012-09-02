
(ns weare.web.core
  (:use compojure.core
        [weare.web.auth :only [wrap-login]]
        (ring.middleware [cookies :only [wrap-cookies]]
                         [session :only [wrap-session]]
                         [params :only [wrap-params]]
                         [reload :only [wrap-reload]]
                         [stacktrace :only [wrap-stacktrace]]))
  (:require [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
            (compojure [handler :as handler]
                       [route :as route])
            (weare.web [pages :as pages]
                       [actions :as actions])))

(defroutes user-routes
  (GET "/account" request "Show Account"))

(defroutes app-routes

  (GET "/" [] pages/home)

  (context "/user" []
    (friend/wrap-authorize user-routes #{::user}))

  (GET "/login" [] pages/login)

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
    (wrap-login)
    (wrap-if dev? wrap-stacktrace)
    (wrap-if dev? wrap-reload)))

