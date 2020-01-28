package fr.hippo.nbastats.config;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.domain.StatFilter;
import java.util.List;
import org.junit.jupiter.api.Test;

class StatFilterPropertiesUnitTest {

    @Test
    public void shouldGetStatFilter() {
        StatFilterProperties properties = new StatFilterProperties();
        properties.setHighEval(42);
        properties.setPlayerIds(List.of(43));
        StatFilter statFilter = properties.statFilter();

        assertThat(statFilter.matches(42, 1)).isTrue();
        assertThat(statFilter.matches(1, 43)).isTrue();
    }
}
