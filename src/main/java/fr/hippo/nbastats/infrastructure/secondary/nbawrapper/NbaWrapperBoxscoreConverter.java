package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import fr.hippo.nbastats.domain.PlayerStat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperBoxscoreConverter {
    private final NbaWrapperPlayerConverter playerConverter;

    NbaWrapperBoxscoreConverter(NbaWrapperPlayerConverter playerConverter) {
        this.playerConverter = playerConverter;
    }

    List<PlayerStat> extractStatForTeam(Boxscore boxscore, String teamId) {
        return boxscore
            .getStats()
            .getActivePlayers()
            .stream()
            .filter(activePlayer -> activePlayer.getTeamId().equals(teamId))
            .map(playerConverter::toDomain)
            .collect(Collectors.toList());
    }
}
