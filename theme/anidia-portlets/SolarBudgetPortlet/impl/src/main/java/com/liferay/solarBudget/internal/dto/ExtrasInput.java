package com.liferay.solarBudget.internal.dto;

public class ExtrasInput {

    //@JsonProperty("MetersBoilerToWindow")
    private Integer metersBoilerToWindow;
    //@JsonProperty("MetersWaterIntake")
    private String metersWaterIntake;
    //@JsonProperty("HasVentilationGrill")
    private Boolean hasVentilationGrill;
    //@JsonProperty("ControllHeatingFloor")
    private Boolean controllHeatingFloor;
    //@JsonProperty("ConvertDeviceKitchen")
    private Boolean convertDeviceKitchen;

    private Boolean connectDeviceToKitchen;
    //@JsonProperty("RadiatorsBathroom")
    private String radiatorsBathroom;

    public Integer getMetersBoilerToWindow() {
        return metersBoilerToWindow;
    }

    public void setMetersBoilerToWindow(Integer metersBoilerToWindow) {
        this.metersBoilerToWindow = metersBoilerToWindow;
    }

    public String getMetersWaterIntake() {
        return metersWaterIntake;
    }

    public void setMetersWaterIntake(String metersWaterIntake) {
        this.metersWaterIntake = metersWaterIntake;
    }

    public Boolean getHasVentilationGrill() {
        return hasVentilationGrill;
    }

    public void setHasVentilationGrill(Boolean hasVentilationGrill) {
        this.hasVentilationGrill = hasVentilationGrill;
    }

    public Boolean getControllHeatingFloor() {
        return controllHeatingFloor;
    }

    public void setControllHeatingFloor(Boolean controllHeatingFloor) {
        this.controllHeatingFloor = controllHeatingFloor;
    }

    public Boolean getConvertDeviceKitchen() {
        return convertDeviceKitchen;
    }

    public void setConvertDeviceKitchen(Boolean convertDeviceKitchen) {
        this.convertDeviceKitchen = convertDeviceKitchen;
    }

    public Boolean getConnectDeviceToKitchen() {
        return connectDeviceToKitchen;
    }

    public void setConnectDeviceToKitchen(Boolean connectDeviceToKitchen) {
        this.connectDeviceToKitchen = connectDeviceToKitchen;
    }

    public String getRadiatorsBathroom() {
        return radiatorsBathroom;
    }

    public void setRadiatorsBathroom(String radiatorsBathroom) {
        this.radiatorsBathroom = radiatorsBathroom;
    }
}
