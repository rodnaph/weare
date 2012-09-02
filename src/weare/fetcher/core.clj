
(ns weare.fetcher.core
  (:use korma.core
        weare.fetcher.source)
  (:require [weare.entities :as e]
            weare.fetcher.source.github))

(defn- new-item? [item]
  (not (nil? item)))

(defn- fetch-and-insert [source]
  (let [defaults {:users_id 1
                  :date_imported "2012-03-03 03:03:03"}
        activities (->> (fetch source)
                        (filter new-item?)
                        (map #(merge defaults %)))]
    (insert e/activity
      (values activities))))

(defn- fetch-sources []
  (doseq [source (select e/source)]
    (fetch-and-insert source)))

;; Public
;; ------

