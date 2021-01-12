
package com.liferay.gasBudget.internal.dto;

public class SolarInputRequest {

    private String houseType;
    private Integer monthlyConsumption;
    private Integer annualConsumption;
    private String roofType;

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

    public String getRoofType() {
    return roofType;
    }

    public void setRoofType(String roofType) {
    this.roofType = roofType;
    }

}