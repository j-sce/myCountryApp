package org.myCountriesApplication.core.services;

import com.google.gson.Gson;
import org.myCountriesApplication.core.domain.Border;
import org.myCountriesApplication.core.domain.CountryInfo;
import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetCountryBordersResponse;
import org.myCountriesApplication.core.responses.GetCountryInfoResponse;
import org.myCountriesApplication.core.validators.CoreError;
import org.myCountriesApplication.core.validators.GetCountryInfoRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCountryInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetCountryInfoRequestValidator validator;

    @Value("${get.country.info.for.given.country.api.url}")
    private String url;

    public GetCountryInfoResponse getCountryInformation(GetCountryInfoRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new GetCountryInfoResponse(errors);
        }

        String countryUrl = url + request.getCountryCode();

        try {
            String json = restTemplate.getForObject(countryUrl, String.class);
            Gson gson = new Gson();
            CountryInfo countryInfo = gson.fromJson(json, CountryInfo.class);

            if (countryInfo != null) {
                return new GetCountryInfoResponse(countryInfo);
            } else {
                errors = new ArrayList<>();
                errors.add(new CoreError("Country", "not found!"));
                return new GetCountryInfoResponse(errors);
            }
        } catch (Exception e) {
            errors = new ArrayList<>();
            errors.add(new CoreError("API", "request failed"));
            return new GetCountryInfoResponse(errors);
        }
    }

    public GetCountryBordersResponse getCountryBorders(GetCountryInfoRequest request) {
        GetCountryInfoResponse infoResponse = getCountryInformation(request);

        if (infoResponse.hasErrors()) {
            return new GetCountryBordersResponse(infoResponse.getErrors());
        }

        Border[] borders = infoResponse.getCountryInfo().getBorders();
        ArrayList<String> borderNames = extractBorderNames(borders);

        return new GetCountryBordersResponse(borderNames);
    }

    private ArrayList<String> extractBorderNames(Border[] borders) {
        return Arrays.stream(borders)
                .map(Border::getCommonName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
