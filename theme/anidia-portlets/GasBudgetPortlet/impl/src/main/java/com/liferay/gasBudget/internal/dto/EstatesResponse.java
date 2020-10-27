package com.liferay.gasBudget.internal.dto;

import java.util.*;

public class EstatesResponse {

    List<EstateResponse> estateResponses;

    public List<EstateResponse> getEstateResponses() {
        return estateResponses;
    }

    public void setEstateResponses(
        List<EstateResponse> estateResponses) {
        this.estateResponses = estateResponses;
    }
}
