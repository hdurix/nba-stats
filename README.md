# Telegram NBA stats bot

Send NBA games statistics to a [Telegram][] channel (using a [Telegram Bot]) as soon as a game is finished.

Two running channels are available:
- full version with every stats → https://t.me/nba_games
- lite version with top stats → https://t.me/nba_top_stats

## Technical environment

* Java 11
* Spring Boot
* Maven

A library is used for getting NBA statistics: [nbawrapper-stats][]. Many thanks to [@antoineguay1][] for his great work!

[![codecov](https://codecov.io/gh/hdurix/nba-stats/branch/master/graph/badge.svg)](https://codecov.io/gh/hdurix/nba-stats)

## Methodologies

* TDD
* DDD
* Hexagonal architecture

## Environment variables

Before running the app (development, tests, production), be sure to provide 2 environment variables:
- `telegram.bot_id`
- `telegram.chat_id`

For instance with:

> TELEGRAM_BOT_ID=123:abc TELEGRAM_CHAT_ID=123 ./mvnw test

[Telegram]: https://telegram.org/
[Telegram Bot]: https://core.telegram.org/bots
[nbawrapper-stats]: https://github.com/antoineguay1/nbawrapper-stats
[@antoineguay1]: https://github.com/antoineguay1
