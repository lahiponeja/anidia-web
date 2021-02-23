
package com.liferay.gasBudget.internal.dto;

public class SendLeadRequest {

    private CalculatorGasRequest calculatorGas;
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
