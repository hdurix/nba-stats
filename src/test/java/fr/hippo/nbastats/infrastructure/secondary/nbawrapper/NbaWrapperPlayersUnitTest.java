package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.domain.Identity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperPlayersUnitTest {

    @InjectMocks
    private NbaWrapperPlayers players;

    @Test
    void shouldGetPlayerIdentityFromWrapper() {
        Identity identity = players.getIdentityFromPlayerId("201567");

        assertThat(identity.getId()).isEqualTo(201567);
        assertThat(identity).hasToString("K. Love    ");
    }

    @Test
    void shouldGetUnknownPlayerIdentityFromWrapper() {
        Identity identity = players.getIdentityFromPlayerId("000000");

        assertThat(identity.getId()).isEqualTo(0);
        assertThat(identity).hasToString("?. ???     ");
    }
}
