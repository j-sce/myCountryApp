package org.myCountriesApplication.web_ui.controllers.web;

import org.myCountriesApplication.core.responses.GetAllCountriesResponse;
import org.myCountriesApplication.core.services.GetAllCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllCountriesController {

    @Autowired
    private GetAllCountriesService getAllCountriesService;

    @GetMapping("/countries")
    public String getAllCountries(ModelMap modelMap) {
        GetAllCountriesResponse response = getAllCountriesService.getAllCountries();
        modelMap.addAttribute("countries", response.getCountries());
        return "/countries";
    }

}
