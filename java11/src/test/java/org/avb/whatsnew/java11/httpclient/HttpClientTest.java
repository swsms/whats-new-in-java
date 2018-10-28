package org.avb.whatsnew.java11.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Java 11 standardizes a new http client introduced in Java 9.
 * The client supports both HTTP/1.1 and HTTP/2.
 */
public class HttpClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);
    private static final URI GITHUB_HTTP2_URL = URI.create("https://http2.github.io");
    private static final String LINE_SEPARATOR = "=".repeat(80);

    @Test
    public void testSyncGetRequestUsingHttp2() throws InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(GITHUB_HTTP2_URL)
                .GET()
                .build();

        var httpClient = HttpClient.newHttpClient();
        var bodyHandler = HttpResponse.BodyHandlers.ofString();

        try {
            var response = httpClient.send(request, bodyHandler);
            printResponse(response);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testAsyncGetRequestUsingHttp2() {
        var request = HttpRequest.newBuilder()
                .uri(GITHUB_HTTP2_URL)
                .GET()
                .build();

        var httpClient = HttpClient.newHttpClient();
        var bodyHandler = HttpResponse.BodyHandlers.ofString();

        httpClient
                .sendAsync(request, bodyHandler)
                .thenAccept(HttpClientTest::printResponse)
                .join();
    }

    private static void printResponse(HttpResponse<String> response) {
        LOGGER.info("Protocol version: {}", response.version());
        LOGGER.info("Status code: {}", response.statusCode());
        response.headers()
                .map()
                .forEach((name, values) -> LOGGER.info(name + ": " + values));
        LOGGER.info("Body length {}", response.body().length());
    }
}
