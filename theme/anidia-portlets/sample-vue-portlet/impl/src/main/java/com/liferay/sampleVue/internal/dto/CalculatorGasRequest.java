
package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class CalculatorGasRequest {

    @JsonProperty("input")
    private InputRequest inputRequest;
    @JsonProperty("output")
    private OutputRequest outputRequest;

    public InputRequest getInputRequest() {
        return inputRequest;
    }

    public void setInputRequest(InputRequest inputRequest) {
        this.inputRequest = inputRequest;
    }

    public OutputRequest getOutputRequest() {
        return outputRequest;
    }

    public void setOutputRequest(OutputRequest outputRequest) {
        this.outputRequest = outputRequest;
    }
}
