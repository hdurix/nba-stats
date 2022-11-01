package fr.hippo.nbastats.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.util.Assert;

public class TeamStat {

    private static final Comparator<PlayerStat> PLAYER_STAT_COMPARATOR = Comparator.comparing(PlayerStat::eval).reversed();

    private final StatFilter filter;
    private final TeamName name;
    private final int score;
    private final int wins;
    private final int losses;
    private final List<PlayerStat> players;

    TeamStat(TeamStatBuilder builder) {
        Assert.notNull(builder.filter, "missing filter");
        Assert.notNull(builder.name, "missing name");
        Assert.notNull(builder.players, "missing players");

        this.filter = builder.filter;
        this.name = builder.name;
        this.score = builder.score;
        this.wins = builder.wins;
        this.losses = builder.losses;
        this.players = buildPlayers(builder.players);
    }

    private List<PlayerStat> buildPlayers(List<PlayerStat> players) {
        List<PlayerStat> playerStats = new ArrayList<>(players);
        playerStats.sort(PLAYER_STAT_COMPARATOR);
        return Collections.unmodifiableList(playerStats);
    }

    public static TeamStatBuilder builder() {
        return new TeamStatBuilder();
    }

    TeamName getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    List<PlayerStat> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        String playerStats = wantedPlayers().stream().map(PlayerStat::toString).collect(Collectors.joining("\n\n"));
        return name + "\n" + playerStats;
    }

    private List<PlayerStat> wantedPlayers() {
        List<PlayerStat> wantedPlayers = players.stream().filter(PlayerStat::played).filter(matchFilter()).collect(Collectors.toList());

        if (wantedPlayers.size() >= 3) {
            return wantedPlayers;
        }

        List<PlayerStat> notWantedPlayers = players.stream().filter(p -> !wantedPlayers.contains(p)).collect(Collectors.toList());

        List<PlayerStat> playersToAdd = notWantedPlayers.subList(0, 3 - wantedPlayers.size());

        return Stream.concat(wantedPlayers.stream(), playersToAdd.stream()).sorted(PLAYER_STAT_COMPARATOR).collect(Collectors.toList());
    }

    private Predicate<PlayerStat> matchFilter() {
        return playerStat -> filter.matches(playerStat.eval(), playerStat.id());
    }

    public static class TeamStatBuilder {

        private StatFilter filter;
        private TeamName name;
        private int score;
        private int wins;
        private int losses;
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

        public TeamStatBuilder wins(int wins) {
            this.wins = wins;

            return this;
        }

        public TeamStatBuilder losses(int losses) {
            this.losses = losses;

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
