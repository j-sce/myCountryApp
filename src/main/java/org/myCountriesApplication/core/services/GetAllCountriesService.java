package org.myCountriesApplication.core.services;

import com.google.gson.Gson;
import org.myCountriesApplication.core.domain.Country;
import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component

public class GetAllCountriesService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${get.all.countries.api.url}")
    private String url;

    public GetAllCountriesResponse getAllCountries() {
        try {
            String json = getJsonFromUrl();
            List<Country> countries = parseJsonToCountryList(json);
            return new GetAllCountriesResponse(countries);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private String getJsonFromUrl() {
        return restTemplate.getForObject(this.url, String.class);
    }

    private List<Country> parseJsonToCountryList(String json) {
        Gson gson = new Gson();
        Country[] countryArray = gson.fromJson(json, Country[].class);
        return Arrays.asList(countryArray);
    }

    private GetAllCountriesResponse handleException(Exception e) {
        e.printStackTrace();
        return new GetAllCountriesResponse(Collections.emptyList());
    }

}