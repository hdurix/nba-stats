package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.ActivePlayer;
import fr.hippo.nbastats.domain.BinaryStat;
import fr.hippo.nbastats.domain.Fouls;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.UnaryStat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperPlayerConverter {
    private final NbaWrapperPlayers players;

    public NbaWrapperPlayerConverter(NbaWrapperPlayers players) {
        this.players = players;
    }

    public PlayerStat toDomain(ActivePlayer player) {
        Identity identity = players.getIdentityFromPlayerId(player.getPersonId());

        return PlayerStat
            .builder()
            .identity(identity)
            .fouls(new Fouls(parseStat(player.getPFouls())))
            .points(new UnaryStat(parseStat(player.getPoints())))
            .rebounds(new UnaryStat(parseStat(player.getTotReb())))
            .assists(new UnaryStat(parseStat(player.getAssists())))
            .blocks(new UnaryStat(parseStat(player.getBlocks())))
            .steals(new UnaryStat(parseStat(player.getSteals())))
            .fieldGoals(new BinaryStat(parseStat(player.getFgm()), parseStat(player.getFga())))
            .threePoints(new BinaryStat(parseStat(player.getTpm()), parseStat(player.getTpa())))
            .freeThrows(new BinaryStat(parseStat(player.getFtm()), parseStat(player.getFta())))
            .turnovers(new UnaryStat(parseStat(player.getTurnovers())))
            .minutes(new UnaryStat(parseMinutes(player.getMin())))
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
