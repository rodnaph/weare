
(ns weare.web.layouts
  (:require [weare.web.auth :as auth]
            [hiccup.page :as page]))

(defn- session-link []
  (println "User:" (auth/user))
  [:li
    (if-let [user (auth/user)]
      [:a {:href "/logout"} (format "Logout %s" (:name user))]
      [:a {:href "/login"} "Login"])])

;; Public
;; ------

(defn standard [title body]
  (page/html5
    [:head
      [:title title]
      (page/include-css "/assets/bootstrap/css/bootstrap.min.css")
      (page/include-css "/assets/css/main.css")]
    [:body
      [:div.container
        [:div.row
          [:div.span12
            [:div.navbar
              [:div.navbar-inner
                [:ul.nav
                  (session-link)]]]]
          [:div.span12
            [:header
              [:h1 "We Are..."]]]]
        body
        [:footer
          "Copyright Me"]
        (page/include-js "/assets/bootstrap/js/bootstrap.min.js")]]))

