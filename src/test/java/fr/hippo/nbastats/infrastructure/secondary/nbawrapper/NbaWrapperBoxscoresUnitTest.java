package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperBoxscoresUnitTest {

    @InjectMocks
    private NbaWrapperBoxscores boxscores;

    @Test
    void shouldGetBoxscore() {
        assertThat(boxscores.findByGameId(LocalDate.of(2019, 12, 27), "0021900466")).isNotNull();
    }

    @Test
    void shouldNotGetBoxscoreForUnknownId() {
        assertThatThrownBy(() -> boxscores.findByGameId(LocalDate.of(2019, 12, 27), "unknown"))
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Boxscore");
    }
}
