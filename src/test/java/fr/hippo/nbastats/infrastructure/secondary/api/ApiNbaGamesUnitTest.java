package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.hippo.nbastats.domain.GameStatUnitTest;
import java.time.LocalDate;
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
        when(scoreboards.forDate(yesterday)).thenReturn(buildScoreboard(game("01", 3), game("02", 3)));

        LocalDate today = LocalDate.now();
        when(scoreboards.forDate(today)).thenReturn(buildScoreboard(game("11", 2), game("12", 3), game("13", 1)));
    }

    private ApiScoreboard buildScoreboard(ApiModule... modules) {
        ApiScoreboard scoreboard = new ApiScoreboard();
        scoreboard.setModules(Arrays.asList(modules));
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
        // First pass: both "02" and "12" seen and added to awaitingStableRecord
        assertThat(games.findUnreleased()).isEmpty();

        // Second pass: "02" passes recordStabilized() and gets released
        release();

        // Third pass: "12" passes recordStabilized() and gets released
        release();

        // No more unreleased games
        assertThat(games.findUnreleased()).isEmpty();

        verify(releasedGames).add("02");
        verify(releasedGames).add("12");
    }

    private void release() {
        assertThat(games.findUnreleased()).isPresent();
    }

    private ApiModule game(String gameId, int status) {
        ApiScoreboardGame gameDetails = new ApiScoreboardGame();
        gameDetails.setGameId(gameId);
        gameDetails.setGameStatus(status);

        ApiCard card = new ApiCard();
        card.setCardData(gameDetails);

        ApiModule module = new ApiModule();
        module.setCards(List.of(card));
        return module;
    }
}
