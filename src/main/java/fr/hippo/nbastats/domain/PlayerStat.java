package fr.hippo.nbastats.domain;

import org.springframework.util.Assert;

public class PlayerStat {

    private final Identity identity;
    private final Fouls fouls;
    private final Evaluation evaluation;
    private final Stat points;
    private final Stat rebounds;
    private final Stat assists;
    private final Stat blocks;
    private final Stat steals;
    private final StatTuple fieldGoals;
    private final StatTuple threePoints;
    private final StatTuple freeThrows;
    private final Stat turnovers;
    private final Stat minutes;

    private PlayerStat(PlayerStatBuilder builder) {
        Assert.notNull(builder.identity, "missing identity");
        Assert.notNull(builder.fouls, "missing fouls");
        Assert.notNull(builder.points, "missing points");
        Assert.notNull(builder.rebounds, "missing rebounds");
        Assert.notNull(builder.assists, "missing assists");
        Assert.notNull(builder.blocks, "missing blocks");
        Assert.notNull(builder.steals, "missing steals");
        Assert.notNull(builder.fieldGoals, "missing fieldGoals");
        Assert.notNull(builder.threePoints, "missing threePoints");
        Assert.notNull(builder.freeThrows, "missing freeThrows");
        Assert.notNull(builder.turnovers, "missing turnovers");
        Assert.notNull(builder.minutes, "missing minutes");

        this.identity = builder.identity;
        this.fouls = builder.fouls;
        this.points = builder.points;
        this.rebounds = builder.rebounds;
        this.assists = builder.assists;
        this.blocks = builder.blocks;
        this.steals = builder.steals;
        this.fieldGoals = builder.fieldGoals;
        this.threePoints = builder.threePoints;
        this.freeThrows = builder.freeThrows;
        this.turnovers = builder.turnovers;
        this.minutes = builder.minutes;

        this.evaluation = ttflEvaluation();
    }

    private Evaluation ttflEvaluation() {
        return new Evaluation(goodMovesScore() - badMovesScore());
    }

    private int goodMovesScore() {
        return (
            points.value() +
            rebounds.value() +
            assists.value() +
            blocks.value() +
            steals.value() +
            fieldGoals.getSuccess() +
            threePoints.getSuccess() +
            freeThrows.getSuccess()
        );
    }

    private int badMovesScore() {
        return turnovers.value() + fieldGoals.getMissed() + threePoints.getMissed() + freeThrows.getMissed();
    }

    public static PlayerStatBuilder builder() {
        return new PlayerStatBuilder();
    }

    int eval() {
        return evaluation.value();
    }

    boolean played() {
        return minutes.value() > 0;
    }

    int id() {
        return identity.getId();
    }

    @Override
    public String toString() {
        return line1() + "\n" + line2();
    }

    private String line1() {
        return new StringBuilder()
            .append(identity)
            .append(fouls)
            .append(evaluation)
            .append("|")
            .append(points)
            .append(" ")
            .append(rebounds)
            .append(" ")
            .append(assists)
            .append(" ")
            .append(blocks)
            .append(" ")
            .append(steals)
            .toString();
    }

    private String line2() {
        return new StringBuilder()
            .append(fieldGoals)
            .append(" ")
            .append(threePoints)
            .append(" ")
            .append(freeThrows)
            .append("|")
            .append(turnovers)
            .append("|")
            .append(minutes)
            .append("'")
            .toString();
    }

    public static class PlayerStatBuilder {

        private Identity identity;
        private Fouls fouls;
        private Stat points;
        private Stat rebounds;
        private Stat assists;
        private Stat blocks;
        private Stat steals;
        private StatTuple fieldGoals;
        private StatTuple threePoints;
        private StatTuple freeThrows;
        private Stat turnovers;
        private Stat minutes;

        public PlayerStatBuilder identity(Identity identity) {
            this.identity = identity;

            return this;
        }

        public PlayerStatBuilder fouls(Fouls fouls) {
            this.fouls = fouls;

            return this;
        }

        public PlayerStatBuilder points(Stat points) {
            this.points = points;

            return this;
        }

        public PlayerStatBuilder rebounds(Stat rebounds) {
            this.rebounds = rebounds;

            return this;
        }

        public PlayerStatBuilder assists(Stat assists) {
            this.assists = assists;

            return this;
        }

        public PlayerStatBuilder blocks(Stat blocks) {
            this.blocks = blocks;

            return this;
        }

        public PlayerStatBuilder steals(Stat steals) {
            this.steals = steals;

            return this;
        }

        public PlayerStatBuilder fieldGoals(StatTuple fieldGoals) {
            this.fieldGoals = fieldGoals;

            return this;
        }

        public PlayerStatBuilder threePoints(StatTuple threePoints) {
            this.threePoints = threePoints;

            return this;
        }

        public PlayerStatBuilder freeThrows(StatTuple freeThrows) {
            this.freeThrows = freeThrows;

            return this;
        }

        public PlayerStatBuilder turnovers(Stat turnover) {
            this.turnovers = turnover;

            return this;
        }

        public PlayerStatBuilder minutes(Stat minutes) {
            this.minutes = minutes;

            return this;
        }

        public PlayerStat build() {
            return new PlayerStat(this);
        }
    }
}
