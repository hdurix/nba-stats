package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class StatFilterUnitTest {

    @Test
    void shouldMatchWithOnlyHighEval() {
        StatFilter statFilter = new StatFilter(30, null);

        assertThat(statFilter.matches(42, 42)).isTrue();
    }

    @Test
    void shouldNotMatchWithOnlyLowEval() {
        StatFilter statFilter = new StatFilter(30, null);

        assertThat(statFilter.matches(1, 42)).isFalse();
    }

    @Test
    void shouldMatchWithHighEval() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(42, 42)).isTrue();
    }

    @Test
    void shouldMatchWithOnlyMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(null, List.of(1));

        assertThat(statFilter.matches(42, 1)).isTrue();
    }

    @Test
    void shouldNotMatchWithOnlyNotMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(null, List.of(1));

        assertThat(statFilter.matches(42, 42)).isFalse();
    }

    @Test
    void shouldMatchWithMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(-1, 1)).isTrue();
    }

    @Test
    void shouldNotMatchWithNotMatchingFilters() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(-1, 42)).isFalse();
    }

    @Test
    void shouldMatchWithEmptyFilter() {
        StatFilter statFilter = empty();

        assertThat(statFilter.matches(-1, 1)).isTrue();
    }

    public static StatFilter empty() {
        return new StatFilter(null, null);
    }
}
