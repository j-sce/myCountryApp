package org.myCountriesApplication.core.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myCountriesApplication.core.domain.Country;
import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.myCountriesApplication.core.services.GetAllCountriesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCountryInfoRequestValidatorTest {

    @Mock
    private GetAllCountriesService getAllCountriesService;

    @InjectMocks
    private GetCountryInfoRequestValidator validator;


    @Test
    public void testValidatorWithValidRequest() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("LV");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("LV", "Latvia"));
        countries.add(new Country("US", "United States"));
        GetAllCountriesResponse response = new GetAllCountriesResponse(countries);
        Mockito.when(getAllCountriesService.getAllCountries()).thenReturn(response);

        // act
        List<CoreError> errors = validator.validate(request);

        // assert
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenCountryCodeIsEmpty() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest(null);

        // act
        List<CoreError> errors = validator.validate(request);

        // assert
        assertEquals(1, errors.size());
        assertEquals("Country code", errors.get(0).getField());
        assertEquals(" must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenCountryDoesNotExist() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("LL");
        GetAllCountriesResponse response = new GetAllCountriesResponse(Collections.emptyList());
        Mockito.when(getAllCountriesService.getAllCountries()).thenReturn(response);

        // act
        List<CoreError> errors = validator.validate(request);

        // assert
        assertEquals(1, errors.size());
        assertEquals("Country code", errors.get(0).getField());
        assertEquals(" LL does not exist!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenCountryCodeFormatIsWrong() {
        // arrange
        GetCountryInfoRequest request = new GetCountryInfoRequest("LL1");
        GetAllCountriesResponse response = new GetAllCountriesResponse(Collections.emptyList());
        Mockito.when(getAllCountriesService.getAllCountries()).thenReturn(response);

        // act
        List<CoreError> errors = validator.validate(request);

        // assert
        assertEquals(2, errors.size());
        assertEquals("Country code", errors.get(0).getField());
        assertEquals(" LL1 has different format!", errors.get(0).getMessage());
        assertEquals("Country code", errors.get(1).getField());
        assertEquals(" LL1 does not exist!", errors.get(1).getMessage());
    }

}

