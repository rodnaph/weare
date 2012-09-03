
(ns weare.web.actions
  (:use korma.core)
  (:require [weare.entities :as e]
            [weare.jobs.core :as jobs]
            [ring.util.response :as response]))

(defn- hash-password [pass]
  (->> (-> "sha1"
           java.security.MessageDigest/getInstance
           (.digest (.getBytes pass)))
       (map #(.substring
              (Integer/toString
               (+ (bit-and % 0xff) 0x100) 16) 1))
       (apply str)))

(defn- find-user [{:keys [params]}] 
  (let [pass (hash-password (:pass params ""))
        res (select e/user
              (where (and {:email (:email params)}
                          {:pass pass})))]
    (first res)))

;; Public
;; ------

(defn user-login [req]
  (let [user (find-user req)
        url (if user "/" "/login")]
    (assoc-in (response/redirect url)
              [:session :user] user)))

(defn user-logout [req]
  (merge (response/redirect "/")
         {:session nil}))

(defn source-create [req]
  (jobs/add! {:type :source-create
              :url (get-in req [:params :url])})
  (response/redirect "/user/sources"))

