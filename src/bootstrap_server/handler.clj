(ns bootstrap-server.handler
  (:require
   [clj-http.lite.client :as http]
   [clojure.java.io :as io]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [hickory.core :as hick]
   [ring.middleware.json :as json]
   [ring.util.response :as resp])
  (:import (java.time Instant)))

(defn- handle
  ([status body]
   {:status (or status 404)
    :body   body})
  ([status]
   (handle status nil)))

(defn options [_]
  (handle 200))

