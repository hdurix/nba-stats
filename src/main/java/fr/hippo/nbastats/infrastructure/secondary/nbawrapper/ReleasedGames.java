package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
class ReleasedGames {
    private static final Logger log = LoggerFactory.getLogger(ReleasedGames.class);

    private static final String ID_SEPARATOR = "\n";

    private final Path storagePath;

    public ReleasedGames(@Value("${games.storage}") String storageUrl) {
        this.storagePath = Paths.get(storageUrl);
    }

    public void add(String gameId) {
        String toStore = gameId + ID_SEPARATOR;
        try {
            Files.createDirectories(storagePath.getParent());
            Files.write(storagePath, toStore.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Cannot add game to storage", e);
        }
    }

    public List<String> findAll() {
        try {
            return Stream.of(new String(Files.readAllBytes(storagePath)).split("\n")).collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
