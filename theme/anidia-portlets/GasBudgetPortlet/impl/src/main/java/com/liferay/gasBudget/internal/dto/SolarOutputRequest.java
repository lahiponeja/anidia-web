
package com.liferay.gasBudget.internal.dto;


public class SolarOutputRequest {

    private String panelsType;
    private String size;//
    private String inverter;//
    private String panelsExtra;
    private String triphasicExtra;
    private String inverterExtra;
    private String roofExtra;
    private String pergolaExtra;
    private String pipelineExtra;
    private String carCharger;
    private String battery;
    private String additionalPanelsInstallation;
    private String totalPrize;
    private String superiorInstallation;

    public String getPanelsType() {
        return panelsType;
    }

    public void setPanelsType(String panelsType) {
        this.panelsType = panelsType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInverter() {
        return inverter;
    }

    public void setInverter(String inverter) {
        this.inverter = inverter;
    }

    public String getPanelsExtra() {
        return panelsExtra;
    }

    public void setPanelsExtra(String panelsExtra) {
        this.panelsExtra = panelsExtra;
    }

    public String getTriphasicExtra() {
        return triphasicExtra;
    }

    public void setTriphasicExtra(String triphasicExtra) {
        this.triphasicExtra = triphasicExtra;
    }

    public String getInverterExtra() {
        return inverterExtra;
    }

    public void setInverterExtra(String inverterExtra) {
    this.inverterExtra = inverterExtra;
    }

    public String getRoofExtra() {
        return roofExtra;
    }

    public void setRoofExtra(String roofExtra) {
        this.roofExtra = roofExtra;
    }

    public String getPergolaExtra() {
        return pergolaExtra;
    }

    public void setPergolaExtra(String pergolaExtra) {
        this.pergolaExtra = pergolaExtra;
    }

    public String getPipelineExtra() {
        return pipelineExtra;
    }

    public void setPipelineExtra(String pipelineExtra) {
        this.pipelineExtra = pipelineExtra;
    }

    public String getCarCharger() {
        return carCharger;
    }

    public void setCarCharger(String carCharger) {
        this.carCharger = carCharger;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
    this.battery = battery;
    }

    public String getAdditionalPanelsInstallation() {
        return additionalPanelsInstallation;
    }

    public void setAdditionalPanelsInstallation(String additionalPanelsInstallation) {
        this.additionalPanelsInstallation = additionalPanelsInstallation;
    }

    public String getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(String totalPrize) {
        this.totalPrize = totalPrize;
    }

    public String getSuperiorInstallation() {
        return superiorInstallation;
    }

    public void setSuperiorInstallation(String superiorInstallation) {
        this.superiorInstallation = superiorInstallation;
    }

}