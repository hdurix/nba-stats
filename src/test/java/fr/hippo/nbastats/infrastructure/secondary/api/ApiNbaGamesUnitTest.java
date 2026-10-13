package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.hippo.nbastats.JsonHelper;
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
        when(scoreboards.forDate(today)).thenReturn(buildScoreboard(game("11", 2), game("12", 3), game("13", 1), foreignTeamGame("14")));
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
        release();
        release();

        assertThat(games.findUnreleased()).isEmpty();
        verify(releasedGames).add("02");
        verify(releasedGames).add("12");
    }

    @Test
    void shouldIgnoreGamesAgainstNonNbaTeams() {
        games.findUnreleased();
        games.findUnreleased();
        games.findUnreleased();

        verify(releasedGames, never()).add("14");
        verify(scoreboards, never()).boxscoreForGame("14");
    }

    private void release() {
        assertThat(games.findUnreleased()).isPresent();
    }

    private ApiModule game(String gameId, int status) {
        return module(gameId, status, "1610612743", "1610612738");
    }

    private ApiModule foreignTeamGame(String gameId) {
        return module(gameId, 3, "1610612757", "50015");
    }

    private ApiModule module(String gameId, int status, String homeTeamId, String awayTeamId) {
        String json = String.format(
            "{\"gameId\": \"%s\", \"gameStatus\": %d, \"homeTeam\": {\"teamId\": \"%s\"}, \"awayTeam\": {\"teamId\": \"%s\"}}",
            gameId,
            status,
            homeTeamId,
            awayTeamId
        );
        ApiScoreboardGame gameDetails = JsonHelper.readFromJson(json, ApiScoreboardGame.class);

        ApiCard card = new ApiCard();
        card.setCardData(gameDetails);

        ApiModule module = new ApiModule();
        module.setCards(List.of(card));
        return module;
    }
}
