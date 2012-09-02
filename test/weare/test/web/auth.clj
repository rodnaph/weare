
(ns weare.test.web.auth
  (:use midje.sweet
        weare.web.auth))

(def handler (wrap-login (fn [x] true)))

(facts "about requiring login handler"

  (handler {:session {:user_id 1}}) => true

  (handler {}) => (contains {:status 302})

  (handler {:uri "/login"}) => true

)

