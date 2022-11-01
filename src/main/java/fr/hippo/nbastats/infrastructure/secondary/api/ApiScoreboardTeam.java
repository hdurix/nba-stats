package fr.hippo.nbastats.infrastructure.secondary.api;

class ApiScoreboardTeam {

    private String teamId;
    private int score;
    private int wins;
    private int losses;

    public String getTeamId() {
        return teamId;
    }

    public int getScore() {
        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}
