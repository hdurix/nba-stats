package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.utils.scoreboard.GameDetails;
import com.drmilk.nbawrapper.domain.utils.scoreboard.Scoreboard;
import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.Games;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
class NbaWrapperGames implements Games {

    public static final int STATUS_FINISHED = 3;
    private List<String> released = new ArrayList<>();

    private final NbaWrapperScoreboards scoreboards;
    private final ReleasedGames releasedGames;
    private final NbaWrapperBoxscores boxscores;
    private final NbaWrapperGameConverter gameConverter;

    NbaWrapperGames(
        NbaWrapperScoreboards scoreboards,
        ReleasedGames releasedGames,
        NbaWrapperBoxscores boxscores,
        NbaWrapperGameConverter gameConverter
    ) {
        this.scoreboards = scoreboards;
        this.releasedGames = releasedGames;
        this.boxscores = boxscores;
        this.gameConverter = gameConverter;
    }

    @PostConstruct
    void initReleases() {
        released = releasedGames.findAll();
    }

    @Override
    public Optional<GameStat> findUnreleased() {
        return getRecentGames().stream().filter(finished()).filter(notReleased()).findFirst().map(release());
    }

    private List<GameDetails> getRecentGames() {
        return Stream
            .of(scoreboards.forDate(LocalDate.now().minusDays(1)), scoreboards.forDate(LocalDate.now()))
            .map(Scoreboard::getGames)
            .flatMap(scoreboard -> scoreboard.stream())
            .collect(Collectors.toList());
    }

    private Predicate<GameDetails> finished() {
        return game -> getStatusNum(game) == STATUS_FINISHED;
    }

    private int getStatusNum(GameDetails game) {
        return (int) game.getAdditionalProperties().get("statusNum");
    }

    private Predicate<GameDetails> notReleased() {
        return game -> !released.contains(game.getGameId());
    }

    private Function<GameDetails, GameStat> release() {
        return game -> {
            storeRelease(game.getGameId());

            return gameConverter.toDomain(game, boxscores.findByGameId(getGameDate(game), game.getGameId()));
        };
    }

    private void storeRelease(String gameId) {
        releasedGames.add(gameId);
        released.add(gameId);
    }

    private LocalDate getGameDate(GameDetails game) {
        String urlCode = (String) game.getAdditionalProperties().get("gameUrlCode");
        return LocalDate.parse(urlCode.split("/")[0], DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
