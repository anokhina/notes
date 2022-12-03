## Telegram

BotFather

```
/newbot
/help
```

### Java

<https://github.com/pengrad/java-telegram-bot-api>

<https://javarush.ru/groups/posts/504-sozdanie-telegram-bota-na-java-ot-idei-do-deploja>

<https://core.telegram.org/bots/api#inlinekeyboardmarkup>

#### Libs

<https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started>

Too old examples <https://github.com/rubenlagus/TelegramBotsExample>

### WebhookBot

<https://github.com/UnAfraid/SpringTelegramBot/blob/master/src/main/java/com/github/unafraid/spring/bot/TelegramWebHookBot.java>

### Spring boot

<https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-spring-boot-starter>

Run grizzly server for webhooks

### Login with telegram

<https://habr.com/ru/post/501728/>

### WebHook

<https://core.telegram.org/bots/webhooks#how-do-i-set-a-webhook-for-either-type>

```
    A curl example for a verified certificate:
  curl -F "url=https://<YOURDOMAIN.EXAMPLE>/<WEBHOOKLOCATION>" https://api.telegram.org/bot<YOURTOKEN>/setWebhook

    A curl example for a self-signed certificate:
  curl -F "url=https://<YOURDOMAIN.EXAMPLE>/<WEBHOOKLOCATION>" -F "certificate=@<YOURCERTIFICATE>.pem" https://api.telegram.org/bot<YOURTOKEN>/setWebhook

    A curl example to clear a previous webhook :
  curl -F "url=" https://api.telegram.org/bot<YOURTOKEN>/setWebhook

    By default that means we’re knocking at your door on port 443. If you want to use another port (80,88 or 8443), you’ll have to specify the port in the URL parameter.

    Example:
  url=https://<YOURDOMAIN.EXAMPLE>:88/<WEBHOOKLOCATION>
```

