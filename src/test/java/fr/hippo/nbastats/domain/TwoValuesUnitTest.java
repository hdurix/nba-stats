package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TwoValuesUnitTest {

    @Test
    public void shouldGetValues() {
        TwoValues twoValues = new TwoValues(2, 12);
        assertThat(twoValues.getSuccess()).isEqualTo(2);
        assertThat(twoValues.getTotal()).isEqualTo(12);
        assertThat(twoValues.getMissed()).isEqualTo(10);
    }

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
