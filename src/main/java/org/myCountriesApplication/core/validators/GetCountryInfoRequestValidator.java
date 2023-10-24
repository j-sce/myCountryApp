package org.myCountriesApplication.core.validators;

import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.myCountriesApplication.core.services.GetAllCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GetCountryInfoRequestValidator {

    @Autowired
    private GetAllCountriesService getAllCountriesService;

    public List<CoreError> validate(GetCountryInfoRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateCountryCodeIsNotEmpty(request).ifPresent(errors::add);
        if(errors.isEmpty()){
        validateCountryCodeFormat(request).ifPresent(errors::add);
        validateAvailableCountry(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateCountryCodeIsNotEmpty(GetCountryInfoRequest request) {
        return (request.getCountryCode() == null) ? Optional.of(new CoreError("Country code", " must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateCountryCodeFormat(GetCountryInfoRequest request) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher = pattern.matcher(request.getCountryCode());
        boolean matchFound = matcher.find();
        return (!matchFound) ? Optional.of(new CoreError("Country code", " " +request.getCountryCode() + " has different format!")) : Optional.empty();
    }

    private Optional<CoreError> validateAvailableCountry(GetCountryInfoRequest request) {
        GetAllCountriesResponse response = getAllCountriesService.getAllCountries();
        return (!response.getCountries().toString().contains(request.getCountryCode())) ? Optional.of(new CoreError("Country code", " " +request.getCountryCode() + " does not exist!")) : Optional.empty();
    }

}
