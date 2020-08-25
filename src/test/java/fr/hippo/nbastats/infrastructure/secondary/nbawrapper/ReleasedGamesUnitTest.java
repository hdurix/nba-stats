package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ReleasedGamesUnitTest {
    private static final String STORAGE = "target/releases-test.txt";

    private ReleasedGames releasedGames;

    @BeforeEach
    void setup() {
        releasedGames = new ReleasedGames(STORAGE);
    }

    @AfterEach
    void deleteStorage() throws IOException {
        Files.deleteIfExists(Paths.get(STORAGE));
    }

    @Test
    void shouldAddRelease() throws IOException {
        releasedGames.add("1234");

        assertThat(new String(Files.readAllBytes(Paths.get(STORAGE)))).isEqualTo("1234\n");
    }

    @Test
    void shouldNotAddRelease() throws IOException {
        String storageUrl = "/root/super-powered";

        new ReleasedGames(storageUrl).add("1234");

        assertThat(Files.exists(Paths.get(storageUrl))).isFalse();
    }

    @Test
    void shouldFindAllReleases() {
        releasedGames.add("1234");
        releasedGames.add("5678");

        assertThat(releasedGames.findAll()).containsExactly("1234", "5678");
    }

    @Test
    void shouldFindEmptyReleaseOnError() throws IOException {
        String storageUrl = "/root/super-powered";

        assertThat(releasedGames.findAll()).isEmpty();
    }
}
