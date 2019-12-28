package fr.hippo.nbastats.domain;

import org.springframework.util.Assert;

public class GameStat {
    private final TeamStat away;
    private final TeamStat home;

    public GameStat(TeamStat away, TeamStat home) {
        Assert.notNull(away, "missing away team");
        Assert.notNull(home, "missing home team");

        this.away = away;
        this.home = home;
    }

    @Override
    public String toString() {
        return (
            away.getName().nickname() +
            " " +
            away.getScore() +
            " - " +
            home.getName().nickname() +
            " " +
            home.getScore() +
            "\n\n" +
            away +
            "\n\n" +
            home
        );
    }
}
