
(ns weare.core
  (:use korma.db)
  (:require [ring.adapter.jetty :as jetty]
            [weare.web.core :as web]
            [weare.jobs.core :as jobs]))

(defn -main [& args]
  (defdb main (mysql {:db "weare"
                      :user "root"
                      :pass ""}))
  (jobs/start)
  (jetty/run-jetty web/app {:port 5555}))

