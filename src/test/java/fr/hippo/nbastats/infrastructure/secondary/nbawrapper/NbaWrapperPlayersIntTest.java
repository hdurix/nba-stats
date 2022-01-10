package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static fr.hippo.nbastats.domain.IdentityUnitTest.defaultIdentity;
import static fr.hippo.nbastats.domain.IdentityUnitTest.unknownIdentity;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

@SpringBootTest(properties = { "telegram.bot_id=dummy", "telegram.chat_id=dummy" })
class CacheableNbaWrapperPlayersIntTest {

    @Autowired
    private CacheableNbaWrapperPlayers cacheablePlayers;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private NbaWrapperPlayers players;

    @BeforeEach
    public void setup() {
        cacheManager.getCache("players").clear();
    }

    @Test
    void shouldNotCacheResultForUnknownPlayer() {
        when(players.getIdentityFromPlayerId("1234")).thenReturn(unknownIdentity());

        cacheablePlayers.getIdentityFromPlayerId("1234");
        cacheablePlayers.getIdentityFromPlayerId("1234");

        verify(players, times(2)).getIdentityFromPlayerId("1234");
    }

    @Test
    void shouldCacheResult() {
        when(players.getIdentityFromPlayerId("1234")).thenReturn(defaultIdentity());

        cacheablePlayers.getIdentityFromPlayerId("1234");
        cacheablePlayers.getIdentityFromPlayerId("1234");

        verify(players).getIdentityFromPlayerId("1234");
    }
}
