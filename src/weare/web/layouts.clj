
(ns weare.web.layouts
  (:require [weare.web.auth :as auth]
            [hiccup.page :as page]))

(def logged-out-links [
    ["Register" "/register"]
    ["Login" "/login"]
])

(defn- nav-item [title url]
  [:li
    [:a {:href url} title]])

(defn- nav-items [items]
  (map (partial apply nav-item) items))

(defn- session-link []
  (if-let [user (auth/user)]
    (nav-item (str "Logout " (:name user)) "/logout")
    (nav-items logged-out-links)))

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

