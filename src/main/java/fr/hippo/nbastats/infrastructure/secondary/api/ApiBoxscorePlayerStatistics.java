package fr.hippo.nbastats.infrastructure.secondary.api;

class ApiBoxscorePlayerStatistics {

    private int foulsPersonal;
    private int points;
    private int reboundsTotal;
    private int assists;
    private int blocks;
    private int steals;
    private int fieldGoalsMade;
    private int fieldGoalsAttempted;
    private int threePointersMade;
    private int threePointersAttempted;
    private int freeThrowsMade;
    private int freeThrowsAttempted;
    private int turnovers;
    private String minutesCalculated;

    public int getFoulsPersonal() {
        return foulsPersonal;
    }

    public int getPoints() {
        return points;
    }

    public int getReboundsTotal() {
        return reboundsTotal;
    }

    public int getAssists() {
        return assists;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getSteals() {
        return steals;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public String getMinutesCalculated() {
        return minutesCalculated;
    }
}
