package org.myCountriesApplication.core.responses;

import org.myCountriesApplication.core.domain.Country;

import java.util.List;

public class GetAllCountriesResponse extends CoreResponse {

    private List<Country> countries;

    public GetAllCountriesResponse(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

}
