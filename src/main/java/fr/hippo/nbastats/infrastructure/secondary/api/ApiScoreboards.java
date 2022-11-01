package fr.hippo.nbastats.infrastructure.secondary.api;

import java.time.LocalDate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class ApiScoreboards {

    private final RestTemplate rest;

    ApiScoreboards(RestTemplate rest) {
        this.rest = rest;
    }

    ApiScoreboard forDate(LocalDate date) {
        String url = "https://stats.nba.com/stats/scoreboardv3?LeagueID=00&GameDate=" + date;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Referer", "https://www.nba.com/");
        headers.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64)");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return rest.exchange(url, HttpMethod.GET, entity, ApiScoreboardWrapper.class).getBody().getScoreboard();
    }

    public ApiBoxscoreGame boxscoreForGame(String gameId) {
        String url = "https://cdn.nba.com/static/json/liveData/boxscore/boxscore_" + gameId + ".json";

        return rest.getForObject(url, ApiBoxscore.class).getGame();
    }
}
