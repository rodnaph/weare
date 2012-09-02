
(defproject weare "0.0.1"
  :description "What's going on in your group"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [korma "0.3.0-beta11"]
                 [compojure "1.1.1"]
                 [ring/ring-jetty-adapter "1.1.2"]
                 [ring/ring-devel "1.1.3"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [hiccup "1.0.1"]
                 [cheshire "4.0.2"]]
  :dev-dependencies [[midje "1.4.0"]
                     [lein-midje "1.0.9"]
                     [lein-marginalia "0.7.0"]]
  :plugins [[lein-ring "0.7.2"]]
  :main weare.core)

