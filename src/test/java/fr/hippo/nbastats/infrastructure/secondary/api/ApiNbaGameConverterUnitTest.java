package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.hippo.nbastats.JsonHelper;
import fr.hippo.nbastats.config.StatFilterProperties;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.StatFilterUnitTest;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ApiNbaGameConverterUnitTest {

    @Value("classpath:fixtures/api-scoreboard.json")
    private Resource scoreboardResource;

    @Value("classpath:fixtures/api-boxscore.json")
    private Resource boxscoreResource;

    @Mock
    private StatFilterProperties statFilterProperties;

    @InjectMocks
    private ApiNbaGameConverter converter;

    private ApiScoreboardGame scoreboardGame;
    private ApiBoxscoreGame boxscoreGame;

    @BeforeEach
    void initMocks() throws IOException {
        scoreboardGame = getScoreboardGame();
        boxscoreGame = getBoxscoreGame();
        when(statFilterProperties.statFilter()).thenReturn(StatFilterUnitTest.empty());
    }

    @Test
    void shouldConvertGame() {
        GameStat gameStat = converter.toDomain(scoreboardGame, boxscoreGame);

        assertThat(gameStat)
            .hasToString(
                "   Magic 109 - Pistons 113   \n" +
                "\n" +
                "------- Orlando Magic -------\n" +
                "P. Banchero 46|27  9  5  2  0\n" +
                "11/18  0/0   5/7 | 4|35'\n" +
                "\n" +
                "J. Suggs   *31|21  1  3  0  2\n" +
                " 8/11  4/6   1/1 | 4|25'\n" +
                "\n" +
                "W. Carter J 26|11 11  3  1  1\n" +
                " 5/8   1/2   0/0 | 3|33'\n" +
                "\n" +
                "F. Wagner   22|20  4  5  0  0\n" +
                " 8/18  2/6   2/2 | 5|34'\n" +
                "\n" +
                "B. Bol      19|10  6  0  1  0\n" +
                " 4/6   0/0   2/2 | 2|18'\n" +
                "\n" +
                "R. Hampton  15| 5  4  2  0  1\n" +
                " 1/2   1/1   2/2 | 0|11'\n" +
                "\n" +
                "T. Ross     14|13  4  1  0  1\n" +
                " 5/12  3/8   0/1 | 0|32'\n" +
                "\n" +
                "C. Okeke     5| 2  4  1  0  0\n" +
                " 0/3   0/1   2/2 | 0|18'\n" +
                "\n" +
                "C. Houstan  -1| 0  3  0  1  0\n" +
                " 0/3   0/2   0/0 | 0|22'\n" +
                "\n" +
                "M. Bamba    -8| 0  2  1  0  0\n" +
                " 0/5   0/4   0/2 | 0|12'\n" +
                "\n" +
                "------ Detroit Pistons ------\n" +
                "B. Bogdanov 35|24  5  2  0  1\n" +
                " 8/16  6/10  2/2 | 1|35'\n" +
                "\n" +
                "J. Ivey     29|19  3  4  0  3\n" +
                " 8/15  2/4   1/1 | 2|32'\n" +
                "\n" +
                "I. Stewart  28|14  5  3  0  2\n" +
                " 3/6   1/4   7/8 | 0|26'\n" +
                "\n" +
                "C. Cunningh 25|18  1 10  0  1\n" +
                " 6/16  2/6   4/4 | 3|35'\n" +
                "\n" +
                "J. Duren    22|14 10  1  3  0\n" +
                " 7/13  0/0   0/4 | 3|22'\n" +
                "\n" +
                "S. Bey      18| 8  6  3  0  1\n" +
                " 2/5   0/2   4/4 | 1|31'\n" +
                "\n" +
                "C. Joseph   16| 8  1  2  0  0\n" +
                " 3/3   2/2   0/0 | 0|15'\n" +
                "\n" +
                "K. Hayes     8| 3  5  5  1  3\n" +
                " 1/9   0/3   1/1 | 0|16'\n" +
                "\n" +
                "H. Diallo    3| 2  2  1  0  0\n" +
                " 1/3   0/1   0/0 | 0|15'\n" +
                "\n" +
                "K. Knox II  -6| 3  3  0  0  0\n" +
                " 1/8   1/6   0/0 | 2|13'"
            );
    }

    private ApiBoxscoreGame getBoxscoreGame() throws IOException {
        return JsonHelper.readFromJson(defaultBoxscoreJson(), ApiBoxscore.class).getGame();
    }

    private ApiScoreboardGame getScoreboardGame() throws IOException {
        return JsonHelper.readFromJson(defaultScoreboardJson(), ApiScoreboardWrapper.class).getScoreboard().getGames().iterator().next();
    }

    private String defaultBoxscoreJson() throws IOException {
        return new String(Files.readAllBytes(boxscoreResource.getFile().toPath()));
    }

    private String defaultScoreboardJson() throws IOException {
        return new String(Files.readAllBytes(scoreboardResource.getFile().toPath()));
    }
}
