(ns prometheus-sample.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [prometheus-sample.api :as api]
            [prometheus-sample.bot :as bot]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            ; Dependencies for Heroku Example
            [compojure.handler :refer [site]]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]))

(defroutes app-routes
  (GET "/" [] "\"Hello World\" from the Prometheus Sample on Heroku! :)")
  (POST "/webhook" request
                   (api/handle-message request bot/on-message bot/on-postback bot/on-payload bot/on-referral bot/on-quick-reply bot/on-attachments)
                   {:status 200})
  (GET "/webhook" request (api/bot-authenticate request))
  (route/not-found "Not Found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-keyword-params)
      (wrap-json-params)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 3000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
