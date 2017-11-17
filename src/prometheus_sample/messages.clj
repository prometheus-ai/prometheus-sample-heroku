(ns prometheus-sample.messages
  (:gen-class)
  (:require [fb-messenger.send :as send]
            [fb-messenger.templates :as templates]
            [environ.core :refer [env]]))

(def page-access-token (env :page-access-token))

(defn postback-fallback
  [sender-id]
  (send/send-message sender-id (templates/text-message "Sorry, I don't know how to handle that postback") page-access-token))

(defn referral-fallback
  [sender-id]
  (send/send-message sender-id (templates/text-message "Sorry, I don't know how to handle that referral") page-access-token))

(defn text-fallback
  [sender-id]
  (send/send-message sender-id (templates/text-message "Hello World!") page-access-token))

(defn image
  [sender-id]
  (send/send-message sender-id (templates/image-message "https://images.unsplash.com/photo-1461053148709-c980d9e5d8d9") page-access-token))

(defn attachment-fallback
  [sender-id]
  (send/send-message sender-id (templates/text-message "Thanks for your attachment.") page-access-token))

(defn quick-reply-fallback
  [sender-id]
  (send/send-message sender-id (templates/text-message "Sorry, I don't know how to handle that quick reply") page-access-token))

(defn send-quick-reply
  [sender-id]
  (send/send-message sender-id (templates/quick-replies-message
                                "What's your favorite movie genre?"
                                [{:content_type "text"
                                  :title "Action"
                                  :payload "DEVELOPER_DEFINED_PAYLOAD_FOR_PICKING_ACTION"}
                                 {:content_type "text"
                                  :title "Comedy"
                                  :payload "DEVELOPER_DEFINED_PAYLOAD_FOR_PICKING_COMEDY"}
                                 {:content_type "text"
                                  :title "Drama"
                                  :payload "DEVELOPER_DEFINED_PAYLOAD_FOR_PICKING_DRAMA"}])
                               page-access-token))

(defn send-buttons
  [sender-id]
  (send/send-message sender-id (templates/button-template
                                "What do you want me to do next?"
                                [{:title "Next"
                                  :type "postback"
                                  :payload "POSTBACK_NEXT"}
                                 {:title "Previous"
                                  :type "postback"
                                  :payload "POSTBACK_NEXT"}
                                 {:title "Random"
                                  :type "postback"
                                    :payload "POSTBACK_RANDOM"}])
                               page-access-token))
