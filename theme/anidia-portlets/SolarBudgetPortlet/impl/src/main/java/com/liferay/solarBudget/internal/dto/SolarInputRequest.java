
package com.liferay.solarBudget.internal.dto;

public class SolarInputRequest {

    private String houseType;
    private Integer monthlyConsumption;
    private Integer annualConsumption;

    public String getHouseType() {
    return houseType;
    }

    public void setHouseType(String houseType) {
    this.houseType = houseType;
    }

    public Integer getMonthlyConsumption() {
    return monthlyConsumption;
    }

    public void setMonthlyConsumption(Integer monthlyConsumption) {
    this.monthlyConsumption = monthlyConsumption;
    }

    public Integer getAnnualConsumption() {
    return annualConsumption;
    }

    public void setAnnualConsumption(Integer annualConsumption) {
    this.annualConsumption = annualConsumption;
    }

}