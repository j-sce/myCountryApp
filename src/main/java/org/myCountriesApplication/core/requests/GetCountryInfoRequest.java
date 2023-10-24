package org.myCountriesApplication.core.requests;

public class GetCountryInfoRequest {

    private String countryCode;

    public GetCountryInfoRequest() {
    }

    public GetCountryInfoRequest(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
