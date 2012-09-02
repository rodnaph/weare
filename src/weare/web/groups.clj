
(ns weare.web.groups
  (:use korma.core)
  (:require [weare.entities :as e]))

(defn activity []
  (select e/activity
    (with e/user)))

