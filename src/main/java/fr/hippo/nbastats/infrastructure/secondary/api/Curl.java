package fr.hippo.nbastats.infrastructure.secondary.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
class Curl {

    private static final int TIMEOUT_SECONDS = 20;

    String get(String url, Map<String, String> headers) {
        Process process = start(command(url, headers));
        String body = read(process.getInputStream());
        int exitCode = waitFor(process);

        if (exitCode != 0) {
            throw new CurlException("curl exited with code " + exitCode + ": " + read(process.getErrorStream()).trim());
        }

        return body;
    }

    static List<String> command(String url, Map<String, String> headers) {
        List<String> command = new ArrayList<>(
            List.of("curl", "--silent", "--show-error", "--fail", "--compressed", "--max-time", String.valueOf(TIMEOUT_SECONDS))
        );
        headers.forEach((name, value) -> command.addAll(List.of("--header", name + ": " + value)));
        command.add(url);

        return command;
    }

    private static Process start(List<String> command) {
        try {
            return new ProcessBuilder(command).start();
        } catch (IOException e) {
            throw new CurlException("cannot run curl, is it installed?", e);
        }
    }

    private static String read(InputStream stream) {
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CurlException("cannot read curl output", e);
        }
    }

    private static int waitFor(Process process) {
        try {
            return process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CurlException("interrupted while waiting for curl", e);
        }
    }
}
