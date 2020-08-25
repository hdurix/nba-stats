package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.drmilk.nbawrapper.domain.utils.scoreboard.TeamScoreDetails;
import fr.hippo.nbastats.config.StatFilterProperties;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.TeamStat;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperGameConverter {
    private final NbaWrapperTeams teams;
    private final NbaWrapperBoxscoreConverter boxscoreConverter;
    private final StatFilterProperties statFilterProperties;

    NbaWrapperGameConverter(
        NbaWrapperTeams teams,
        NbaWrapperBoxscoreConverter boxscoreConverter,
        StatFilterProperties statFilterProperties
    ) {
        this.teams = teams;
        this.boxscoreConverter = boxscoreConverter;
        this.statFilterProperties = statFilterProperties;
    }

    GameStat toDomain(GameDetails gameDetails, Boxscore boxscore) {
        TeamStat away = toTeamStat(boxscore, gameDetails.getVisitingTeamScore());
        TeamStat home = toTeamStat(boxscore, gameDetails.getHomeTeamScore());
        return new GameStat(away, home);
    }

    private TeamStat toTeamStat(Boxscore boxscore, TeamScoreDetails teamScore) {
        return TeamStat
            .builder()
            .filter(statFilterProperties.statFilter())
            .name(teams.findById(teamScore.getTeamId()))
            .score(Integer.parseInt(teamScore.getScore()))
            .players(boxscoreConverter.extractStatForTeam(boxscore, teamScore.getTeamId()))
            .build();
    }
}
