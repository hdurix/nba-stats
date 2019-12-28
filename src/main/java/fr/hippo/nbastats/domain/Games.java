package fr.hippo.nbastats.domain;

import java.util.Optional;

public interface Games {
    Optional<GameStat> findUnreleased();
}
