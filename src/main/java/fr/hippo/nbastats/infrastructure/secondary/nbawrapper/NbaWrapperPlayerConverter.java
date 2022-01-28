package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.ActivePlayer;
import fr.hippo.nbastats.domain.Fouls;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.Stat;
import fr.hippo.nbastats.domain.StatTuple;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperPlayerConverter {

    private final CacheableNbaWrapperPlayers players;

    NbaWrapperPlayerConverter(CacheableNbaWrapperPlayers players) {
        this.players = players;
    }

    PlayerStat toDomain(ActivePlayer player) {
        Identity identity = players.getIdentityFromPlayerId(player.getPersonId());

        return PlayerStat
            .builder()
            .identity(identity)
            .fouls(new Fouls(parseStat(player.getPFouls())))
            .points(new Stat(parseStat(player.getPoints())))
            .rebounds(new Stat(parseStat(player.getTotReb())))
            .assists(new Stat(parseStat(player.getAssists())))
            .blocks(new Stat(parseStat(player.getBlocks())))
            .steals(new Stat(parseStat(player.getSteals())))
            .fieldGoals(new StatTuple(parseStat(player.getFgm()), parseStat(player.getFga())))
            .threePoints(new StatTuple(parseStat(player.getTpm()), parseStat(player.getTpa())))
            .freeThrows(new StatTuple(parseStat(player.getFtm()), parseStat(player.getFta())))
            .turnovers(new Stat(parseStat(player.getTurnovers())))
            .minutes(new Stat(parseMinutes(player.getMin())))
            .build();
    }

    private int parseStat(String stat) {
        if (StringUtils.isBlank(stat)) {
            return 0;
        }
        return Integer.parseInt(stat);
    }

    private int parseMinutes(String minutes) {
        if (StringUtils.isBlank(minutes)) {
            return 0;
        }
        return parseStat(minutes.split(":")[0]);
    }
}
