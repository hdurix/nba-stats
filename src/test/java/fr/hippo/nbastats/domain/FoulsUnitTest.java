package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FoulsUnitTest {

    @Test
    void shouldHaveEmptyToStringForLowFouls() {
        assertThat(new Fouls(2)).hasToString(" ");
    }

    @Test
    void shouldHaveMarkedToStringForMaxFouls() {
        assertThat(new Fouls(6)).hasToString("*");
    }
}
