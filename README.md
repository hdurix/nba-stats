# Environment variables

Before running the app (development, tests, production), be sure to provide 2 environment variables:
- `telegram.bot_id`
- `telegram.chat_id`

For instance with:

> TELEGRAM_BOT_ID=123:abc TELEGRAM_CHAT_ID=123 ./mvnw test
