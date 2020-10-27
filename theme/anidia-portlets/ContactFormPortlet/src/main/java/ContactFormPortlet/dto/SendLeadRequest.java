package ContactFormPortlet.dto;

import com.fasterxml.jackson.annotation.*;

public class SendLeadRequest {

    @JsonProperty("personalData")
    private PersonalData personalData;

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
