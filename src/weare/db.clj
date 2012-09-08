
(ns weare.db
  (:require [datomic.api :as d]))

(defn- field [ident db-type]
  {:db/id #db/id[:db.part/db]
   :db/ident ident
   :db/valueType db-type
   :db/cardinality :db.cardinality/one
   :db/fulltext true
   :db.install/_attribute :db.part/db})

(def db-uri "datomic:mem://weare")

(def cnn
  (let [cnn (do
              (d/create-database db-uri)
              (d/connect db-uri))
        schema [
          (field :user/name :db.type/string)
          (field :user/email :db.type/string)
          (field :user/pass :db.type/string)
        ]]
    (d/transact cnn schema)
    cnn))

