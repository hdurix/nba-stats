package fr.hippo.nbastats.infrastructure.secondary.api;

import fr.hippo.nbastats.config.StatFilterProperties;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.TeamStat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
class ApiNbaGameConverter {

    private final StatFilterProperties statFilterProperties;

    ApiNbaGameConverter(StatFilterProperties statFilterProperties) {
        this.statFilterProperties = statFilterProperties;
    }

    GameStat toDomain(ApiScoreboardGame gameDetails, ApiBoxscoreGame boxscore) {
        TeamStat away = toTeamStat(boxscore.getAwayTeam(), gameDetails.getAwayTeam());
        TeamStat home = toTeamStat(boxscore.getHomeTeam(), gameDetails.getHomeTeam());
        return new GameStat(away, home);
    }

    private TeamStat toTeamStat(ApiBoxscoreTeam boxscore, ApiScoreboardTeam teamScore) {
        return TeamStat
            .builder()
            .filter(statFilterProperties.statFilter())
            .name(NbaTeamIds.findById(teamScore.getTeamId()))
            .score(teamScore.getScore())
            .wins(teamScore.getWins())
            .losses(teamScore.getLosses())
            .players(extractStat(boxscore))
            .build();
    }

    static List<PlayerStat> extractStat(ApiBoxscoreTeam boxscoreTeam) {
        return boxscoreTeam.getPlayers().stream().map(ApiBoxscorePlayer::toDomain).collect(Collectors.toList());
    }
}
