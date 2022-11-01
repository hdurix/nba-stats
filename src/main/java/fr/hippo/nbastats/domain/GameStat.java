package fr.hippo.nbastats.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class GameStat {

    private static final int LINE_SIZE = 29;

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
        String scoreLine = StringUtils.center(score(), LINE_SIZE);
        return scoreLine + "\n" + winsLossesLine(scoreLine) + "\n\n" + away + "\n\n" + home;
    }

    private String score() {
        return away.getName().nickname() + " " + away.getScore() + " - " + home.getName().nickname() + " " + home.getScore();
    }

    private String winsLossesLine(String scoreLine) {
        int indexOfScoreCentralDash = scoreLine.indexOf('-');
        String nineSpaces = " ".repeat(9);

        String winLosses = away.getWins() + "-" + away.getLosses() + nineSpaces + home.getWins() + "-" + home.getLosses();

        int indexOfWinsLossesCentralSpace = winLosses.indexOf(nineSpaces) + 4;

        String leftSpaces = " ".repeat(indexOfScoreCentralDash - indexOfWinsLossesCentralSpace);
        return StringUtils.rightPad(leftSpaces + winLosses, LINE_SIZE);
    }
}
