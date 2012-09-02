
(ns weare.test.jobs.core
  (:use midje.sweet
        weare.jobs.core))

(facts "about adding jobs to be processed"

  (add! {:foo "bar"})
    => (contains {:foo "bar"})

  (do (add! {:foo "bar"})
      (add! {:foo "baz"}))
    => (contains {:foo "baz"})

)

