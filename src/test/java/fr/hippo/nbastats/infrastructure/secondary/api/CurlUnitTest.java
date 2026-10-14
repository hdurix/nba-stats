package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CurlUnitTest {

    @Test
    void shouldBuildCommandWithHeadersInOrderAndUrlLast() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Origin", "https://www.nba.com/");
        headers.put("Referer", "https://www.nba.com/");

        assertThat(Curl.command("https://cdn.nba.com/box.json", headers))
            .containsSubsequence("--header", "Origin: https://www.nba.com/", "--header", "Referer: https://www.nba.com/")
            .endsWith("https://cdn.nba.com/box.json");
    }

    @Test
    void shouldFailOnHttpErrorsAndAcceptCompression() {
        assertThat(Curl.command("https://cdn.nba.com/box.json", Map.of())).contains("--fail", "--compressed");
    }
}
