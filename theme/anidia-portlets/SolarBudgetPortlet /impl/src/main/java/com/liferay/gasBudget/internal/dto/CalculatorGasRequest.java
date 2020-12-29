
package com.liferay.gasBudget.internal.dto;

public class CalculatorGasRequest {

    //@JsonProperty("input")
    private InputRequest input;
    //@JsonProperty("output")
    private OutputRequest output;

    public InputRequest getInput() {
        return input;
    }

    public void setInput(InputRequest input) {
        this.input = input;
    }

    public OutputRequest getOutput() {
        return output;
    }

    public void setOutput(OutputRequest output) {
        this.output = output;
    }
}
