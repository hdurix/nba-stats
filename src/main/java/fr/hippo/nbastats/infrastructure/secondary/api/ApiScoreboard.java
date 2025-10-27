package fr.hippo.nbastats.infrastructure.secondary.api;

import java.util.Collection;
import java.util.stream.Collectors;

class ApiScoreboard {

    private Collection<ApiModule> modules;

    public void setModules(Collection<ApiModule> modules) {
        this.modules = modules;
    }

    public Collection<ApiScoreboardGame> getGames() {
        return modules.stream().flatMap(module -> module.getCards().stream()).map(ApiCard::getCardData).collect(Collectors.toList());
    }
}
