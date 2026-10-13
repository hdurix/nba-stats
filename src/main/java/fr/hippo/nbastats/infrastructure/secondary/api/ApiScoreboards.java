package fr.hippo.nbastats.infrastructure.secondary.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class ApiScoreboards {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final Map<String, String> NBA_HEADERS = nbaHeaders();

    private final RestTemplate rest;
    private final Curl curl;
    private final ObjectMapper objectMapper;

    ApiScoreboards(RestTemplate rest, Curl curl, ObjectMapper objectMapper) {
        this.rest = rest;
        this.curl = curl;
        this.objectMapper = objectMapper;
    }

    ApiScoreboard forDate(LocalDate date) {
        String url = "https://core-api.nba.com/cp/api/v1.9/feeds/gamecardfeed?gamedate=" + date.format(DATE_FORMATTER) + "&platform=web";

        return rest.exchange(url, HttpMethod.GET, entityWithNbaHeaders(), ApiScoreboard.class).getBody();
    }

    public ApiBoxscoreGame boxscoreForGame(String gameId) {
        String url = "https://cdn.nba.com/static/json/liveData/boxscore/boxscore_" + gameId + ".json";

        return readBoxscore(curl.get(url, NBA_HEADERS)).getGame();
    }

    private ApiBoxscore readBoxscore(String json) {
        try {
            return objectMapper.readValue(json, ApiBoxscore.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot read boxscore", e);
        }
    }

    private static HttpEntity<Void> entityWithNbaHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(NBA_HEADERS);

        return new HttpEntity<>(headers);
    }

    private static Map<String, String> nbaHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Origin", "https://www.nba.com/");
        headers.put("Referer", "https://www.nba.com/");
        headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64)");
        headers.put("ocp-apim-subscription-key", "747fa6900c6c4e89a58b81b72f36eb96");

        return headers;
    }
}
