(ns bootstrap-server.api
  (:require
   [compojure.route :as route]
   [compojure.core :refer :all]
   [bootstrap-server.handler :as h]
   [taoensso.timbre :as timbre
    :refer-macros [log  trace  debug  info  warn  error  fatal  report
                   logf tracef debugf infof warnf errorf fatalf reportf
                   spy get-env]]))

(defroutes public-routes
  (OPTIONS "/**"                              [] h/options)
  (route/files "/images" {:root "public/images"})
  (route/files "/configs" {:root "public/configs"})
  (route/not-found "Not Found"))


