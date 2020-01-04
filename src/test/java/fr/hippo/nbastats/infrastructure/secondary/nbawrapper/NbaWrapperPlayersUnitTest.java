package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.domain.Identity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NbaWrapperPlayersUnitTest {
    @InjectMocks
    private NbaWrapperPlayers players;

    @Test
    public void shouldGetPlayerIdentityFromWrapper() {
        Identity identity = players.getIdentityFromPlayerId("201567");

        assertThat(identity).hasToString("K. Love    ");
    }

    @Test
    public void shouldGetUnknownPlayerIdentityFromWrapper() {
        Identity identity = players.getIdentityFromPlayerId("000000");

        assertThat(identity).hasToString("?. ???     ");
    }
}
