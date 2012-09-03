
(ns weare.test.web.auth
  (:use midje.sweet
        weare.web.auth))

(def handler (wrap-login (fn [x] true)))

(facts "about requiring login handler"

  (handler {:session {:user {:name "foo"}}}) => true

  (handler {:session {:user nil}}) => (contains {:status 302})
  (handler {}) => (contains {:status 302})

)

