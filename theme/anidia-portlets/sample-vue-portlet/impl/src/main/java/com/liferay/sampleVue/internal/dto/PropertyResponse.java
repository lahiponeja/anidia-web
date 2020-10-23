package com.liferay.sampleVue.internal.dto;

import com.fasterxml.jackson.annotation.*;

public class PropertyResponse {

    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("Direccion_completa__c")
    private String direccionCompletaC;
    @JsonProperty("Codigo_unico_inmueble__c")
    private String codigoUnicoInmuebleC;
    @JsonProperty("Tipo_finca__c")
    private String tipoFincaC;
    @JsonProperty("CUPS__c")
    private String cUPSC;
    @JsonProperty("Bloque__c")
    private String bloqueC;
    @JsonProperty("Escalera__c")
    private String escaleraC;
    @JsonProperty("Planta__c")
    private String plantaC;
    @JsonProperty("Puerta__c")
    private String puertaC;
    @JsonProperty("Estado__c")
    private String estadoC;
    @JsonProperty("Canon_IRC__c")
    private Double canonIRCC;
    @JsonProperty("Finca__c")
    private String fincaC;
    @JsonProperty("Id")
    private String id;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getDireccionCompletaC() {
        return direccionCompletaC;
    }

    public void setDireccionCompletaC(String direccionCompletaC) {
        this.direccionCompletaC = direccionCompletaC;
    }

    public String getCodigoUnicoInmuebleC() {
        return codigoUnicoInmuebleC;
    }

    public void setCodigoUnicoInmuebleC(String codigoUnicoInmuebleC) {
        this.codigoUnicoInmuebleC = codigoUnicoInmuebleC;
    }

    public String getTipoFincaC() {
        return tipoFincaC;
    }

    public void setTipoFincaC(String tipoFincaC) {
        this.tipoFincaC = tipoFincaC;
    }

    public String getcUPSC() {
        return cUPSC;
    }

    public void setcUPSC(String cUPSC) {
        this.cUPSC = cUPSC;
    }

    public String getBloqueC() {
        return bloqueC;
    }

    public void setBloqueC(String bloqueC) {
        this.bloqueC = bloqueC;
    }

    public String getEscaleraC() {
        return escaleraC;
    }

    public void setEscaleraC(String escaleraC) {
        this.escaleraC = escaleraC;
    }

    public String getPlantaC() {
        return plantaC;
    }

    public void setPlantaC(String plantaC) {
        this.plantaC = plantaC;
    }

    public String getPuertaC() {
        return puertaC;
    }

    public void setPuertaC(String puertaC) {
        this.puertaC = puertaC;
    }

    public String getEstadoC() {
        return estadoC;
    }

    public void setEstadoC(String estadoC) {
        this.estadoC = estadoC;
    }

    public Double getCanonIRCC() {
        return canonIRCC;
    }

    public void setCanonIRCC(Double canonIRCC) {
        this.canonIRCC = canonIRCC;
    }

    public String getFincaC() {
        return fincaC;
    }

    public void setFincaC(String fincaC) {
        this.fincaC = fincaC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
