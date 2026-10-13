package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import fr.hippo.nbastats.JsonHelper;
import org.junit.jupiter.api.Test;

class ApiBoxscorePlayerUnitTest {

    @Test
    void shouldUseCalculatedMinutesWhenPresent() {
        assertThat(minutesDisplayed("\"minutes\": \"PT38M20.00S\", \"minutesCalculated\": \"PT39M\"")).endsWith("|39'");
    }

    @Test
    void shouldFallbackOnRoundedMinutesWhenCalculatedIsMissing() {
        assertThat(minutesDisplayed("\"minutes\": \"PT16M59.00S\"")).endsWith("|17'");
    }

    @Test
    void shouldRoundDownMinutesWhenCalculatedIsMissing() {
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
