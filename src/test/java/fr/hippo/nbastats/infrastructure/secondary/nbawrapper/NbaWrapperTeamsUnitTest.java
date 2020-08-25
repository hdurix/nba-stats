package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.domain.TeamName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperTeamsUnitTest {
    @InjectMocks
    private NbaWrapperTeams teams;

    @Test
    void shouldGetTeamNameFromWrapperId() {
        TeamName teamName = teams.findById("1610612743");

        assertThat(teamName).isEqualTo(TeamName.DENVER);
    }
}
