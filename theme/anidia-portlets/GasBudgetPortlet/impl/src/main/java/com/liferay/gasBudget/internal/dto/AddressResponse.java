package com.liferay.gasBudget.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class AddressResponse {

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("Tipo_de_via__c")
    private String tipoDeViaC;

    @JsonProperty("Nombre_de_via__c")
    private String nombreDeViaC;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getTipoDeViaC() {
        return tipoDeViaC;
    }

    public void setTipoDeViaC(String tipoDeViaC) {
        this.tipoDeViaC = tipoDeViaC;
    }

    public String getNombreDeViaC() {
        return nombreDeViaC;
    }

    public void setNombreDeViaC(String nombreDeViaC) {
        this.nombreDeViaC = nombreDeViaC;
    }
}
