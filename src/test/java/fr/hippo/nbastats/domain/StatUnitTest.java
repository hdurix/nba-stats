package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatUnitTest {

    @Test
    void shouldNotBuildWithNegativeValue() {
        assertThatThrownBy(() -> new Stat(-1))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("stat should be positive");
    }

    @Test
    void shouldGetValue() {
        assertThat(new Stat(42).value()).isEqualTo(42);
    }

    @Test
    void shouldHaveTwoDigitToStringWithoutSpace() {
        assertThat(new Stat(42)).hasToString("42");
    }

    @Test
    void shouldHaveOneDigitToStringWithSpaceWithSpace() {
        assertThat(new Stat(8)).hasToString(" 8");
    }
}
