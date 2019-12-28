package fr.hippo.nbastats.domain;

import static fr.hippo.nbastats.domain.TeamStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStatUnitTest {

    @Test
    public void shouldNotBuildWithoutAwayTeam() {
        assertThatThrownBy(() -> new GameStat(null, detroit()))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("away");
    }

    @Test
    public void shouldNotBuildWithoutHomeTeam() {
        assertThatThrownBy(() -> new GameStat(indiana(), null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("home");
    }

    @Test
    public void shouldHaveFullToString() {
        assertThat(defaultGameStat()).hasToString("Pacers 123 - Pistons 124\n\n" + indiana() + "\n\n" + detroit());
    }

    public static GameStat defaultGameStat() {
        return new GameStat(indiana(), detroit());
    }
}
