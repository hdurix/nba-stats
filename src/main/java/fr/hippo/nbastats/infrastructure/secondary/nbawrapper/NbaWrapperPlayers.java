package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.Player;
import com.drmilk.nbawrapper.domain.PlayerMin;
import com.drmilk.nbawrapper.exception.PlayerNotFoundException;
import fr.hippo.nbastats.domain.Identity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "players")
class NbaWrapperPlayers {

    @Cacheable
    Identity getIdentityFromPlayerId(String playerId) {
        PlayerMin player;
        try {
            player = Player.getPlayerMinById(playerId);
        } catch (PlayerNotFoundException e) {
            return new Identity(Integer.parseInt(playerId), "?", "???");
        }
        return new Identity(Integer.parseInt(playerId), player.getFirstName(), player.getLastName());
    }
}
