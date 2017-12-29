(ns dogs.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec {:dbtype "h2" :dbname "./dogs"})
(defn add-dog [name breed]
  (let [results (jdbc/insert! db-spec :dogs {:name name :breed breed})]
    (assert (= (count results) 1))))

(defn get-all-dogs []
  (jdbc/query db-spec "select id, name, breed from dogs"))
