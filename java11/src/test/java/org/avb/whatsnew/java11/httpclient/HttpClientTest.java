package org.avb.whatsnew.java11.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.*;

/**
 * Java 11 standardizes a new http client introduced in Java 9.
 * It supports HTTP/1.1, HTTP/2, WebSockets, and both sync and async requests.
 *
 * By default the client will send requests using HTTP/2.
 * If the server does not support HTTP/2 yet, the request will be downgraded to HTTP/1.1.
 *
 * HTTP/2 key features:
 * - header compression,
 * - single connection for parallel requests,
 * - fully multiplexed,
 * - server push,
 * - more compact binary format.
 *
 * More about HTTP/2: https://http2.github.io/faq/#what-are-the-key-differences-to-http1x
 */
public class HttpClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);
    private static final String LINE_SEPARATOR = "=".repeat(80);

    private static final Duration REQUEST_DURATION = Duration.ofSeconds(60);

    @DataProvider(name = "httpResourcesProvider")
    public static Object[][] resources() {
        return new Object[][] {
                { "https://http2.github.io" },
                { "https://github.io" }
        };
    }

    @Test(dataProvider = "httpResourcesProvider")
    public void testSyncGetRequest(String url) throws InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(REQUEST_DURATION)
                .GET() // default
                .build();

        var httpClient = HttpClient.newHttpClient();

        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            printResponse(response, false);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testSyncPostRequest() throws InterruptedException {
        var fakePostService = URI.create("https://jsonplaceholder.typicode.com/posts");

        var request = HttpRequest.newBuilder()
                .uri(fakePostService)
                .timeout(REQUEST_DURATION)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"title\":\"foo\", \"body\":\"bar\"}"))
                .build();

        var httpClient = HttpClient.newHttpClient();

        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            printResponse(response, true);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test(dataProvider = "httpResourcesProvider")
    public void testAsyncGetRequest(String url) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(REQUEST_DURATION)
                .GET()
                .build();

        var httpClient = HttpClient.newHttpClient();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> printResponse(response, false))
                .join();
    }

    @Test
    public void testWebSocket() throws InterruptedException {
        var echoServerURI = URI.create("wss://echo.websocket.org");
        var executor = Executors.newSingleThreadExecutor();
        var httpClient = HttpClient.newBuilder()
                .executor(executor)
                .build();

        var webSocket = httpClient.newWebSocketBuilder().buildAsync(echoServerURI, new WebSocket.Listener() {

            @Override
            public void onOpen(WebSocket webSocket) {
                LOGGER.info("Successfully connected to {}", echoServerURI);
                webSocket.sendText("Hello!", true);
                WebSocket.Listener.super.onOpen(webSocket);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                LOGGER.info("received: {}", data);
                return WebSocket.Listener.super.onText(webSocket, data, last);
            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                LOGGER.info("Closed with status " + statusCode + ", reason: " + reason);
                executor.shutdown();
                return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
            }
        }).join();

        Thread.sleep(1000);

        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok")
                .thenRun(() -> LOGGER.info("Sent close"));
    }

    @Test
    public void handleBodyAsReactiveStream() throws InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://emojitrack-gostreamer.herokuapp.com/subscribe/eps"))
                .timeout(REQUEST_DURATION)
                .GET()
                .build();

        var httpClient = HttpClient.newHttpClient();

        // subscriber
        // send async
    }


    private static void printResponse(HttpResponse<String> response, boolean printBody) {
        LOGGER.info("Protocol version: {}", response.version());
        LOGGER.info("Status code: {}", response.statusCode());
        response.headers()
                .map()
                .forEach((name, values) -> LOGGER.info(name + ": " + values));
        LOGGER.info("Body length {}", response.body().length());
        if (printBody) {
            LOGGER.info("Body {}", response.body());
        }
    }
}