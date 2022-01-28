package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EvaluationUnitTest {

    @Test
    void shouldGetValue() {
        assertThat(new Evaluation(42).value()).isEqualTo(42);
    }

    @Test
    void shouldHaveTwoDigitToStringWithoutSpace() {
        assertThat(new Evaluation(42)).hasToString("42");
    }

    @Test
    void shouldHaveNegativeTwoDigitToStringWithoutSpace() {
        assertThat(new Evaluation(-1)).hasToString("-1");
    }

    @Test
    void shouldHaveOneDigitToStringWithSpaceWithSpace() {
        assertThat(new Evaluation(8)).hasToString(" 8");
    }
}
