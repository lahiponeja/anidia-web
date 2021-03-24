
package com.liferay.solarBudget.internal.dto;

public class SendLeadRequest {

    private CalculatorSolarRequest calculatorSolar;
    private PersonalDataRequest personalData;

    public CalculatorSolarRequest getCalculatorSolar() {
        return calculatorSolar;
    }

    public void setCalculatorSolar(CalculatorSolarRequest calculatorSolar) {
        this.calculatorSolar = calculatorSolar;
    }

    public PersonalDataRequest getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataRequest personalData) {
        this.personalData = personalData;
    }
}
