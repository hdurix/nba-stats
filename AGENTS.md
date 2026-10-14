# AGENTS.md

Telegram bot that posts NBA game stats once a game is finished. Overview in `README.md`.

## Commands

- Test: `TELEGRAM_BOT_ID=x TELEGRAM_CHAT_ID=x ./mvnw test` (without both variables the Spring context fails to start)
- Format: `npm run prettier:java` (CI runs `prettier:java:check`)

## Constraints

- Java 11 in CI: no `String.formatted`, records, text blocks
- `Api*` DTOs have private fields and getters only: Jackson needs the getter to fill the field, keep it even if unused

## Style

- Guard clauses, small well-named methods, no comments
- TDD: write the failing test first

## Tests

- `*IntTest` and `NbaStatsApplicationTests` call the real NBA API. `NbaStatsApplicationServiceIntTest` also posts to Telegram: never run it with real credentials

## NBA API

- The feed changes without notice (`Breaking NBA API changes`, `minutesCalculated` gone since October 2026, foreign teams in preseason): reproduce against the live feed before changing code
- The boxscore (`cdn.nba.com`) answers 403 to JVM HTTP clients from some IPs (GitHub Actions, the Raspberry Pi) even with the headers `curl` passes with, hence `Curl` (curl sub-process). Do not switch it back to `RestTemplate`
- Team records (wins/losses) of the game card are not updated yet when a game turns final: `ApiNbaGames` waits for a second sighting (scheduler every 3 minutes) before publishing

## State

- Published game ids are stored in `target/releases.txt`. Deleting `target/` (`mvnw clean`) makes the bot repost yesterday's and today's finished games
