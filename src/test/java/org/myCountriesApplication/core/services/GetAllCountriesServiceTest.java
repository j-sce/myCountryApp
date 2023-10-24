package org.myCountriesApplication.core.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myCountriesApplication.core.domain.Country;
import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetAllCountriesServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GetAllCountriesService service = new GetAllCountriesService();

    @Value("${get.all.countries.api.url}")
    private String url;

    @Test
    public void testGetAllCountriesWithSuccessfulResponse() {
        // arrange
        String json = "[{\"countryCode\":\"AD\",\"name\":\"Andorra\"},{\"countryCode\":\"AL\",\"name\":\"Albania\"}]";
        List<Country> expectedCountries = Arrays.asList(new Country("AD", "Andorra"), new Country("AL", "Albania"));
        Mockito.when(restTemplate.getForObject(Mockito.eq(url), Mockito.eq(String.class))).thenReturn(json);

        // act
        GetAllCountriesResponse response = service.getAllCountries();

        // assert
        assertEquals(expectedCountries, response.getCountries());
    }

    @Test
    public void testExecuteWithException() {
        // arrange
        Mockito.when(restTemplate.getForObject(Mockito.eq(url), Mockito.eq(String.class))).thenThrow(new RestClientException("Simulated API error"));

        // act
        GetAllCountriesResponse response = service.getAllCountries();

        // assert
        assertEquals(0, response.getCountries().size());
        assertEquals(Collections.emptyList(), response.getCountries());
    }
}