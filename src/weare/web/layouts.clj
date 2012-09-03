
(ns weare.web.layouts
  (:require [weare.web.auth :as auth]
            [hiccup.page :as page]))

(defn- nav-item [title url]
  [:li
    [:a {:href url} title]])

(defn- session-link []
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
                [:a.brand {:href "/"} "We Are"]
                [:ul.nav
                  (nav-item "Sources" "/user/sources")]
                [:ul.nav.pull-right
                  (session-link)]]]]]
        body
        [:footer
          "Copyright Me"]
        (page/include-js "/assets/bootstrap/js/bootstrap.min.js")]]))

