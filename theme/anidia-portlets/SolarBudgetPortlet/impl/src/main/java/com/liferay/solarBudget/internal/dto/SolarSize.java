package com.liferay.solarBudget.internal.dto;


public class SolarSize {

    private String value;
    private String unitPrice;
    private String price;
    private String basePanels;
    private String totalPanels;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBasePanels() {
        return basePanels;
    }

    public void setBasePanels(String basePanels) {
        this.basePanels = basePanels;
    }

    public String getTotalPanels() {
        return totalPanels;
    }

    public void setTotalPanels(String totalPanels) {
        this.totalPanels = totalPanels;
    }

}