package fr.hippo.nbastats.application;

import fr.hippo.nbastats.domain.GameNotifier;
import fr.hippo.nbastats.domain.Games;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NbaStatsApplicationService {
    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;

    private static final Logger log = LoggerFactory.getLogger(NbaStatsApplicationService.class);

    private final Games games;
    private final GameNotifier notifier;

    public NbaStatsApplicationService(Games games, GameNotifier notifier) {
        this.games = games;
        this.notifier = notifier;
    }

    @Scheduled(initialDelay = ONE_SECOND, fixedDelay = ONE_MINUTE)
    public void notifyNextGame() {
        log.debug("Check if a new game has to be notified");

        games.findUnreleased().ifPresent(notifier::send);
    }
}
