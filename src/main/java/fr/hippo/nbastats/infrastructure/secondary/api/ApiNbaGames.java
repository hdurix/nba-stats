package fr.hippo.nbastats.infrastructure.secondary.api;

import fr.hippo.nbastats.domain.GameStat;
import fr.hippo.nbastats.domain.Games;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
class ApiNbaGames implements Games {

    static final int STATUS_FINISHED = 3;
    private List<String> released = new ArrayList<>();

    private final ApiScoreboards scoreboards;
    private final ReleasedGames releasedGames;
    private final ApiNbaGameConverter gameConverter;

    ApiNbaGames(ApiScoreboards scoreboards, ReleasedGames releasedGames, ApiNbaGameConverter gameConverter) {
        this.scoreboards = scoreboards;
        this.releasedGames = releasedGames;
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

    private List<ApiScoreboardGame> getRecentGames() {
        return Stream
            .of(scoreboards.forDate(LocalDate.now().minusDays(1)), scoreboards.forDate(LocalDate.now()))
            .map(ApiScoreboard::getGames)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private Predicate<ApiScoreboardGame> finished() {
        return game -> game.getGameStatus() == STATUS_FINISHED;
    }

    private Predicate<ApiScoreboardGame> notReleased() {
        return game -> !released.contains(game.getGameId());
    }

    private Function<ApiScoreboardGame, GameStat> release() {
        return game -> {
            storeRelease(game.getGameId());

            return gameConverter.toDomain(game, scoreboards.boxscoreForGame(game.getGameId()));
        };
    }

    private void storeRelease(String gameId) {
        releasedGames.add(gameId);
        released.add(gameId);
    }
}
