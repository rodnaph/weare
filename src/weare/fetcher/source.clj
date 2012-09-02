
(ns weare.fetcher.source)

(defmulti ^{:doc "Multi-method for different source implementations
  to be added as required."}
  fetch :type)

