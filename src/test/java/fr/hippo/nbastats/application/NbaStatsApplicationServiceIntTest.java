package fr.hippo.nbastats.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NbaStatsApplicationServiceIntTest {

    @Autowired
    private NbaStatsApplicationService service;

    @Test
    void shouldMakeFullRound() {
        service.notifyNextGame();
    }
}
