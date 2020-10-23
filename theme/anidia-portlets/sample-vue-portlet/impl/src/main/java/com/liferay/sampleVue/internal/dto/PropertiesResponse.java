package com.liferay.sampleVue.internal.dto;

import java.util.*;

public class PropertiesResponse {

    List<PropertyResponse> propertyResponses;

    public List<PropertyResponse> getPropertyResponses() {
        return propertyResponses;
    }

    public void setPropertyResponses(
        List<PropertyResponse> propertyResponses) {
        this.propertyResponses = propertyResponses;
    }
}
