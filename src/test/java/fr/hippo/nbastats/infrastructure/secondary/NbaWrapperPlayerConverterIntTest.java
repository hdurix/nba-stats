package fr.hippo.nbastats.infrastructure.secondary;

import com.drmilk.nbawrapper.domain.League;
import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.drmilk.nbawrapper.domain.utils.scoreboard.Scoreboard;
import com.drmilk.nbawrapper.exception.BoxscoreNotFoundException;
import com.drmilk.nbawrapper.exception.ScoreboardNotFoundException;
import fr.hippo.nbastats.domain.MatchStat;
import fr.hippo.nbastats.domain.TeamStat;
import fr.hippo.nbastats.infrastructure.secondary.nbawrapper.NbaWrapperBoxscoreConverter;
import fr.hippo.nbastats.infrastructure.secondary.nbawrapper.NbaWrapperTeams;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

@SpringBootTest
@Disabled("Only temporary")
public class NbaWrapperPlayerConverterIntTest {
    @Autowired
    private NbaWrapperTeams teamConverter;

    @Autowired
    private NbaWrapperBoxscoreConverter boxscoreConverter;

    @Test
    public void shouldGetSampleMatch() throws ScoreboardNotFoundException, BoxscoreNotFoundException {
        Scoreboard scoreboard = League.getScoreboard(27, 12, 2019);

        List<GameDetails> games = scoreboard.getGames();

        System.out.println(games);

        GameDetails game = games.get(0);

        Boxscore boxscore = League.getBoxscore(27, 12, 2019, game.getGameId());

        TeamStat away = new TeamStat(
            teamConverter.findById(game.getVisitingTeamScore().getTeamId()),
            parseInt(game.getVisitingTeamScore().getScore()),
            boxscoreConverter.extractStatForTeam(boxscore, game.getVisitingTeamScore().getTeamId())
        );
        TeamStat home = new TeamStat(
            teamConverter.findById(game.getHomeTeamScore().getTeamId()),
            parseInt(game.getHomeTeamScore().getScore()),
            boxscoreConverter.extractStatForTeam(boxscore, game.getHomeTeamScore().getTeamId())
        );
        System.out.println(new MatchStat(away, home));
    }

    private Integer parseInt(String input) {
        if (StringUtils.isEmpty(input)) {
            return 0;
        }
        return (int) Double.parseDouble(input);
    }
}
