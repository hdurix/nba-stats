package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.Player;
import com.drmilk.nbawrapper.domain.PlayerMin;
import com.drmilk.nbawrapper.exception.PlayerNotFoundException;
import fr.hippo.nbastats.domain.Identity;
import org.springframework.stereotype.Repository;

@Repository
class NbaWrapperPlayers {

    Identity getIdentityFromPlayerId(String playerId) {
        PlayerMin player;
        try {
            player = Player.getPlayerMinById(playerId);
        } catch (PlayerNotFoundException e) {
            return new Identity(Integer.parseInt(playerId));
        }
        return new Identity(Integer.parseInt(playerId), player.getFirstName(), player.getLastName());
    }
}
