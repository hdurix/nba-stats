package fr.hippo.nbastats.infrastructure.secondary.api;

import java.util.Collection;

class ApiScoreboard {

    private Collection<ApiScoreboardGame> games;

    public Collection<ApiScoreboardGame> getGames() {
        return games;
    }

    public void setGames(Collection<ApiScoreboardGame> games) {
        this.games = games;
    }
}
