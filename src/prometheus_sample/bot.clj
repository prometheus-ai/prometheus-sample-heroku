(ns prometheus-sample.bot
  (:gen-class)
  (:require [clojure.string :as s]
            [environ.core :refer [env]]
            [org.httpkit.client :as http]
            [prometheus-sample.api :as api]
            [prometheus-sample.messages :as msg]
            [clojure.data.json :as json]))

(defn on-message [payload]
  (println "on-message payload:")
  (println payload)
  (let [sender-id (get-in payload [:sender :id])
        recipient-id (get-in payload [:recipient :id])
        time-of-message (get-in payload [:timestamp])
        message-text (get-in payload [:message :text])]
    (cond
      (s/includes? (s/lower-case message-text) "image") (msg/image sender-id)
      (s/includes? (s/lower-case message-text) "quick reply") (msg/send-quick-reply sender-id)
      (s/includes? (s/lower-case message-text) "buttons") (msg/send-buttons sender-id)
      :else (msg/text-fallback sender-id))))

(defn on-payload [payload]
  (println "on-payload payload:")
  (println payload)
  (let [action (get-in payload [:payload])
        sender-id (get-in payload [:psid])]
    (msg/referral-fallback sender-id)))

(defn on-postback [payload]
  (println "on-postback payload:")
  (println payload)
  (let [sender-id (get-in payload [:sender :id])
        recipient-id (get-in payload [:recipient :id])
        time-of-message (get-in payload [:timestamp])
        postback (get-in payload [:postback :payload])
        referral (get-in payload [:postback :referral :ref])]
    (msg/postback-fallback sender-id)))

(defn on-referral [payload]
  (println "on-referral payload:")
  (println payload)
  (let [sender-id (get-in payload [:sender :id])
        recipient-id (get-in payload [:recipient :id])
        time-of-message (get-in payload [:timestamp])
        referral (get-in payload [:referral :ref])]
    (msg/referral-fallback sender-id)))

(defn on-quick-reply [payload]
  (println "on-quick-reply payload:")
  (println payload)
  (let [sender-id (get-in payload [:sender :id])
        recipient-id (get-in payload [:recipient :id])
        time-of-message (get-in payload [:timestamp])
        quick-reply (get-in payload [:message :quick_reply :payload])
        text (get-in payload [:message :text])]
    (msg/quick-reply-fallback sender-id)))

(defn on-attachments [payload]
  (println "on-attachment payload:")
  (println payload)
  (let [sender-id (get-in payload [:sender :id])
        recipient-id (get-in payload [:recipient :id])
        time-of-message (get-in payload [:timestamp])
        attachments (get-in payload [:message :attachments])]
    (msg/attachment-fallback sender-id)))
