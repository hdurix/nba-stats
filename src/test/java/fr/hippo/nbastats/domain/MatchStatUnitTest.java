package fr.hippo.nbastats.domain;

import static fr.hippo.nbastats.domain.TeamStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MatchStatUnitTest {

    @Test
    public void shouldNotBuildWithoutAwayTeam() {
        assertThatThrownBy(() -> new MatchStat(null, detroit()))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("away");
    }

    @Test
    public void shouldNotBuildWithoutHomeTeam() {
        assertThatThrownBy(() -> new MatchStat(indiana(), null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("home");
    }

    @Test
    public void shouldHaveFullToString() {
        assertThat(defaultMatchStat()).hasToString("Pacers 123 - Pistons 124\n\n" + indiana() + "\n\n" + detroit());
    }

    private MatchStat defaultMatchStat() {
        return new MatchStat(indiana(), detroit());
    }
}
