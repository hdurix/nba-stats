package fr.hippo.nbastats.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public class TeamStat {
    private static final Comparator<PlayerStat> PLAYER_STAT_COMPARATOR = Comparator.comparing(PlayerStat::eval).reversed();

    private final TeamName name;
    private final int score;
    private final Set<PlayerStat> players;

    public TeamStat(TeamName name, int score, Set<PlayerStat> players) {
        Assert.notNull(name, "missing name");
        Assert.notNull(players, "missing players");

        this.name = name;
        this.score = score;
        this.players = buildPlayers(players);
    }

    private Set<PlayerStat> buildPlayers(Set<PlayerStat> players) {
        TreeSet<PlayerStat> playerStats = new TreeSet<>(PLAYER_STAT_COMPARATOR);
        playerStats.addAll(players);
        return Collections.unmodifiableSet(playerStats);
    }

    public TeamName getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Set<PlayerStat> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return name + "\n" + players.stream().map(PlayerStat::toString).collect(Collectors.joining("\n\n"));
    }
}
