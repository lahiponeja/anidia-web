package com.liferay.gasBudget.internal.dto;

public class CalculatorSolarRequest {


    private SolarInputRequest input;
    private SolarOutputRequest output;
    private SelectedSolarExtrasRequest selectedExtras;
    private Boolean superiorInstallation;

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

}
