
package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class SendLeadRequest {

    @JsonProperty("calculatorGas")
    private CalculatorGasRequest calculatorGasRequest;
    @JsonProperty("personalData")
    private PersonalDataRequest personalDataRequest;

    public CalculatorGasRequest getCalculatorGasRequest() {
        return calculatorGasRequest;
    }

    public void setCalculatorGasRequest(
        CalculatorGasRequest calculatorGasRequest) {
        this.calculatorGasRequest = calculatorGasRequest;
    }

    public PersonalDataRequest getPersonalDataRequest() {
        return personalDataRequest;
    }

    public void setPersonalDataRequest(
        PersonalDataRequest personalDataRequest) {
        this.personalDataRequest = personalDataRequest;
    }
}
