package ContactFormPortlet.dto;

import com.fasterxml.jackson.annotation.*;

public class PersonalData {

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("ProdInterest")
    private String prodInterest;

    @JsonProperty("PrivacyPolicy")
    private String privacyPolicy;

    @JsonProperty("AcceptNotCom")
    private boolean acceptNotCom;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public boolean getAcceptNotCom() {
        return acceptNotCom;
    }

    public void setAcceptNotCom(boolean acceptNotCom) {
        this.acceptNotCom = acceptNotCom;
    }
}
