
package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class OutputRequest {

    @JsonProperty("BaseBadget")
    private String baseBadget;
    @JsonProperty("Bonus")
    private String bonus;
    @JsonProperty("Equipment")
    private String equipment;
    @JsonProperty("Extras")
    private ExtrasOutput extrasOutput;
    @JsonProperty("Iva21")
    private String iva21;
    @JsonProperty("ProposedPack")
    private String proposedPack;
    @JsonProperty("TotalBudget")
    private String totalBudget;
    @JsonProperty("TotalPVP")
    private String totalPVP;

    public String getBaseBadget() {
        return baseBadget;
    }

    public void setBaseBadget(String baseBadget) {
        this.baseBadget = baseBadget;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public ExtrasOutput getExtrasOutput() {
        return extrasOutput;
    }

    public void setExtrasOutput(ExtrasOutput extrasOutput) {
        this.extrasOutput = extrasOutput;
    }

    public String getIva21() {
        return iva21;
    }

    public void setIva21(String iva21) {
        this.iva21 = iva21;
    }

    public String getProposedPack() {
        return proposedPack;
    }

    public void setProposedPack(String proposedPack) {
        this.proposedPack = proposedPack;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getTotalPVP() {
        return totalPVP;
    }

    public void setTotalPVP(String totalPVP) {
        this.totalPVP = totalPVP;
    }
}
