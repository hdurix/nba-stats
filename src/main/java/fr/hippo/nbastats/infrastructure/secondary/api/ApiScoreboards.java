package fr.hippo.nbastats.infrastructure.secondary.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class ApiScoreboards {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final RestTemplate rest;

    ApiScoreboards(RestTemplate rest) {
        this.rest = rest;
    }

    ApiScoreboard forDate(LocalDate date) {
        String url = "https://core-api.nba.com/cp/api/v1.9/feeds/gamecardfeed?gamedate=" + date.format(DATE_FORMATTER) + "&platform=web";

        return rest.exchange(url, HttpMethod.GET, entityWithNbaHeaders(), ApiScoreboard.class).getBody();
    }

    public ApiBoxscoreGame boxscoreForGame(String gameId) {
        String url = "https://cdn.nba.com/static/json/liveData/boxscore/boxscore_" + gameId + ".json";

        return rest.exchange(url, HttpMethod.GET, entityWithNbaHeaders(), ApiBoxscore.class).getBody().getGame();
    }

    private static HttpEntity<Void> entityWithNbaHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Origin", "https://www.nba.com/");
        headers.set("Referer", "https://www.nba.com/");
        headers.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64)");
        headers.set("ocp-apim-subscription-key", "747fa6900c6c4e89a58b81b72f36eb96");

        return new HttpEntity<>(headers);
    }
}
