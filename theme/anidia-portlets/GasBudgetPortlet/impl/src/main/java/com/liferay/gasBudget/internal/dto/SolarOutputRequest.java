
package com.liferay.gasBudget.internal.dto;


public class SolarOutputRequest {

    private String panelsType;
    private SolarSize size;
    private SolarInverter inverter;
    private String panelsExtra;
    private String triphasicExtra;
    private String inverterExtra;
    private String roofExtra;
    private String pergolaExtra;
    private String pipelineExtra;
    private String carCharger;
    private String battery;
    private String additionalPanelsInstallation;
    private String totalPrice;
    private SolarSuperiorInstallation superiorInstallation;

    public String getPanelsType() {
        return panelsType;
    }

    public void setPanelsType(String panelsType) {
        this.panelsType = panelsType;
    }

    public SolarSize getSize() {
        return size;
    }

    public void setSize(SolarSize size) {
        this.size = size;
    }

    public SolarInverter getInverter() {
        return inverter;
    }

    public void setInverter(SolarInverter inverter) {
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public SolarSuperiorInstallation getSuperiorInstallation() {
        return superiorInstallation;
    }

    public void setSuperiorInstallation(SolarSuperiorInstallation superiorInstallation) {
        this.superiorInstallation = superiorInstallation;
    }

}