
(ns weare.test.web.core
  (:use midje.sweet
        weare.web.core))

(facts "about requiring login"

  ((wrap-login (fn [x] true)) {:session {:user_id 1}}) => true

  ((wrap-login (fn [x] true)) {}) => {:status 301}

)

