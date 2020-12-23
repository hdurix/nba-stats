package com.drmilk.nbawrapper.domain;

import com.drmilk.nbawrapper.config.AppConfig;
import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.domain.utils.scoreboard.Scoreboard;
import com.drmilk.nbawrapper.exception.BoxscoreNotFoundException;
import com.drmilk.nbawrapper.exception.ScoreboardNotFoundException;
import com.drmilk.nbawrapper.utils.QueryManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 *
 * @author Antoine Guay
 *
 */
public class League {

    private static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private static String sourceBaseUrl = (String) context.getBean("sourceBaseUrl");

    private static ObjectMapper objectMapper = (ObjectMapper) context.getBean("objectMapper");

    public static Scoreboard getScoreboard(Integer day, Integer month, Integer year) throws ScoreboardNotFoundException {
        try {
            HttpResponse scoreboardResponse = QueryManager.getHttpResponse(
                sourceBaseUrl + "/" + year.toString() + toStringDate(month) + toStringDate(day) + "/scoreboard.json"
            );
            return objectMapper.readValue(scoreboardResponse.getEntity().getContent(), Scoreboard.class);
        } catch (Exception e) {
            throw new ScoreboardNotFoundException("Could not find a scoreboard for the date " + month + " " + day + "th " + year);
        }
    }

    public static Boxscore getBoxscore(Integer day, Integer month, Integer year, String gameId) throws BoxscoreNotFoundException {
        try {
            HttpResponse boxscoreResponse = QueryManager.getHttpResponse(
                sourceBaseUrl + "/" + year.toString() + toStringDate(month) + toStringDate(day) + "/" + gameId + "_boxscore.json"
            );
            return objectMapper.readValue(boxscoreResponse.getEntity().getContent(), Boxscore.class);
        } catch (Exception e) {
            throw new BoxscoreNotFoundException(
                "Could not find a boxscore for the date " + month + " " + day + "th " + year + " and gameId " + gameId
            );
        }
    }

    private static String toStringDate(Integer date) {
        if (date < 10) {
            return "0" + date.toString();
        }

        return date.toString();
    }
}
