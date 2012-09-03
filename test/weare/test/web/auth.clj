
(ns weare.test.web.auth
  (:use midje.sweet
        weare.web.auth))

(def handler (wrap-login (fn [x] true)))
(def user-req {:session {:user {:name "foo"}}})

(facts "about requiring login handler"

  (handler user-req) => true

  (handler {:session {:user nil}}) => (contains {:status 302})
  (handler {}) => (contains {:status 302})

)

(facts "about making user available"

  ((wrap-user (fn [req] (user))) user-req) => {:name "foo"}

)

