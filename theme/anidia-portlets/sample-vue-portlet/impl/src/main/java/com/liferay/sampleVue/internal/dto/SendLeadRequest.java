
package com.liferay.sampleVue.internal.dto;

public class SendLeadRequest {

    //@JsonProperty("calculatorGas")
    private CalculatorGasRequest calculatorGas;
    //@JsonProperty("personalData")
    private PersonalDataRequest personalData;

    public CalculatorGasRequest getCalculatorGas() {
        return calculatorGas;
    }

    public void setCalculatorGas(CalculatorGasRequest calculatorGas) {
        this.calculatorGas = calculatorGas;
    }

    public PersonalDataRequest getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataRequest personalData) {
        this.personalData = personalData;
    }
}
