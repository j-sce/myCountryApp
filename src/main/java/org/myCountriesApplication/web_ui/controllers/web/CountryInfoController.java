package org.myCountriesApplication.web_ui.controllers.web;

import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetCountryInfoResponse;
import org.myCountriesApplication.core.services.GetCountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CountryInfoController {

    @Autowired
    private GetCountryInfoService countryInfoService;

    @GetMapping(value = "/country-info")
    public String showGetCountryInfoPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetCountryInfoRequest());
        return "country-info";
    }

    @PostMapping(value = "/country-info")
    public String processGetCountryInfoRequest(@ModelAttribute(value = "request") GetCountryInfoRequest request, ModelMap modelMap) {
        GetCountryInfoResponse response = countryInfoService.getCountryInformation(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("countryInfo", response.getCountryInfo());
        }
        return "country-info";
    }

}
