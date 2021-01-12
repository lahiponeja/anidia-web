package com.liferay.gasBudget.internal.dto;

public class SelectedSolarExtrasRequest {

    private Integer extraPanels;
    private Boolean triphasicExtra;
    private Boolean roofExtra;
    private Boolean pergolaExtra;
    private Boolean pipelineUnderground;
    private Integer battery;
    private Boolean carCharger;
    
    public Integer getExtraPanels() {
    return extraPanels;
    }
    
    public void setExtraPanels(Integer extraPanels) {
    this.extraPanels = extraPanels;
    }
    
    public Boolean getTriphasicExtra() {
    return triphasicExtra;
    }
    
    public void setTriphasicExtra(Boolean triphasicExtra) {
    this.triphasicExtra = triphasicExtra;
    }
    
    public Boolean getRoofExtra() {
    return roofExtra;
    }
    
    public void setRoofExtra(Boolean roofExtra) {
    this.roofExtra = roofExtra;
    }
    
    public Boolean getPergolaExtra() {
    return pergolaExtra;
    }
    
    public void setPergolaExtra(Boolean pergolaExtra) {
    this.pergolaExtra = pergolaExtra;
    }
    
    public Boolean getPipelineUnderground() {
    return pipelineUnderground;
    }
    
    public void setPipelineUnderground(Boolean pipelineUnderground) {
    this.pipelineUnderground = pipelineUnderground;
    }
    
    public Integer getBattery() {
    return battery;
    }
    
    public void setBattery(Integer battery) {
    this.battery = battery;
    }
    
    public Boolean getCarCharger() {
    return carCharger;
    }
    
    public void setCarCharger(Boolean carCharger) {
    this.carCharger = carCharger;
    }
    
}