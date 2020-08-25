package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryStatUnitTest {

    @Test
    void shouldGetValues() {
        BinaryStat binaryStat = new BinaryStat(2, 12);

        assertThat(binaryStat.getSuccess()).isEqualTo(2);
        assertThat(binaryStat.getTotal()).isEqualTo(12);
        assertThat(binaryStat.getMissed()).isEqualTo(10);
    }

    @Test
    void shouldToStringWithTwoLowValuesHaveTwoSpaces() {
        assertThat(new BinaryStat(2, 2)).hasToString(" 2/2 ");
    }

    @Test
    void shouldToStringWithHighTotalHaveOneSpace() {
        assertThat(new BinaryStat(2, 12)).hasToString(" 2/12");
    }

    @Test
    void shouldToStringWithHighSuccessHaveNoSpace() {
        assertThat(new BinaryStat(11, 12)).hasToString("11/12");
    }
}
