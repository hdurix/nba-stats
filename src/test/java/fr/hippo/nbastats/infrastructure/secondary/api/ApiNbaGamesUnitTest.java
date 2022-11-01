package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.hippo.nbastats.domain.GameStatUnitTest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ApiNbaGamesUnitTest {

    @Mock
    private ApiScoreboards scoreboards;

    @Mock
    private ReleasedGames releasedGames;

    @Mock
    private ApiNbaGameConverter gameConverter;

    @InjectMocks
    private ApiNbaGames games;

    @BeforeEach
    private void mockScoreboard() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        when(scoreboards.forDate(yesterday)).thenReturn(buildScoreboard(game("01", yesterday, 3), game("02", yesterday, 3)));

        LocalDate today = LocalDate.now();
        when(scoreboards.forDate(today)).thenReturn(buildScoreboard(game("11", today, 2), game("12", today, 3), game("13", today, 1)));
    }

    private ApiScoreboard buildScoreboard(ApiScoreboardGame... games) {
        ApiScoreboard scoreboard = new ApiScoreboard();
        scoreboard.setGames(Arrays.asList(games));
        return scoreboard;
    }

    @BeforeEach
    private void mockBoxscores() {
        when(scoreboards.boxscoreForGame("02")).thenReturn(new ApiBoxscoreGame());
        when(scoreboards.boxscoreForGame("12")).thenReturn(new ApiBoxscoreGame());
    }

    @BeforeEach
    private void mockGameConverter() {
        when(gameConverter.toDomain(any(ApiScoreboardGame.class), any(ApiBoxscoreGame.class)))
            .thenReturn(GameStatUnitTest.defaultGameStat());
    }

    @BeforeEach
    private void mockReleasedGames() {
        when(releasedGames.findAll()).thenReturn(new ArrayList<>(List.of("01")));
        games.initReleases();
    }

    @Test
    void shouldFindTwoUnreleasedGames() {
        release();
        release();

        assertThat(games.findUnreleased()).isEmpty();
        verify(releasedGames).add("02");
        verify(releasedGames).add("12");
    }

    private void release() {
        assertThat(games.findUnreleased()).isPresent();
    }

    private ApiScoreboardGame game(String gameId, LocalDate gameDate, int status) {
        ApiScoreboardGame gameDetails = new ApiScoreboardGame();
        gameDetails.setGameId(gameId);
        gameDetails.setGameStatus(status);
        gameDetails.setGameCode(gameDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "/url");
        return gameDetails;
    }
}
