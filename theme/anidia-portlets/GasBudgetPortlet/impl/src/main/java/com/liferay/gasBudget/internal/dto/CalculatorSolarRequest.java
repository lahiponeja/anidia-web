package com.liferay.gasBudget.internal.dto;

public class CalculatorSolarRequest {


    private SolarInputRequest input;
    private SolarOutputRequest output;
    private SelectedSolarExtrasRequest selectedExtras;
    private Boolean superiorInstallation;
    private String finalPrice;
    private String installatorId;

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

    public void setselectedExtras(SelectedSolarExtrasRequest selectedExtras) {
        this.selectedExtras = selectedExtras;
    }

    public Boolean getSuperiorInstallation(){
        return superiorInstallation;
    }

    public void setselectedExtras(SelectedSolarExtrasRequest selectedExtras) {
        this.selectedExtras = selectedExtras;
    }

    public Boolean getSuperiorInstallation(){
        return superiorInstallation;
    }

    public void setSuperiorInstallation(Boolean superiorInstallation) {
        this.superiorInstallation = superiorInstallation;
    }

    public String getFinalPrice(){
        return superiorInstallation;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getInstallatorId(){
        return installatorId;
    }

    public void setInstallatorId(String installatorId) {
        this.installatorId = installatorId;
    }

}
