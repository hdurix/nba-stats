package fr.hippo.nbastats.infrastructure.secondary.telegram;

import fr.hippo.nbastats.domain.GameNotifier;
import fr.hippo.nbastats.domain.GameStat;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
class TelegramGameNotifier implements GameNotifier {
    private static final Logger log = LoggerFactory.getLogger(TelegramGameNotifier.class);

    private final RestTemplate restTemplate;
    private final int chatId;
    private final String botId;

    TelegramGameNotifier(RestTemplate restTemplate, @Value("${telegram.bot_id}") String botId, @Value("${telegram.chat_id}") int chatId) {
        this.restTemplate = restTemplate;
        this.chatId = chatId;
        this.botId = botId;
    }

    @Override
    public void send(GameStat gameStat) {
        log.info("notify Game: {}", gameStat.toString().split("\n")[0]);

        URI uri = UriComponentsBuilder
            .fromUriString("https://api.telegram.org/bot")
            .path("{bot_id}/sendMessage")
            .queryParam("chat_id", chatId)
            .queryParam("parse_mode", "html")
            .queryParam("text", "<pre>" + gameStat + "</pre>")
            .buildAndExpand(botId)
            .toUri();

        restTemplate.getForEntity(uri, Object.class);
    }
}
