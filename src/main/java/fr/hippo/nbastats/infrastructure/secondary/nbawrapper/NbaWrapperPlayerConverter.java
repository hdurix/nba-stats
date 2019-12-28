package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.boxscore.ActivePlayer;
import fr.hippo.nbastats.domain.Fouls;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.TwoDigitNumber;
import fr.hippo.nbastats.domain.TwoValues;
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
            .points(new TwoDigitNumber(parseInt(player.getPoints())))
            .rebounds(new TwoDigitNumber(parseInt(player.getTotReb())))
            .assists(new TwoDigitNumber(parseInt(player.getAssists())))
            .blocks(new TwoDigitNumber(parseInt(player.getBlocks())))
            .steals(new TwoDigitNumber(parseInt(player.getSteals())))
            .fieldGoals(new TwoValues(parseInt(player.getFgm()), parseInt(player.getFga())))
            .threePoints(new TwoValues(parseInt(player.getTpm()), parseInt(player.getTpa())))
            .freeThrows(new TwoValues(parseInt(player.getFtm()), parseInt(player.getFta())))
            .turnovers(new TwoDigitNumber(parseInt(player.getTurnovers())))
            .minutes(new TwoDigitNumber(parseInt(player.getMin().split(":")[0])))
            .build();
    }

    private Integer parseInt(String input) {
        return Integer.parseInt(input);
    }
}
