package org.myCountriesApplication.core.services;

import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetCountryBordersResponse;
import org.myCountriesApplication.core.responses.GetCountryInfoResponse;
import org.myCountriesApplication.core.validators.CoreError;
import org.myCountriesApplication.core.validators.GetCountryInfoRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetCountryInfoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GetCountryInfoRequestValidator validator;

    @InjectMocks
    private GetCountryInfoService service;

    @Test
    public void testGetCountryInformationWithValidRequestAndSuccessfulResponse() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("CA");
        String json = "{\"commonName\":\"Canada\",\"officialName\":\"Canada\",\"countryCode\":\"CA\",\"region\":\"Americas\",\"borders\":" + "[{\"commonName\":\"United States\",\"officialName\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"Americas\",\"borders\":null}]}";

        Mockito.when(validator.validate(request)).thenReturn(Collections.emptyList());
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(json);

        // act
        GetCountryInfoResponse response = service.getCountryInformation(request);

        // assert
        assertEquals("Canada", response.getCountryInfo().getCommonName());
        assertEquals("CA", response.getCountryInfo().getCountryCode());
        assertNull(response.getErrors());
    }

    @Test
    public void testGetCountryInformationWithInvalidRequest() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("InvalidCountryCode");
        List<CoreError> validationErrors = new ArrayList<>();
        validationErrors.add(new CoreError("Country code", " InvalidCountryCode has different format!"));
        validationErrors.add(new CoreError("Country code", " InvalidCountryCode does not exist!"));
        Mockito.when(validator.validate(request)).thenReturn(validationErrors);

        // act
        GetCountryInfoResponse response = service.getCountryInformation(request);

        // assert
        assertNull(response.getCountryInfo());
        assertEquals(validationErrors, response.getErrors());
        assertEquals("Country code", response.getErrors().get(0).getField());
        assertEquals(" InvalidCountryCode has different format!", response.getErrors().get(0).getMessage());
        assertEquals("Country code", response.getErrors().get(1).getField());
        assertEquals(" InvalidCountryCode does not exist!", response.getErrors().get(1).getMessage());
    }

    @Test
    public void testExecuteWithException() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("LV");
        Mockito.when(validator.validate(request)).thenReturn(Collections.emptyList());
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenThrow(new RuntimeException("Simulated API error"));

        // act
        GetCountryInfoResponse response = service.getCountryInformation(request);

        // assert
        assertFalse(response.getErrors().isEmpty());
        assertEquals(1, response.getErrors().size());
        assertEquals("request failed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void testGetCountryBordersWithValidRequestAndSuccessfulResponse() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("US");
        String json = "{\"commonName\":\"United States\",\"officialName\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"Americas\",\"borders\":" + "[{\"commonName\":\"Canada\",\"officialName\":\"Canada\",\"countryCode\":\"CA\",\"region\":\"Americas\",\"borders\":null}," + "{\"commonName\":\"Mexico\",\"officialName\":\"United Mexican States\",\"countryCode\":\"MX\",\"region\":\"Americas\",\"borders\":null}]}";
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(json);

        // act
        GetCountryInfoResponse infoResponse = service.getCountryInformation(request);
        GetCountryBordersResponse bordersResponse = service.getCountryBorders(request);

        // assert
        assertEquals("United States", infoResponse.getCountryInfo().getCommonName());
        assertEquals("Canada", infoResponse.getCountryInfo().getBorders()[0].getCommonName());
        assertEquals("Mexico", infoResponse.getCountryInfo().getBorders()[1].getCommonName());
        assertEquals("Canada", bordersResponse.getBorders().get(0));
        assertEquals("Mexico", bordersResponse.getBorders().get(1));
    }

    @Test
    public void testGetCountryBordersWithInvalidRequest() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("InvalidCountryCode");
        List<CoreError> validationErrors = new ArrayList<>();
        validationErrors.add(new CoreError("Country code", " InvalidCountryCode has different format!"));
        validationErrors.add(new CoreError("Country code", " InvalidCountryCode does not exist!"));
        Mockito.when(validator.validate(request)).thenReturn(validationErrors);

        // act
        GetCountryBordersResponse response = service.getCountryBorders(request);

        // assert
        assertNull(response.getBorders());
        assertEquals(validationErrors, response.getErrors());
    }

    @Test
    public void testGetCountryBordersWithApiError() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("LV");
        Mockito.when(validator.validate(request)).thenReturn(Collections.emptyList());
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenThrow(new RuntimeException("Simulated API error"));

        // act
        GetCountryBordersResponse response = service.getCountryBorders(request);

        // assert
        assertFalse(response.getErrors().isEmpty());
        assertEquals(1, response.getErrors().size());
        assertEquals("request failed", response.getErrors().get(0).getMessage());
    }

}