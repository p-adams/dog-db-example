(ns dogs.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [dogs.db :as db]))


(defn all-dogs []
  (let [ad (db/get-all-dogs)]
    (for [dog ad]
      (response dog))))

(defroutes app-routes
  (GET "/" [] (all-dogs))
  (route/not-found "Not Found"))

(def app
  (wrap-json-response
    app-routes site-defaults))
