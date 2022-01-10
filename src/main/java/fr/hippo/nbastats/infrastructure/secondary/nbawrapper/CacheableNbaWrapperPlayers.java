package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import fr.hippo.nbastats.domain.Identity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "players")
class CacheableNbaWrapperPlayers {

    private final NbaWrapperPlayers nbaWrapperPlayers;

    CacheableNbaWrapperPlayers(NbaWrapperPlayers nbaWrapperPlayers) {
        this.nbaWrapperPlayers = nbaWrapperPlayers;
    }

    @Cacheable(unless = "#result.isUnknown()")
    public Identity getIdentityFromPlayerId(String playerId) {
        return nbaWrapperPlayers.getIdentityFromPlayerId(playerId);
    }
}
