package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class StatFilterUnitTest {

    @Test
    public void shouldMatchWithOnlyHighEval() {
        StatFilter statFilter = new StatFilter(30, null);

        assertThat(statFilter.matches(42, 42)).isTrue();
    }

    @Test
    public void shouldNotMatchWithOnlyLowEval() {
        StatFilter statFilter = new StatFilter(30, null);

        assertThat(statFilter.matches(1, 42)).isFalse();
    }

    @Test
    public void shouldMatchWithHighEval() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(42, 42)).isTrue();
    }

    @Test
    public void shouldMatchWithOnlyMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(null, List.of(1));

        assertThat(statFilter.matches(42, 1)).isTrue();
    }

    @Test
    public void shouldNotMatchWithOnlyNotMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(null, List.of(1));

        assertThat(statFilter.matches(42, 42)).isFalse();
    }

    @Test
    public void shouldMatchWithMatchingPlayerId() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(-1, 1)).isTrue();
    }

    @Test
    public void shouldNotMatchWithNotMatchingFilters() {
        StatFilter statFilter = new StatFilter(30, List.of(1));

        assertThat(statFilter.matches(-1, 42)).isFalse();
    }

    @Test
    public void shouldMatchWithEmptyFilter() {
        StatFilter statFilter = empty();

        assertThat(statFilter.matches(-1, 1)).isTrue();
    }

    public static StatFilter empty() {
        return new StatFilter(null, null);
    }
}
