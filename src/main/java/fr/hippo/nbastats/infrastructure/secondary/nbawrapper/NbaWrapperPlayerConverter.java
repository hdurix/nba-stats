package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static java.lang.Integer.*;

import com.drmilk.nbawrapper.domain.utils.boxscore.ActivePlayer;
import fr.hippo.nbastats.domain.BinaryStat;
import fr.hippo.nbastats.domain.Fouls;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.UnaryStat;
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
            .fouls(new Fouls(parseInt(player.getPFouls())))
            .points(new UnaryStat(parseInt(player.getPoints())))
            .rebounds(new UnaryStat(parseInt(player.getTotReb())))
            .assists(new UnaryStat(parseInt(player.getAssists())))
            .blocks(new UnaryStat(parseInt(player.getBlocks())))
            .steals(new UnaryStat(parseInt(player.getSteals())))
            .fieldGoals(new BinaryStat(parseInt(player.getFgm()), parseInt(player.getFga())))
            .threePoints(new BinaryStat(parseInt(player.getTpm()), parseInt(player.getTpa())))
            .freeThrows(new BinaryStat(parseInt(player.getFtm()), parseInt(player.getFta())))
            .turnovers(new UnaryStat(parseInt(player.getTurnovers())))
            .minutes(new UnaryStat(parseInt(player.getMin().split(":")[0])))
            .build();
    }
}
