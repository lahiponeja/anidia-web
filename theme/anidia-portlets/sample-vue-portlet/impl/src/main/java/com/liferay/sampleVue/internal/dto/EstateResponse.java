package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class EstateResponse {

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("Tipo_de_via__c")
    private String tipoDeViaC;

    @JsonProperty("Nombre_de_via__c")
    private String nombreDeViaC;

    @JsonProperty("Numero__c")
    private String numeroC;

    @JsonProperty("Codigo_unico_portal__c")
    private String codigoUnicoPortalC;

    @JsonProperty("Segundo_numero_de_policia__c")
    private String segundoNumeroDePoliciaC;

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

    public String getNumeroC() {
        return numeroC;
    }

    public void setNumeroC(String numeroC) {
        this.numeroC = numeroC;
    }

    public String getCodigoUnicoPortalC() {
        return codigoUnicoPortalC;
    }

    public void setCodigoUnicoPortalC(String codigoUnicoPortalC) {
        this.codigoUnicoPortalC = codigoUnicoPortalC;
    }

    public String getSegundoNumeroDePoliciaC() {
        return segundoNumeroDePoliciaC;
    }

    public void setSegundoNumeroDePoliciaC(String segundoNumeroDePoliciaC) {
        this.segundoNumeroDePoliciaC = segundoNumeroDePoliciaC;
    }
}
