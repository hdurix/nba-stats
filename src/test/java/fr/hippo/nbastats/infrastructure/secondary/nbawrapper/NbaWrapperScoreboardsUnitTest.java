package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperScoreboardsUnitTest {

    @InjectMocks
    private NbaWrapperScoreboards scoreboards;

    @Test
    void shouldGetScoreboards() {
        assertThat(scoreboards.forDate(LocalDate.now())).isNotNull();
    }

    @Test
    void shouldNotGetScoreboardsForUnknownDate() {
        assertThatThrownBy(() -> scoreboards.forDate(LocalDate.of(1, 1, 1)))
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Scoreboard");
    }
}
