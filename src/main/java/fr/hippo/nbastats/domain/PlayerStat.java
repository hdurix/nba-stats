package fr.hippo.nbastats.domain;

import org.springframework.util.Assert;

public class PlayerStat {
    private final Identity identity;
    private final Fouls fouls;
    private final TwoDigitNumber evaluation;
    private final TwoDigitNumber points;
    private final TwoDigitNumber rebounds;
    private final TwoDigitNumber assists;
    private final TwoDigitNumber blocks;
    private final TwoDigitNumber steals;
    private final TwoValues fieldGoals;
    private final TwoValues threePoints;
    private final TwoValues freeThrows;
    private final TwoDigitNumber turnovers;
    private final TwoDigitNumber minutes;

    private PlayerStat(PlayerStatBuilder builder) {
        Assert.notNull(builder.identity, "missing identity");
        Assert.notNull(builder.fouls, "missing fouls");
        Assert.notNull(builder.evaluation, "missing evaluation");
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
        this.evaluation = builder.evaluation;
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
    }

    public static PlayerStatBuilder builder() {
        return new PlayerStatBuilder();
    }

    public int eval() {
        return evaluation.value();
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
        private TwoDigitNumber evaluation;
        private TwoDigitNumber points;
        private TwoDigitNumber rebounds;
        private TwoDigitNumber assists;
        private TwoDigitNumber blocks;
        private TwoDigitNumber steals;
        private TwoValues fieldGoals;
        private TwoValues threePoints;
        private TwoValues freeThrows;
        private TwoDigitNumber turnovers;
        private TwoDigitNumber minutes;

        public PlayerStatBuilder identity(Identity identity) {
            this.identity = identity;

            return this;
        }

        public PlayerStatBuilder fouls(Fouls fouls) {
            this.fouls = fouls;

            return this;
        }

        public PlayerStatBuilder evaluation(TwoDigitNumber evaluation) {
            this.evaluation = evaluation;

            return this;
        }

        public PlayerStatBuilder points(TwoDigitNumber points) {
            this.points = points;

            return this;
        }

        public PlayerStatBuilder rebounds(TwoDigitNumber rebounds) {
            this.rebounds = rebounds;

            return this;
        }

        public PlayerStatBuilder assists(TwoDigitNumber assists) {
            this.assists = assists;

            return this;
        }

        public PlayerStatBuilder blocks(TwoDigitNumber blocks) {
            this.blocks = blocks;

            return this;
        }

        public PlayerStatBuilder steals(TwoDigitNumber steals) {
            this.steals = steals;

            return this;
        }

        public PlayerStatBuilder fieldGoals(TwoValues fieldGoals) {
            this.fieldGoals = fieldGoals;

            return this;
        }

        public PlayerStatBuilder threePoints(TwoValues threePoints) {
            this.threePoints = threePoints;

            return this;
        }

        public PlayerStatBuilder freeThrows(TwoValues freeThrows) {
            this.freeThrows = freeThrows;

            return this;
        }

        public PlayerStatBuilder turnovers(TwoDigitNumber turnover) {
            this.turnovers = turnover;

            return this;
        }

        public PlayerStatBuilder minutes(TwoDigitNumber minutes) {
            this.minutes = minutes;

            return this;
        }

        public PlayerStat build() {
            return new PlayerStat(this);
        }
    }
}
