package GasBudgetRequestPortlet.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import GasBudgetRequestPortlet.dto.AddressDTO;

public class AddressService {

	public List<AddressDTO> getMunicipalties() throws IOException {
		InputStream inputStream = getClass().getResourceAsStream("municipalities.csv");
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<AddressDTO> addresses = new ArrayList<AddressDTO>();

		while ((line = csvFile.readLine()) != null) {
			String[] columns = line.split(";", -1);
			addresses.add(new AddressDTO(columns[0], columns[1], columns[2], columns[3]));
		}

		return addresses;

	}

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
}
