package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.drmilk.nbawrapper.domain.utils.scoreboard.TeamScoreDetails;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.TeamStat;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperGameConverter {
    private final NbaWrapperTeams teams;
    private final NbaWrapperBoxscoreConverter boxscoreConverter;

    public NbaWrapperGameConverter(NbaWrapperTeams teams, NbaWrapperBoxscoreConverter boxscoreConverter) {
        this.teams = teams;
        this.boxscoreConverter = boxscoreConverter;
    }

    public GameStat toDomain(GameDetails gameDetails, Boxscore boxscore) {
        TeamStat away = toTeamStat(boxscore, gameDetails.getVisitingTeamScore());
        TeamStat home = toTeamStat(boxscore, gameDetails.getHomeTeamScore());
        return new GameStat(away, home);
    }

    private TeamStat toTeamStat(Boxscore boxscore, TeamScoreDetails teamScore) {
        return new TeamStat(
            teams.findById(teamScore.getTeamId()),
            Integer.parseInt(teamScore.getScore()),
            boxscoreConverter.extractStatForTeam(boxscore, teamScore.getTeamId())
        );
    }
}
