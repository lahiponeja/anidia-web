package com.liferay.sampleVue.internal.services;

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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.sampleVue.dto.v1_0.Address;
import com.liferay.sampleVue.dto.v1_0.Estate;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class SalesforceService {

	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_ADDRESSES_URL = System.getenv().get("SALESFORCE_ADDRESSES_URL");
	static String SALESFORCE_ESTATES_URL = System.getenv().get("SALESFORCE_ESTATES_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

	public List<Estate> getEstates(String municipalityId, String postalCode, String addressKind, String addressName) {
		List<Estate> estates = new ArrayList<Estate>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_ESTATES_URL);
		urlBuilder.append("?");
		urlBuilder.append("municipio_ine=");
		urlBuilder.append(municipalityId);
		urlBuilder.append("&codigo_postal=");
		urlBuilder.append(postalCode);
		urlBuilder.append("&tipo_y_nombre_de_via=");
		urlBuilder.append(addressKind);
		urlBuilder.append("+");
		urlBuilder.append(addressName);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting estates to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
//			responseJson = new JSONArray(response.body());
			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES\",\"Numero__c\":\"5\",\"Codigo_unico_portal__c\":\"22\",\"Segundo_numero_de_policia__c\":null}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return estates;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return estates;
		}

		// The response format is [{"attributes":{"type":"AggregateResult"},"Tipo_de_via__c":"CL","Nombre_de_via__c":"TEJEDORES","Numero__c":"5","Codigo_unico_portal__c":"22","Segundo_numero_de_policia__c":null}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject estateJson = null;
			try {
				estateJson = responseJson.getJSONObject(i);
				Estate estate = new Estate();
				estate.setAddressKind(estateJson.getString("Tipo_de_via__c"));
				estate.setAdressName(estateJson.getString("Nombre_de_via__c"));
				estate.setNumber(estateJson.getString("Numero__c"));
				estate.setGateId(estateJson.getString("Codigo_unico_portal__c"));
				estate.setAnnex(estateJson.getString("Segundo_numero_de_policia__c"));
				estates.add(estate);
			} catch (JSONException e) {
				System.out.println("Salesforce response: " + response.body());
				if(estateJson != null) {
					System.out.println("Json Object with error: " + estateJson.toString());
				}
				e.printStackTrace();
			}
		}
		return estates;
	}


	public List<Address> getAddresses(String municipalityId, String postalCode) {

		List<Address> addresses = new ArrayList<Address>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_ADDRESSES_URL);
		urlBuilder.append("?");
		urlBuilder.append("municipio_ine=");
		urlBuilder.append(municipalityId);
		urlBuilder.append("&codigo_postal=");
		urlBuilder.append(postalCode);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting addresses to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES\"},{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES 2\"}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return addresses;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return addresses;
		}

		// The response format is [{"attributes":{"type":"AggregateResult"},"Tipo_de_via__c":"CL","Nombre_de_via__c":"TEJEDORES"}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject addressJson = null;
			try {
				addressJson = responseJson.getJSONObject(i);
				Address address = new Address();
				address.setKind(addressJson.getString("Tipo_de_via__c"));
				address.setName(addressJson.getString("Nombre_de_via__c"));
				addresses.add(address);
			} catch (JSONException e) {
				System.out.println("Salesforce response: " + response.body());
				if(addressJson != null) {
					System.out.println("Json Object with error: " + addressJson.toString());
				}
				e.printStackTrace();
			}
		}
		return addresses;
	}

	private String getSalesforceToken() {

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
