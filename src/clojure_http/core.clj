
(ns clojure_http.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.adapter.jetty :as jetty]))

(def answer 42)

(defroutes myapp
           (GET "/" [] "Hello from computer"))

(defn myappo [request]
  (str "Hello, " (get (:params request) "name"))
)


(myapp {})

(defn string-response-middleware [handler]
  (fn [request]
    (let [response (handler request)]
      (if (instance? String response)
        {:body response
         :status 200
         :headers {"Content-Type" "text/html"}}
        response))))

(defn -main []
  (jetty/run-jetty (string-response-middleware myapp) {:port 3000}))