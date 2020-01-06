package fr.hippo.nbastats.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public class TeamStat {
    private static final Comparator<PlayerStat> PLAYER_STAT_COMPARATOR = Comparator.comparing(PlayerStat::eval).reversed();

    private final TeamName name;
    private final int score;
    private final List<PlayerStat> players;

    public TeamStat(TeamName name, int score, List<PlayerStat> players) {
        Assert.notNull(name, "missing name");
        Assert.notNull(players, "missing players");

        this.name = name;
        this.score = score;
        this.players = buildPlayers(players);
    }

    private List<PlayerStat> buildPlayers(List<PlayerStat> players) {
        List<PlayerStat> playerStats = new ArrayList<>(players);
        Collections.sort(playerStats, PLAYER_STAT_COMPARATOR);
        return Collections.unmodifiableList(playerStats);
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
        return name + "\n" + players.stream().filter(PlayerStat::played).map(PlayerStat::toString).collect(Collectors.joining("\n\n"));
    }
}
