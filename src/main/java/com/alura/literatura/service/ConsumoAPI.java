package com.alura.literatura.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private final HttpClient client = HttpClient.newHttpClient();

    public String obtenerDatos(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Error al consultar la API. Código HTTP: " + response.statusCode());
            }

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No fue posible realizar la consulta a la API.", e);
        }
    }
}

