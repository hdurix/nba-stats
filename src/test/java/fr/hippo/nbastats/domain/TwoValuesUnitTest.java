package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TwoValuesUnitTest {

    @Test
    public void shouldToStringWithTwoLowValuesHaveTwoSpaces() {
        assertThat(new TwoValues(2, 2)).hasToString(" 2/2 ");
    }

    @Test
    public void shouldToStringWithHighTotalHaveOneSpace() {
        assertThat(new TwoValues(2, 12)).hasToString(" 2/12");
    }

    @Test
    public void shouldToStringWithHighSuccessHaveNoSpace() {
        assertThat(new TwoValues(11, 12)).hasToString("11/12");
    }
}
