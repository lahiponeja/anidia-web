package ContactFormPortlet.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.json.JSONException;
import org.json.JSONObject;

import ContactFormPortlet.dto.LeadDTO;

public class SalesforceService {

	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_LEAD_URL = System.getenv().get("SALESFORCE_LEAD_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

  public String getSalesforceToken() {

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_TOKEN_URL);
		urlBuilder.append("?");
		urlBuilder.append("grant_type=password");
		urlBuilder.append("&client_id=");
		urlBuilder.append(SALESFORCE_CLIENT_ID);
		urlBuilder.append("&client_secret=");
		urlBuilder.append(SALESFORCE_CLIENT_SECRET);
		urlBuilder.append("&username=");
		urlBuilder.append(SALESFORCE_USERNAME);
		urlBuilder.append("&password=");
		urlBuilder.append(SALESFORCE_PASSWORD);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			POST(HttpRequest.BodyPublishers.noBody()).
			build();

		HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

	    JSONObject json;
		try {
			json = new JSONObject(response.body());
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		try {
			return json.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String sendLead(LeadDTO lead) throws JSONException{

		String token = this.getSalesforceToken();

		JSONObject jsonLead = new JSONObject();
		jsonLead.put("PersonalData", new JSONObject());
		jsonLead.getJSONObject("PersonalData").put("FirstName", lead.getFirstName());
		jsonLead.getJSONObject("PersonalData").put("LastName", lead.getLastName());
		jsonLead.getJSONObject("PersonalData").put("Email", lead.getEmail());
		jsonLead.getJSONObject("PersonalData").put("Phone", lead.getPhoneNumber());

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(SalesforceService.SALESFORCE_LEAD_URL)).
			header("Authorization", "Bearer " + token).
			POST(HttpRequest.BodyPublishers.ofString(jsonLead.toString())).
			build();

		HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return null;

	}

}
