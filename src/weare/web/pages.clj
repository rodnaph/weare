
(ns weare.web.pages
  (:use hiccup.form)
  (:require [weare.web.layouts :as layouts]
            [weare.web.groups :as groups]))

(defn- render-activity [a]
  [:div
    [:a.title {:href (:url a)} (:title a)]
    [:div.description (:description a)]
    [:div.user (:title (:user a))]])

;; Public
;; ------

(defn home [req]
  (layouts/standard "Home"
    [:div.row
      [:div.span8
        [:ul
          (for [activity (groups/activity)]
            [:li (render-activity activity)])]]
      [:div.span4
        (form-to {} ["POST" "/jobs"]
          (submit-button {} "Create Job"))]]))

(defn login [req]
  (layouts/standard "Login"
    [:div.row
      [:div.span8
        (form-to {:class "form-horizontal"} ["POST" "/login"]
          [:div.control-group
           (label {} "email" "Email")
           (text-field {} "email")]
          [:div.control-group
           (label {} "pass" "Password:")
           (password-field {} "pass")]
          [:div.control-group
           (submit-button {:class "btn btn-primary"} "Login")])]]))

(defn not-found [req]
  (layouts/standard "Uh oh..."
    [:div.row
      [:div.span12
        [:h2 "Not here sorry..."]
        [:p "Looks like the thing you want ain't here."]]]))

