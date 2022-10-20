package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "telegram.bot_id=dummy", "telegram.chat_id=dummy" })
class ApiScoreboardsIntTest {

    @Autowired
    private ApiScoreboards scoreboards;

    @Test
    void shouldGetScoreboards() {
        assertThat(scoreboards.forDate(LocalDate.now())).isNotNull();
    }

    @Test
    void shouldGetBoxscore() {
        assertThat(scoreboards.boxscoreForGame("0022200003")).isNotNull();
    }
}
