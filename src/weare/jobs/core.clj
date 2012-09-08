(ns ^{:doc "This namespace provides a super-simple in-memory job
  server for easily processing jobs offline."}
  weare.jobs.core)

(def ^{:doc "The amount of time the job server will sleep to
  wait for more jobs to be added before processing them."}
  job-quiet-time-in-ms 5000)

(def ^{:doc "A ref type for the job queue.  New jobs are conj'd
  to the vector, while the processing pops jobs off the head to
  process them."}
  job-queue (ref []))

(defn- ^{:doc "Process a single job."}
  process-job [job]
  (println "Processing job:" job))

(defn- ^{:doc "Shift an item from the front of a queue."}
  shift! [queue]
  (dosync
    (let [item (first @queue)]
      (alter queue rest)
      item)))

(defn- ^{:doc "Process all the jobs in the queue, popping them off
  the head of the queue one at a time."}
  process-jobs []
  (while (not (empty? @job-queue))
    (process-job (shift! job-queue))))

(defn- ^{:doc "Listen for jobs being added to the job queue and then
  dispatch them for processing when they are."}
  listen-for-jobs []
  (process-jobs)
  (Thread/sleep job-quiet-time-in-ms)
  (recur))

;; Public
;; ------

(defn ^{:doc "Add a job to the queue to be processed.  No garauntee
  of job order, or when the job will be processed is given."}
  add! [job]
  (dosync
    (alter job-queue conj job)))

(defn ^{:doc "Start the jobs server which will process jobs
  that are submitted in an offline manner."}
  start []
  (future (listen-for-jobs)))

