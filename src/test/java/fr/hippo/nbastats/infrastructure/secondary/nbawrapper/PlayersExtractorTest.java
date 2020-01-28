package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.player.LeaguePlayer;
import com.drmilk.nbawrapper.domain.utils.player.LeaguePlayersResponse;
import com.drmilk.nbawrapper.utils.QueryManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hippo.nbastats.domain.TeamName;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("no need to be automated")
public class PlayersExtractorTest {
    private static final int CURRENT_SEASON = 2019;
    private static final String SOURCE_BASE_URL = "http://data.nba.net/data/10s/prod/v1";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private NbaWrapperTeams nbaWrapperTeams;

    @Test
    public void extractAllPlayersAsCsv() throws IOException {
        HttpResponse response = QueryManager.getHttpResponse(SOURCE_BASE_URL + "/" + CURRENT_SEASON + "/players.json");
        LeaguePlayersResponse leaguePlayers = objectMapper.readValue(response.getEntity().getContent(), LeaguePlayersResponse.class);

        List<LeaguePlayer> players = leaguePlayers.getLeague().getStandard();
        players.stream().filter(p -> getTeam(p) != null).map(toCsv()).forEach(System.out::println);
    }

    private TeamName getTeam(LeaguePlayer p) {
        return nbaWrapperTeams.findById(p.getTeamId());
    }

    private Function<LeaguePlayer, String> toCsv() {
        return p -> getTeam(p).nickname() + ";" + p.getFirstName() + ";" + p.getLastName() + ";" + p.getPersonId();
    }
}
