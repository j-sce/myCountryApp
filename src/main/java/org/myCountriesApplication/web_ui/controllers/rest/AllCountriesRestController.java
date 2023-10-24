package org.myCountriesApplication.web_ui.controllers.rest;

import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.myCountriesApplication.core.services.GetAllCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class AllCountriesRestController {

    @Autowired
    private GetAllCountriesService getAllCountriesService;

    @GetMapping(path = "/", produces = "application/json")
    public GetAllCountriesResponse getAllCountriesResponse() {
        return getAllCountriesService.getAllCountries();
    }

}
