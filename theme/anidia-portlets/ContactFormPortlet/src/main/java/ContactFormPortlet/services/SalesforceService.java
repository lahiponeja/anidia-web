package ContactFormPortlet.services;

import ContactFormPortlet.dto.*;
import ContactFormPortlet.exception.*;
import org.apache.commons.lang3.*;
import org.apache.commons.validator.routines.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class SalesforceService {

    static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
    static String SALESFORCE_LEAD_URL = System.getenv().get("SALESFORCE_LEAD_URL");
    static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
    static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
    static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
    static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

    public String getSalesforceToken() throws PortletException {

        RestTemplate restTemplate = new RestTemplate();

        AccessTokenResponse response = restTemplate.postForObject(
            getTokenUrl(), null, AccessTokenResponse.class);

        if (response != null && StringUtils.isNotEmpty(response.getAccessToken())) {
            return response.getAccessToken();
        } else {
            throw new PortletException(2, "Can not retrieve the access token");
        }
    }

    public void sendLead(LeadDTO lead) throws ValidationException, PortletException {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String accessToken = getSalesforceToken();
            validateSendLeadRequest(lead);

            SendLeadRequest sendLeadRequest = mapToSendLeadRequest(lead);
            HttpEntity<SendLeadRequest> entity = new HttpEntity<>(sendLeadRequest,
                getHeaderToken(accessToken));

            String url = getSendLeadUrl();
            restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PortletException(1, "There is a problem saving data");
        }
    }

    private void validateSendLeadRequest(LeadDTO leadDTO) throws ValidationException {
        boolean validEmail = EmailValidator.getInstance().isValid(leadDTO.getEmail());
        if (!validEmail) {
            throw new ValidationException(1, "Email lead is invalid");
        }
    }

    private SendLeadRequest mapToSendLeadRequest(LeadDTO leadDTO) {
        SendLeadRequest sendLeadRequest = new SendLeadRequest();
        PersonalData personalData = new PersonalData();

        personalData.setFirstName(leadDTO.getFirstName());
        personalData.setLastName(leadDTO.getLastName());
        personalData.setPhone(leadDTO.getPhonePrefix() + leadDTO.getPhoneNumber());
        personalData.setEmail(leadDTO.getEmail());
        personalData.setProdInterest(leadDTO.getProductType());

        sendLeadRequest.setPersonalData(personalData);

        return sendLeadRequest;
    }

    private HttpHeaders getHeaderToken(String authToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(authToken);
        return httpHeaders;
    }

    private String getTokenUrl() {
        return SALESFORCE_TOKEN_URL + "?grant_type=password&client_id=" + SALESFORCE_CLIENT_ID + "&client_secret=" + SALESFORCE_CLIENT_SECRET + "&username=" + SALESFORCE_USERNAME + "&password=" + SALESFORCE_PASSWORD;
    }

    private String getSendLeadUrl(){
        return SALESFORCE_LEAD_URL;
    }
}
