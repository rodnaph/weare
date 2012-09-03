
(ns weare.test.web.auth
  (:use midje.sweet
        weare.web.auth))

(def handler (wrap-login (fn [x] true)))

(facts "about requiring login handler"

  (handler {:session {:user_id 1}}) =not=> (contains {:status 302})
  (handler {}) => (contains {:status 302})

)

