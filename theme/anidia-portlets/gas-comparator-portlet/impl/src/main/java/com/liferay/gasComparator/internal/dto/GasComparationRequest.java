package com.liferay.gasComparator.internal.dto;

public class GasComparationRequest {

    private Integer anualConsumptionButane;
    private Integer anualConsumptionGLP;
    private Integer anualConsumptionGOC;
    private Integer anualConsuptionElectricity;
    private String acsUse;
    private String heatingUse;
    private String kitchenUse;

    public Integer getAnualConsumptionButane() {
        return anualConsumptionButane;
    }

    public void setAnualConsumptionButane(Integer anualConsumptionButane) {
        this.anualConsumptionButane = anualConsumptionButane;
    }

    public Integer getAnualConsumptionGLP() {
        return anualConsumptionGLP;
    }

    public void setAnualConsumptionGLP(Integer anualConsumptionGLP) {
        this.anualConsumptionGLP = anualConsumptionGLP;
    }

    public Integer getAnualConsumptionGOC() {
        return anualConsumptionGOC;
    }

    public void setAnualConsumptionGOC(Integer anualConsumptionGOC) {
        this.anualConsumptionGOC = anualConsumptionGOC;
    }

    public Integer getAnualConsuptionElectricity() {
        return anualConsuptionElectricity;
    }

    public void setAnualConsuptionElectricity(Integer anualConsuptionElectricity) {
        this.anualConsuptionElectricity = anualConsuptionElectricity;
    }

    public String getAcsUse() {
        return acsUse;
    }

    public void setAcsUse(String acsUse) {
        this.acsUse = acsUse;
    }

    public String getHeatingUse() {
        return heatingUse;
    }

    public void setHeatingUse(String heatingUse) {
        this.heatingUse = heatingUse;
    }

    public String getKitchenUse() {
        return kitchenUse;
    }

    public void setKitchenUse(String kitchenUse) {
        this.kitchenUse = kitchenUse;
    }
}
