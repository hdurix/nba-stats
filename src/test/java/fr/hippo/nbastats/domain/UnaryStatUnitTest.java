package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnaryStatUnitTest {

    @Test
    void shouldGetValue() {
        assertThat(new UnaryStat(42).value()).isEqualTo(42);
    }

    @Test
    void shouldHaveTwoDigitToStringWithoutSpace() {
        assertThat(new UnaryStat(42)).hasToString("42");
    }

    @Test
    void shouldHaveNegativeTwoDigitToStringWithoutSpace() {
        assertThat(new UnaryStat(-1)).hasToString("-1");
    }

    @Test
    void shouldHaveOneDigitToStringWithSpaceWithSpace() {
        assertThat(new UnaryStat(8)).hasToString(" 8");
    }
}
