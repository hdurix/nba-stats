package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FoulsUnitTest {

    @Test
    public void shouldHaveEmptyToStringForLowFouls() {
        assertThat(new Fouls(2)).hasToString(" ");
    }

    @Test
    public void shouldHaveMarkedToStringForMaxFouls() {
        assertThat(new Fouls(6)).hasToString("*");
    }
}
