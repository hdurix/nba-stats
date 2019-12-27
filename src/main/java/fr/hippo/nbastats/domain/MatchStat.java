package fr.hippo.nbastats.domain;

import org.springframework.util.Assert;

public class MatchStat {
    private final TeamStat away;
    private final TeamStat home;

    public MatchStat(TeamStat away, TeamStat home) {
        this.away = away;
        this.home = home;
        Assert.notNull(away, "missing away team");
        Assert.notNull(home, "missing home team");
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
