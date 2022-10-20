package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.domain.TeamName;
import org.junit.jupiter.api.Test;

class NbaTeamIdsUnitTest {

    @Test
    void shouldGetTeamNameFromWrapperId() {
        TeamName teamName = NbaTeamIds.findById("1610612743");

        assertThat(teamName).isEqualTo(TeamName.DENVER);
    }
}
