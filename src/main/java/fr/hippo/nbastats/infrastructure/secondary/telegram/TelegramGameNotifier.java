package fr.hippo.nbastats.infrastructure.secondary.telegram;

import fr.hippo.nbastats.domain.GameNotifier;
import fr.hippo.nbastats.domain.GameStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TelegramGameNotifier implements GameNotifier {
    private static final Logger log = LoggerFactory.getLogger(TelegramGameNotifier.class);

    @Override
    public void send(GameStat gameStat) {
        log.info("notify Game: {}", gameStat.toString().split("\n")[0]);

        System.out.println(gameStat);
    }
}
