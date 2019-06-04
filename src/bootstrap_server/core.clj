(ns bootstrap-server.core
  (:require
   [bootstrap-server.api :as api]
   [org.httpkit.server :as srv]
   [ring.logger.timbre :as logger.timbre])
  #_(:import (sun.util.logging PlatformLogger PlatformLogger$Level))
  (:gen-class))

#_(def ^:private logger
    "This throwaway static/compile-time invocation is a workaround for runtime
   reflection of sun.util.logging.{LoggingSupport|PlatformLogger}."
    (.isLoggable (PlatformLogger/getLogger "dummy") PlatformLogger$Level/ALL))

(defn -main [& args]
  (println "Hello, Web!")
  (srv/run-server (logger.timbre/wrap-with-logger api/public-routes) {:port 3000}))