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
public class ReleaseGamesUnitTest {
    private static final String STORAGE = "target/releases-test.txt";

    private ReleaseGames releaseGames;

    @BeforeEach
    public void setup() {
        releaseGames = new ReleaseGames(STORAGE);
    }

    @AfterEach
    public void deleteStorage() throws IOException {
        Files.deleteIfExists(Paths.get(STORAGE));
    }

    @Test
    public void shouldAddRelease() throws IOException {
        releaseGames.add("1234");

        assertThat(new String(Files.readAllBytes(Paths.get(STORAGE)))).isEqualTo("1234\n");
    }

    @Test
    public void shouldNotAddRelease() throws IOException {
        String storageUrl = "/root/super-powered";

        new ReleaseGames(storageUrl).add("1234");

        assertThat(Files.exists(Paths.get(storageUrl))).isFalse();
    }

    @Test
    public void shouldFindAllReleases() {
        releaseGames.add("1234");
        releaseGames.add("5678");

        assertThat(releaseGames.findAll()).containsExactly("1234", "5678");
    }

    @Test
    public void shouldFindEmptyReleaseOnError() throws IOException {
        String storageUrl = "/root/super-powered";

        assertThat(releaseGames.findAll()).isEmpty();
    }
}
