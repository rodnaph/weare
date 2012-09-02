
(ns weare.entities
  (:use korma.core))

(defentity user
  (table "users"))

(defentity source
  (table "sources")
  (belongs-to user))

(defentity activity
  (table "activity")
  (belongs-to user))

