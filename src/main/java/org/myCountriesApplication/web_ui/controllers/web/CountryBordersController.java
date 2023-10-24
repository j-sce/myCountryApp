package org.myCountriesApplication.web_ui.controllers.web;

import org.myCountriesApplication.core.requests.GetCountryInfoRequest;
import org.myCountriesApplication.core.responses.GetCountryBordersResponse;
import org.myCountriesApplication.core.services.GetCountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CountryBordersController {

    @Autowired
    private GetCountryInfoService getCountryInfoService;

    @GetMapping(value = "/country-borders")
    public String showGetCountryInfoPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetCountryInfoRequest());
        return "country-borders";
    }

    @PostMapping(value = "/country-borders")
    public String processGetCountryInfoRequest(@ModelAttribute(value = "request") GetCountryInfoRequest request, ModelMap modelMap) {
        GetCountryBordersResponse response = getCountryInfoService.getCountryBorders(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("borders", response.getBorders());
        }
        return "country-borders";
    }

}
