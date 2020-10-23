package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class Attributes {

    @JsonProperty("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
