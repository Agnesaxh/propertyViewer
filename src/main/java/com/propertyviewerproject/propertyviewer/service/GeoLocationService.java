package com.propertyviewerproject.propertyviewer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.propertyviewerproject.propertyviewer.dto.GeoLocationResponse;
import com.propertyviewerproject.propertyviewer.dto.GeoLocationResponseData;
import com.propertyviewerproject.propertyviewer.entity.Building;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeoLocationService {

    @Value("${geolocation.api.key}")
    private String apiKey;

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public GeoLocationService(
            HttpClient httpClient,
            ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public GeoLocationResponseData getGeoLocationData(Building building) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://api.geoapify.com/v1/geocode/search")
                .queryParam("apiKey", apiKey)
                .queryParam("name", building.getName())
                .queryParam("street", building.getStreet())
                .queryParam("postcode", building.getPostalCode())
                .queryParam("city", building.getCity())
                .queryParam("country", building.getCountry())
                .queryParam("format", "json")
                .encode()
                .toUriString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlTemplate))
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        GeoLocationResponse geoLocationResponse;
        try {
            geoLocationResponse = objectMapper.readValue(response.body(), GeoLocationResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (geoLocationResponse.getResults() == null || geoLocationResponse.getResults().length == 0) {
            return new GeoLocationResponseData("0", "0");
        }

        return geoLocationResponse.getResults()[0];
    }
}
