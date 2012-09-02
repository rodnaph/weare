
(ns ^{:doc "Namespace that provides functions for parsing a users
  activity from Github.  It handles the 'github' source from the feeds
  table and pulls down JSON to read through."}
  weare.fetcher.source.github
  (:use weare.fetcher.source)
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(defmulti to-activity :type)

(defmethod to-activity "WatchEvent"
  [item]
  (let [repo (:repository item)]
    {:title (format "Watched %s (%s)" (:name repo) (:descripton repo))
     :url (:url item)}))

(defmethod to-activity "PushEvent"
  [item]
  {:title (format "Pushed to %s" (get-in item [:repository :name]))
   :url (:url item)})

(defmethod to-activity :default [item] nil)

;; Public
;; ------

(defn activity [item]
  (let [defaults {:date_authored "2012-01-01 12:00:00"}]
    (if-let [activity (to-activity item)]
      (merge defaults activity))))

(defmethod fetch "github"
  [source]
  (let [url (format "https://github.com/%s.json" (:value source))
        data (json/parse-stream (io/reader url) true)
        activities (map activity data)]
    activities))

