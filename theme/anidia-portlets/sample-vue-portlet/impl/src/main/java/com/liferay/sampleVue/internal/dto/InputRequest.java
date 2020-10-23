
package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;
import javax.annotation.*;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class InputRequest {

    @JsonProperty("ACSUse")
    private String aCSUse;
    @JsonProperty("BathroomNumber")
    private String bathroomNumber;
    @JsonProperty("BoilerLocation")
    private String boilerLocation;
    @JsonProperty("Extras")
    private ExtrasInput extras;
    @JsonProperty("FloorNumber")
    private String floorNumber;
    @JsonProperty("GasNaturalUse")
    private String gasNaturalUse;
    @JsonProperty("HeatingUse")
    private String heatingUse;
    @JsonProperty("HouseType")
    private String houseType;
    @JsonProperty("KitchenUse")
    private String kitchenUse;
    @JsonProperty("PersonsWater")
    private String personsWater;
    @JsonProperty("PropertyMeters")
    private String propertyMeters;
    @JsonProperty("StaysNumber")
    private Long staysNumber;
    @JsonProperty("ZipCode")
    private String zipCode;

    public String getACSUse() {
        return aCSUse;
    }

    public void setACSUse(String aCSUse) {
        this.aCSUse = aCSUse;
    }

    public String getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(String bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    public String getBoilerLocation() {
        return boilerLocation;
    }

    public void setBoilerLocation(String boilerLocation) {
        this.boilerLocation = boilerLocation;
    }

    public ExtrasInput getExtrasInput() {
        return extras;
    }

    public void setExtrasInput(ExtrasInput extras) {
        this.extras = extras;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getGasNaturalUse() {
        return gasNaturalUse;
    }

    public void setGasNaturalUse(String gasNaturalUse) {
        this.gasNaturalUse = gasNaturalUse;
    }

    public String getHeatingUse() {
        return heatingUse;
    }

    public void setHeatingUse(String heatingUse) {
        this.heatingUse = heatingUse;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getKitchenUse() {
        return kitchenUse;
    }

    public void setKitchenUse(String kitchenUse) {
        this.kitchenUse = kitchenUse;
    }

    public String getPersonsWater() {
        return personsWater;
    }

    public void setPersonsWater(String personsWater) {
        this.personsWater = personsWater;
    }

    public String getPropertyMeters() {
        return propertyMeters;
    }

    public void setPropertyMeters(String propertyMeters) {
        this.propertyMeters = propertyMeters;
    }

    public Long getStaysNumber() {
        return staysNumber;
    }

    public void setStaysNumber(Long staysNumber) {
        this.staysNumber = staysNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
