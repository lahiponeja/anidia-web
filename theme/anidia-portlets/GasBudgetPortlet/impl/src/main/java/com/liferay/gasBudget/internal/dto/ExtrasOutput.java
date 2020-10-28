package com.liferay.gasBudget.internal.dto;

public class ExtrasOutput {

    //@JsonProperty("MetersBoilerToWindow")
    private String metersBoilerToWindow;
    //@JsonProperty("MetersWaterIntake")
    private String metersWaterIntake;
    //@JsonProperty("HasVentilationGrill")
    private String hasVentilationGrill;
    //@JsonProperty("ControllHeatingFloor")
    private String controllHeatingFloor;
    //@JsonProperty("ConvertDeviceKitchen")
    private String convertDeviceKitchen;

    private String connectDeviceToKitchen;

    //@JsonProperty("RadiatorsBathroom")
    private String radiatorsBathroom;
    //@JsonProperty("ExtraTotalPrice")
    private String extraTotalPrice;

    public String getMetersBoilerToWindow() {
        return metersBoilerToWindow;
    }

    public void setMetersBoilerToWindow(String metersBoilerToWindow) {
        this.metersBoilerToWindow = metersBoilerToWindow;
    }

    public String getMetersWaterIntake() {
        return metersWaterIntake;
    }

    public void setMetersWaterIntake(String metersWaterIntake) {
        this.metersWaterIntake = metersWaterIntake;
    }

    public String getHasVentilationGrill() {
        return hasVentilationGrill;
    }

    public void setHasVentilationGrill(String hasVentilationGrill) {
        this.hasVentilationGrill = hasVentilationGrill;
    }

    public String getControllHeatingFloor() {
        return controllHeatingFloor;
    }

    public void setControllHeatingFloor(String controllHeatingFloor) {
        this.controllHeatingFloor = controllHeatingFloor;
    }

    public String getConvertDeviceKitchen() {
        return convertDeviceKitchen;
    }

    public void setConvertDeviceKitchen(String convertDeviceKitchen) {
        this.convertDeviceKitchen = convertDeviceKitchen;
    }

    public String getConnectDeviceToKitchen() {
        return connectDeviceToKitchen;
    }

    public void setConnectDeviceToKitchen(String connectDeviceToKitchen) {
        this.connectDeviceToKitchen = connectDeviceToKitchen;
    }

    public String getRadiatorsBathroom() {
        return radiatorsBathroom;
    }

    public void setRadiatorsBathroom(String radiatorsBathroom) {
        this.radiatorsBathroom = radiatorsBathroom;
    }

    public String getExtraTotalPrice() {
        return extraTotalPrice;
    }

    public void setExtraTotalPrice(String extraTotalPrice) {
        this.extraTotalPrice = extraTotalPrice;
    }
}
