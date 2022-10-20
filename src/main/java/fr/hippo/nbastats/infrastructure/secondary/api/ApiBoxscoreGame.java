package fr.hippo.nbastats.infrastructure.secondary.api;

class ApiBoxscoreGame {

    private ApiBoxscoreTeam homeTeam;
    private ApiBoxscoreTeam awayTeam;

    public ApiBoxscoreTeam getHomeTeam() {
        return homeTeam;
    }

    public ApiBoxscoreTeam getAwayTeam() {
        return awayTeam;
    }
}
