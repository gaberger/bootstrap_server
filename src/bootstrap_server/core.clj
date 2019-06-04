(ns bootstrap-server.core
  (:require
   [bootstrap-server.api :as api]
   [org.httpkit.server :as srv]
   [clojure.java.io :as io]
   [taoensso.timbre :as timbre
    :refer [log  trace  debug  info  warn  error  fatal  report
            logf tracef debugf infof warnf errorf fatalf reportf
            spy get-env]]
   [ring.logger.timbre :as logger.timbre])
  (:gen-class))

(defn update-dir []
  (try
    (if-not (.exists (io/as-file "public/images"))
      (do
        (info "Creating file serving directories")
        (.mkdirs (io/as-file "public/images"))
        (.mkdirs (io/as-file "public/configs")))
      (info "Directory tree for file serving exists"))
    (catch Exception e
      (error "Exception creating directories" e))))

(defn -main [& args]
  (info "Starting bootstrap server on port 3000")
  (update-dir)
  (srv/run-server (logger.timbre/wrap-with-logger api/public-routes) {:port 3000}))