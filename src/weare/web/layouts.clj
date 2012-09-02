
(ns weare.web.layouts
  (:require [hiccup.page :as page]))

(defn standard [title body]
  (page/html5
    [:head
      [:title title]
      (page/include-css "/bootstrap/css/bootstrap.min.css")
      (page/include-css "/css/main.css")]
    [:body
      [:div.container
        [:div.row
          [:div.span12
            [:header
              [:h1 "We Are..."]]]]
        body
        [:footer
          "Copyright Me"]
        (page/include-js "/bootstrap/js/bootstrap.min.js")]]))

