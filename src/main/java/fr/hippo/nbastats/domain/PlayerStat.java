package fr.hippo.nbastats.domain;

import org.springframework.util.Assert;

public class PlayerStat {
    private final Identity identity;
    private final Fouls fouls;
    private final UnaryStat evaluation;
    private final UnaryStat points;
    private final UnaryStat rebounds;
    private final UnaryStat assists;
    private final UnaryStat blocks;
    private final UnaryStat steals;
    private final BinaryStat fieldGoals;
    private final BinaryStat threePoints;
    private final BinaryStat freeThrows;
    private final UnaryStat turnovers;
    private final UnaryStat minutes;

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

    private UnaryStat ttflEvaluation() {
        int score =
            points.value() +
            rebounds.value() +
            assists.value() +
            blocks.value() +
            steals.value() +
            fieldGoals.getSuccess() +
            threePoints.getSuccess() +
            freeThrows.getSuccess() -
            turnovers.value() -
            fieldGoals.getMissed() -
            threePoints.getMissed() -
            freeThrows.getMissed();
        return new UnaryStat(score);
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
        return (
            "" +
            identity +
            fouls +
            evaluation +
            "|" +
            points +
            " " +
            rebounds +
            " " +
            assists +
            " " +
            blocks +
            " " +
            steals +
            "\n" +
            fieldGoals +
            " " +
            threePoints +
            " " +
            freeThrows +
            "|" +
            turnovers +
            "|" +
            minutes +
            "'"
        );
    }

    public static class PlayerStatBuilder {
        private Identity identity;
        private Fouls fouls;
        private UnaryStat points;
        private UnaryStat rebounds;
        private UnaryStat assists;
        private UnaryStat blocks;
        private UnaryStat steals;
        private BinaryStat fieldGoals;
        private BinaryStat threePoints;
        private BinaryStat freeThrows;
        private UnaryStat turnovers;
        private UnaryStat minutes;

        public PlayerStatBuilder identity(Identity identity) {
            this.identity = identity;

            return this;
        }

        public PlayerStatBuilder fouls(Fouls fouls) {
            this.fouls = fouls;

            return this;
        }

        public PlayerStatBuilder points(UnaryStat points) {
            this.points = points;

            return this;
        }

        public PlayerStatBuilder rebounds(UnaryStat rebounds) {
            this.rebounds = rebounds;

            return this;
        }

        public PlayerStatBuilder assists(UnaryStat assists) {
            this.assists = assists;

            return this;
        }

        public PlayerStatBuilder blocks(UnaryStat blocks) {
            this.blocks = blocks;

            return this;
        }

        public PlayerStatBuilder steals(UnaryStat steals) {
            this.steals = steals;

            return this;
        }

        public PlayerStatBuilder fieldGoals(BinaryStat fieldGoals) {
            this.fieldGoals = fieldGoals;

            return this;
        }

        public PlayerStatBuilder threePoints(BinaryStat threePoints) {
            this.threePoints = threePoints;

            return this;
        }

        public PlayerStatBuilder freeThrows(BinaryStat freeThrows) {
            this.freeThrows = freeThrows;

            return this;
        }

        public PlayerStatBuilder turnovers(UnaryStat turnover) {
            this.turnovers = turnover;

            return this;
        }

        public PlayerStatBuilder minutes(UnaryStat minutes) {
            this.minutes = minutes;

            return this;
        }

        public PlayerStat build() {
            return new PlayerStat(this);
        }
    }
}
