package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TwoDigitNumberUnitTest {

    @Test
    public void shouldGetValue() {
        assertThat(new TwoDigitNumber(42).value()).isEqualTo(42);
    }

    @Test
    public void shouldHaveTwoDigitToStringWithoutSpace() {
        assertThat(new TwoDigitNumber(42)).hasToString("42");
    }

    @Test
    public void shouldHaveNegativeTwoDigitToStringWithoutSpace() {
        assertThat(new TwoDigitNumber(-1)).hasToString("-1");
    }

    @Test
    public void shouldHaveOneDigitToStringWithSpaceWithSpace() {
        assertThat(new TwoDigitNumber(8)).hasToString(" 8");
    }
}
