package com.propertyviewerproject.propertyviewer.service;

import com.propertyviewerproject.propertyviewer.dto.GeoLocationResponse;
import com.propertyviewerproject.propertyviewer.dto.GeoLocationResponseData;
import com.propertyviewerproject.propertyviewer.entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeoLocationService {

    @Value("${geolocation.api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    public GeoLocationService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public GeoLocationResponseData getGeoLocationData(Building building){

        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://api.geoapify.com/v1/geocode/search")
                .queryParam("apiKey", apiKey)
                .queryParam("name", building.getName())
                .queryParam("street", building.getStreet())
                .queryParam("postcode", building.getPostalCode())
                .queryParam("city",building.getCity())
                .queryParam("country", building.getCountry())
                .encode()
                .toUriString();

        HttpEntity<GeoLocationResponse> response = restTemplate.getForEntity(
                urlTemplate,
                GeoLocationResponse.class
        );

        GeoLocationResponse geoLocationResponse = response.getBody();
        if (geoLocationResponse.getResults().isEmpty()){
            return new GeoLocationResponseData();
        }

        return geoLocationResponse.getResults().get(0);
    }
}
