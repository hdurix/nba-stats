package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.JsonHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class ApiScoreboardWrapperUnitTest {

    @Test
    void shouldReadFromJson() {
        ApiScoreboardWrapper scoreboard = JsonHelper.readFromJson(defaultJson(), ApiScoreboardWrapper.class);

        ApiScoreboardGame game = scoreboard.getScoreboard().getGames().iterator().next();
        assertThat(game.getGameId()).isEqualTo("0022200003");
        assertThat(game.getGameCode()).isEqualTo("20221019/ORLDET");
        assertThat(game.getGameStatus()).isEqualTo(3);
        assertThat(game.getHomeTeam().getTeamId()).isEqualTo("1610612765");
        assertThat(game.getHomeTeam().getScore()).isEqualTo(113);
        assertThat(game.getAwayTeam().getTeamId()).isEqualTo("1610612753");
        assertThat(game.getAwayTeam().getScore()).isEqualTo(109);
    }

    private String defaultJson() {
        try {
            return Files.readString(Path.of("src/test/resources/fixtures/api-scoreboard.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
