package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hippo.nbastats.config.StatFilterProperties;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.PlayerStatUnitTest;
import fr.hippo.nbastats.domain.StatFilterUnitTest;
import fr.hippo.nbastats.domain.TeamName;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NbaWrapperGameConverterUnitTest {
    @Value("classpath:fixtures/game.json")
    private Resource resource;

    @Mock
    private NbaWrapperTeams teams;

    @Mock
    private NbaWrapperBoxscoreConverter boxscoreConverter;

    @Mock
    private StatFilterProperties statFilterProperties;

    @InjectMocks
    private NbaWrapperGameConverter converter;

    @Test
    public void shouldConvertGame() throws IOException {
        GameDetails gameDetails = getGameDetails();
        Boxscore boxscore = new Boxscore();
        when(teams.findById("1610612738")).thenReturn(TeamName.BOSTON);
        when(teams.findById("1610612739")).thenReturn(TeamName.CLEVELAND);
        when(boxscoreConverter.extractStatForTeam(boxscore, "1610612738")).thenReturn(List.of(PlayerStatUnitTest.brookLopez()));
        when(boxscoreConverter.extractStatForTeam(boxscore, "1610612739")).thenReturn(List.of(PlayerStatUnitTest.jeremyLamb()));
        when(statFilterProperties.statFilter()).thenReturn(StatFilterUnitTest.empty());

        GameStat gameStat = converter.toDomain(gameDetails, boxscore);

        assertThat(gameStat)
            .hasToString(
                " Cavaliers 117 - Celtics 129 \n" +
                "\n" +
                "---- Cleveland Cavaliers ----\n" +
                "J. Lamb    *44|10  8 10  4  0\n" +
                " 9/12 10/13  4/5 | 4|34'\n" +
                "\n" +
                "------ Boston Celtics -------\n" +
                "B. Lopez   *76|42  8 10  4  0\n" +
                " 9/12 10/13  4/5 | 4|34'"
            );
    }

    private GameDetails getGameDetails() throws IOException {
        return new ObjectMapper().readValue(defaultJson(), GameDetails.class);
    }

    private String defaultJson() throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
