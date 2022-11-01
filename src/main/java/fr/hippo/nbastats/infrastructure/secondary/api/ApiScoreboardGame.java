package fr.hippo.nbastats.infrastructure.secondary.api;

class ApiScoreboardGame {

    private String gameId;
    private String gameCode;
    private int gameStatus;
    private ApiScoreboardTeam homeTeam;
    private ApiScoreboardTeam awayTeam;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ApiScoreboardTeam getHomeTeam() {
        return homeTeam;
    }

    public ApiScoreboardTeam getAwayTeam() {
        return awayTeam;
    }
}
