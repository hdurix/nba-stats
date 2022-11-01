package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.JsonHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class ApiBoxscoreUnitTest {

    @Test
    void shouldReadFromJson() {
        ApiBoxscore scoreboard = JsonHelper.readFromJson(defaultJson(), ApiBoxscore.class);

        ApiBoxscoreGame game = scoreboard.getGame();

        ApiBoxscoreTeam homeTeam = game.getHomeTeam();
        ApiBoxscorePlayer player = homeTeam.getPlayers().iterator().next();
        assertThat(player.getPersonId()).isEqualTo(1630180);
        assertThat(player.getFirstName()).isEqualTo("Saddiq");
        assertThat(player.getFamilyName()).isEqualTo("Bey");
        ApiBoxscorePlayerStatistics statistics = player.getStatistics();
        assertThat(statistics.getFoulsPersonal()).isEqualTo(2);
        assertThat(statistics.getPoints()).isEqualTo(8);
        assertThat(statistics.getReboundsTotal()).isEqualTo(6);
        assertThat(statistics.getAssists()).isEqualTo(3);
        assertThat(statistics.getBlocks()).isEqualTo(0);
        assertThat(statistics.getSteals()).isEqualTo(1);
        assertThat(statistics.getFieldGoalsMade()).isEqualTo(2);
        assertThat(statistics.getFieldGoalsAttempted()).isEqualTo(5);
        assertThat(statistics.getThreePointersMade()).isEqualTo(0);
        assertThat(statistics.getThreePointersAttempted()).isEqualTo(2);
        assertThat(statistics.getFreeThrowsMade()).isEqualTo(4);
        assertThat(statistics.getFreeThrowsAttempted()).isEqualTo(4);
        assertThat(statistics.getTurnovers()).isEqualTo(1);
        assertThat(statistics.getMinutesCalculated()).isEqualTo("PT31M");

        assertThat(game.getAwayTeam()).isNotNull();
    }

    private String defaultJson() {
        try {
            return Files.readString(Path.of("src/test/resources/fixtures/api-boxscore.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
