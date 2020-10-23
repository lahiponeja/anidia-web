package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class ExtrasInput {

    @JsonProperty("MetersBoilerToWindow")
    private Integer metersBoilerToWindow;
    @JsonProperty("MetersWaterIntake")
    private String metersWaterIntake;
    @JsonProperty("HasVentilationGrill")
    private String hasVentilationGrill;
    @JsonProperty("ControllHeatingFloor")
    private String controllHeatingFloor;
    @JsonProperty("ConvertDeviceKitchen")
    private String convertDeviceKitchen;
    @JsonProperty("RadiatorsBathroom")
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

    public String getRadiatorsBathroom() {
        return radiatorsBathroom;
    }

    public void setRadiatorsBathroom(String radiatorsBathroom) {
        this.radiatorsBathroom = radiatorsBathroom;
    }
}
