package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.drmilk.nbawrapper.domain.utils.scoreboard.Scoreboard;
import fr.hippo.nbastats.domain.GameStatUnitTest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperGamesUnitTest {
    @Mock
    private NbaWrapperScoreboards scoreboards;

    @Mock
    private NbaWrapperBoxscores boxscores;

    @Mock
    private NbaWrapperGameConverter gameConverter;

    @InjectMocks
    private NbaWrapperGames games;

    @BeforeEach
    private void mockScoreboard() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        when(scoreboards.forDate(yesterday)).thenReturn(buildScoreboard(yesterday, gameDetails("10", yesterday, 3)));

        LocalDate today = LocalDate.now();
        when(scoreboards.forDate(today))
            .thenReturn(buildScoreboard(today, gameDetails("11", today, 2), gameDetails("12", today, 3), gameDetails("13", today, 1)));
    }

    private Scoreboard buildScoreboard(LocalDate gameDate, GameDetails... games) {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setGames(Arrays.asList(games));
        return scoreboard;
    }

    @BeforeEach
    private void mockBoxscores() {
        when(boxscores.findByGameId(LocalDate.now().minusDays(1), "10")).thenReturn(new Boxscore());
        when(boxscores.findByGameId(LocalDate.now(), "12")).thenReturn(new Boxscore());
    }

    @BeforeEach
    private void mockGameConverter() {
        when(gameConverter.toDomain(any(GameDetails.class), any(Boxscore.class))).thenReturn(GameStatUnitTest.defaultGameStat());
    }

    @Test
    public void shouldFindTwoUnreleasedGames() {
        assertThat(games.findUnreleased()).isPresent();
        assertThat(games.findUnreleased()).isPresent();

        assertThat(games.findUnreleased()).isEmpty();
    }

    private GameDetails gameDetails(String gameId, LocalDate gameDate, int status) {
        GameDetails gameDetails = new GameDetails();
        gameDetails.setGameId(gameId);
        gameDetails.setAdditionalProperty("statusNum", status);
        gameDetails.setAdditionalProperty("gameUrlCode", gameDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "/url");
        return gameDetails;
    }
}
