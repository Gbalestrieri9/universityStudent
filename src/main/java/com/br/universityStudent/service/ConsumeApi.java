package com.br.universityStudent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumeApi {

    private final IConverteDados converteDados;

    public ConsumeApi(IConverteDados converteDados) {
        this.converteDados = converteDados;
    }

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public <T> T buscarDados(String url, Class<T> clazz) {
        String json = obterDados(url);
        if (json.trim().startsWith("<")) {
            throw new RuntimeException("Resposta inválida recebida da API.");
        }
        return converteDados.obterDados(json, clazz);
    }
}
