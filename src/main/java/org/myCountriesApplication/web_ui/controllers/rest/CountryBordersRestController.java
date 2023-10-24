package org.myCountriesApplication.web_ui.controllers.rest;

import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetCountryBordersResponse;
import org.myCountriesApplication.core.services.GetCountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country-borders")
public class CountryBordersRestController {

    @Autowired
    GetCountryInfoService getCountryInfoService;

    @GetMapping(path = "/{countryCode}", produces = "application/json")
    public GetCountryBordersResponse getCountryBordersResponse(@PathVariable String countryCode) {
        GetCountryInfoRequest request = new GetCountryInfoRequest(countryCode);
        return getCountryInfoService.getCountryBorders(request);
    }

}
