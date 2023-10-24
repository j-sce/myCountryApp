package org.myCountriesApplication.core.responses;

import org.myCountriesApplication.core.domain.CountryInfo;
import org.myCountriesApplication.core.validators.CoreError;

import java.util.List;

public class GetCountryInfoResponse extends CoreResponse {
    private CountryInfo countryInfo;

    public GetCountryInfoResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetCountryInfoResponse(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

}
