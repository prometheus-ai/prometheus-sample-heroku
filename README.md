# prometheus-sample

A Facebook Messenger Bot Example using `prometheus-ai/fb-messenger-clj`.

## Demo

Try this example Facebook Messenger Bot

https://m.me/1379818582139942

try typing:
* "Hello" or any text
* "image"
* "quick reply" and tap a quick reply.
* "buttons" and tap a button.

Heroku App:

https://prometheus-sample.herokuapp.com/

## Functionality

The Bot replies with "Hello World!" on any text input and returns an image when the user types "image".

* Facebook Messenger Authentication ([api.clj](https://github.com/prometheus-ai/prometheus-sample/blob/master/src/prometheus_sample/api.clj))
* Predefined fallback replies ([messages.clj](https://github.com/prometheus-ai/prometheus-sample/blob/master/src/prometheus_sample/messages.clj))
* Predefined handlers ([bot.clj](https://github.com/prometheus-ai/prometheus-sample/blob/master/src/prometheus_sample/bot.clj)) with standard fallback messages when receiving
  * Text Message
  * Quick Reply
  * Attachment
  * Postback
  * Referral

## Starting the Development Environment

To start a web server for the application, run:

    lein ring server-headless

### HTTPS for the Facebook Webhook Authentication via ngrok

Run `ngrok http 3000` ([read more about ngrok](https://ngrok.com))

### Set Environment Variables

Provide your Facebook Page Access Token, Verify Token and Page Secret for local development by creating a file called `profiles.clj` in the root directory `/profiles.clj`.

```clj
{:dev {:env {:page-access-token "REPLACE"
             :page-secret "REPLACE"
             :verify-token "REPLACE"}}}
```

In your Heroku app provide your Environment Variables in the settings of your app.

![Heroku Config Vars](https://github.com/prometheus-ai/prometheus-sample-heroku/blob/master/doc/images/heroku-config-vars.png)

Also check out this [step-by-step guide](https://github.com/lemmings-io/02-facebook-example) for further details on getting your development environment for a Facebook Messenger Bot up and running in Clojure.

## License

https://github.com/prometheus-ai
