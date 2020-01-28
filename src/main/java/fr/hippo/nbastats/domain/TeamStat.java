package fr.hippo.nbastats.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public class TeamStat {
    private static final Comparator<PlayerStat> PLAYER_STAT_COMPARATOR = Comparator.comparing(PlayerStat::eval).reversed();

    private final StatFilter filter;
    private final TeamName name;
    private final int score;
    private final List<PlayerStat> players;

    public TeamStat(TeamStatBuilder builder) {
        Assert.notNull(builder.filter, "missing filter");
        Assert.notNull(builder.name, "missing name");
        Assert.notNull(builder.players, "missing players");

        this.filter = builder.filter;
        this.name = builder.name;
        this.score = builder.score;
        this.players = buildPlayers(builder.players);
    }

    private List<PlayerStat> buildPlayers(List<PlayerStat> players) {
        List<PlayerStat> playerStats = new ArrayList<>(players);
        Collections.sort(playerStats, PLAYER_STAT_COMPARATOR);
        return Collections.unmodifiableList(playerStats);
    }

    public static TeamStatBuilder builder() {
        return new TeamStatBuilder();
    }

    public TeamName getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<PlayerStat> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        String playerStats = players
            .stream()
            .filter(PlayerStat::played)
            .filter(matchFilter())
            .map(PlayerStat::toString)
            .collect(Collectors.joining("\n\n"));
        return name + "\n" + playerStats;
    }

    private Predicate<PlayerStat> matchFilter() {
        return playerStat -> filter.matches(playerStat.eval(), playerStat.id());
    }

    public static class TeamStatBuilder {
        private StatFilter filter;
        private TeamName name;
        private int score;
        private List<PlayerStat> players;

        public TeamStatBuilder filter(StatFilter filter) {
            this.filter = filter;

            return this;
        }

        public TeamStatBuilder name(TeamName name) {
            this.name = name;

            return this;
        }

        public TeamStatBuilder score(int score) {
            this.score = score;

            return this;
        }

        public TeamStatBuilder players(List<PlayerStat> players) {
            this.players = players;

            return this;
        }

        public TeamStat build() {
            return new TeamStat(this);
        }
    }
}
