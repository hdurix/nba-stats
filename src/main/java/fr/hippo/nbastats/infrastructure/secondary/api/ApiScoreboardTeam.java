package fr.hippo.nbastats.infrastructure.secondary.api;

class ApiScoreboardTeam {

    private String teamId;
    private int score;

    public String getTeamId() {
        return teamId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
