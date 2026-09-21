package com.java11.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * JEP 321: HTTP Client (Standard).
 * java.net.http.HttpClient replaces the legacy HttpURLConnection, supports
 * HTTP/1.1 and HTTP/2, and offers both synchronous and asynchronous APIs.
 */
public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        String body = response.body();
        System.out.println("Body (first 200 chars): " + body.substring(0, Math.min(200, body.length())));
    }
}
