
package com.liferay.gasBudget.internal.dto;

public class PersonalDataRequest {

    //@JsonProperty("AcceptNotCom")
    private Boolean acceptNotCom;
    //@JsonProperty("AddressBloq")
    private String addressBloq;
    //@JsonProperty("AddressDoor")
    private String addressDoor;
    //@JsonProperty("AddressNumber")
    private String addressNumber;
    //@JsonProperty("AddressPlant")
    private String addressPlant;
    //@JsonProperty("AddressPostalCode")
    private String addressPostalCode;
    //@JsonProperty("AddressStair")
    private String addressStair;
    //@JsonProperty("AddressStreet")
    private String addressStreet;
    //@JsonProperty("AddressTown")
    private String addressTown;
    //@JsonProperty("Email")
    private String email;
    //@JsonProperty("FirstName")
    private String firstName;
    //@JsonProperty("LastName")
    private String lastName;
    //@JsonProperty("Phone")
    private String phone;
    //@JsonProperty("ProdInterest")
    private String prodInterest;
    //@JsonProperty("PrivacyPolicy")
    private String privacyPolicy;

    private String codMunicipio;

    private String codFinca;

    private String codInmueble;

    public Boolean getAcceptNotCom() {
        return acceptNotCom;
    }

    public void setAcceptNotCom(Boolean acceptNotCom) {
        this.acceptNotCom = acceptNotCom;
    }

    public String getAddressBloq() {
        return addressBloq;
    }

    public void setAddressBloq(String addressBloq) {
        this.addressBloq = addressBloq;
    }

    public String getAddressDoor() {
        return addressDoor;
    }

    public void setAddressDoor(String addressDoor) {
        this.addressDoor = addressDoor;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressPlant() {
        return addressPlant;
    }

    public void setAddressPlant(String addressPlant) {
        this.addressPlant = addressPlant;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressStair() {
        return addressStair;
    }

    public void setAddressStair(String addressStair) {
        this.addressStair = addressStair;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProdInterest() {
        return prodInterest;
    }

    public void setProdInterest(String prodInterest) {
        this.prodInterest = prodInterest;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getCodFinca() {
        return codFinca;
    }

    public void setCodFinca(String codFinca) {
        this.codFinca = codFinca;
    }

    public String getCodInmueble() {
        return codInmueble;
    }

    public void setCodInmueble(String codInmueble) {
        this.codInmueble = codInmueble;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }
}
