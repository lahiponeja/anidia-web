package com.liferay.solarBudget.internal.dto;

public class CalculatorSolarRequest {


    private SolarInputRequest input;
    private SolarOutputRequest output;
    private SelectedSolarExtrasRequest selectedExtras;
    private Boolean superiorInstallation;
    private String totalPrice;
    private String ivaPrice;
    private String totalPriceIva;
    private String installerCode;

    public SolarInputRequest getInput() {
        return input;
    }

    public void setInput(SolarInputRequest input) {
        this.input = input;
    }

    public SolarOutputRequest getOutput() {
        return output;
    }

    public void setOutput(SolarOutputRequest output) {
        this.output = output;
    }

    public SelectedSolarExtrasRequest getSelectedExtras() {
        return selectedExtras;
    }

    public void setSelectedExtras(SelectedSolarExtrasRequest selectedExtras) {
        this.selectedExtras = selectedExtras;
    }

    public Boolean getSuperiorInstallation(){
        return superiorInstallation;
    }

    public void setSuperiorInstallation(Boolean superiorInstallation) {
        this.superiorInstallation = superiorInstallation;
    }

    public String getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIvaPrice(){
        return ivaPrice;
    }

    public void setIvaPrice(String ivaPrice) {
        this.ivaPrice = ivaPrice;
    }

    public String getTotalPriceIva(){
        return totalPriceIva;
    }

    public void setTotalPriceIva(String totalPriceIva) {
        this.totalPriceIva = totalPriceIva;
    }

    public String getInstallerCode(){
        return installerCode;
    }

    public void setInstallerCode(String installerCode) {
        this.installerCode = installerCode;
    }

}
