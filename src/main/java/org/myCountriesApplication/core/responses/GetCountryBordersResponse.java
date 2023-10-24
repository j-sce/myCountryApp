package org.myCountriesApplication.core.responses;

import org.myCountriesApplication.core.validators.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetCountryBordersResponse extends CoreResponse {

    private List<String> borders;

    public GetCountryBordersResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetCountryBordersResponse(ArrayList<String> borders) {
        this.borders = borders;
    }

    public List<String> getBorders() {
        return borders;
    }

}
