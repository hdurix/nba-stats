package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatTupleUnitTest {

    @Test
    void shouldGetValues() {
        StatTuple statTuple = new StatTuple(2, 12);

        assertThat(statTuple.getSuccess()).isEqualTo(2);
        assertThat(statTuple.getTotal()).isEqualTo(12);
        assertThat(statTuple.getMissed()).isEqualTo(10);
    }

    @Test
    void shouldToStringWithTwoLowValuesHaveTwoSpaces() {
        assertThat(new StatTuple(2, 2)).hasToString(" 2/2 ");
    }

    @Test
    void shouldToStringWithHighTotalHaveOneSpace() {
        assertThat(new StatTuple(2, 12)).hasToString(" 2/12");
    }

    @Test
    void shouldToStringWithHighSuccessHaveNoSpace() {
        assertThat(new StatTuple(11, 12)).hasToString("11/12");
    }
}
