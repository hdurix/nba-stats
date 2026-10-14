package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.JsonHelper;
import org.junit.jupiter.api.Test;

class ApiBoxscorePlayerUnitTest {

    @Test
    void shouldRoundUpMinutes() {
        assertThat(minutesDisplayed("\"minutes\": \"PT16M59.00S\"")).endsWith("|17'");
    }

    @Test
    void shouldRoundDownMinutes() {
        assertThat(minutesDisplayed("\"minutes\": \"PT16M29.00S\"")).endsWith("|16'");
    }

    @Test
    void shouldReadNoMinutes() {
        assertThat(minutesDisplayed("\"minutes\": \"PT00M00.00S\"")).endsWith("| 0'");
    }

    private static String minutesDisplayed(String minutes) {
        String json =
            "{\"personId\": 1, \"firstName\": \"Deni\", \"familyName\": \"Avdija\", \"statistics\": {" + minutes + ", \"points\": 10}}";

        return JsonHelper.readFromJson(json, ApiBoxscorePlayer.class).toDomain().toString();
    }
}
