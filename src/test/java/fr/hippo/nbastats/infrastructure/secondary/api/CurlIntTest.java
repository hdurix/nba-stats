package fr.hippo.nbastats.infrastructure.secondary.api;

import static org.assertj.core.api.Assertions.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurlIntTest {

    private HttpServer server;
    private String baseUrl;

    private final Curl curl = new Curl();

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/headers", exchange -> reply(exchange, 200, exchange.getRequestHeaders().getFirst("Origin")));
        server.createContext("/compression", exchange -> reply(exchange, 200, exchange.getRequestHeaders().getFirst("Accept-Encoding")));
        server.createContext("/forbidden", exchange -> reply(exchange, 403, "Access Denied"));
        server.start();
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
    }

    @Test
    void shouldSendHeaders() {
        String body = curl.get(baseUrl + "/headers", Map.of("Origin", "https://www.nba.com/"));

        assertThat(body).isEqualTo("https://www.nba.com/");
    }

    @Test
    void shouldAcceptCompression() {
        assertThat(curl.get(baseUrl + "/compression", Map.of())).contains("gzip");
    }

    @Test
    void shouldFailOnHttpError() {
        assertThatThrownBy(() -> curl.get(baseUrl + "/forbidden", Map.of())).isInstanceOf(CurlException.class).hasMessageContaining("403");
    }

    private static void reply(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
