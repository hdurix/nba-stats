package fr.hippo.nbastats.domain;

import java.util.List;

public class StatFilter {
    private final Integer highEval;
    private final List<Integer> playerIds;

    public StatFilter(Integer highEval, List<Integer> playerIds) {
        this.highEval = highEval;
        this.playerIds = playerIds;
    }

    public boolean matches(int eval, int playerId) {
        if (noHighEval() && noWantedPlayers()) {
            return true;
        }
        if (hasHighEval() && hasWantedPlayers()) {
            return isHigh(eval) || isWanted(playerId);
        }
        if (hasHighEval()) {
            return isHigh(eval);
        }
        return isWanted(playerId);
    }

    private boolean noHighEval() {
        return !hasHighEval();
    }

    private boolean hasHighEval() {
        return highEval != null;
    }

    private boolean noWantedPlayers() {
        return !hasWantedPlayers();
    }

    private boolean hasWantedPlayers() {
        return playerIds != null;
    }

    private boolean isHigh(int eval) {
        return eval >= highEval;
    }

    private boolean isWanted(int playerId) {
        return playerIds.contains(playerId);
    }
}
