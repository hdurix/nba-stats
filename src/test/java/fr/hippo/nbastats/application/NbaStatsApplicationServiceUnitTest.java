package fr.hippo.nbastats.application;

import static org.mockito.Mockito.*;

import fr.hippo.nbastats.domain.GameNotifier;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.GameStatUnitTest;
import fr.hippo.nbastats.domain.Games;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaStatsApplicationServiceUnitTest {

    @Mock
    private Games games;

    @Mock
    private GameNotifier notifier;

    @InjectMocks
    private NbaStatsApplicationService service;

    @Test
    void shouldNotNotifyIfNoGame() {
        service.notifyNextGame();

        verify(notifier, never()).send(any());
    }

    @Test
    void shouldNotifyGame() {
        GameStat gameStat = GameStatUnitTest.defaultGameStat();
        when(games.findUnreleased()).thenReturn(Optional.of(gameStat));

        service.notifyNextGame();

        verify(notifier).send(gameStat);
    }
}
