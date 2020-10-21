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
import com.liferay.sampleVue.dto.v1_0.Property;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class SalesforceService {

	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_ADDRESSES_URL = System.getenv().get("SALESFORCE_ADDRESSES_URL");
	static String SALESFORCE_ESTATES_URL = System.getenv().get("SALESFORCE_ESTATES_URL");
	static String SALESFORCE_PROPERTIES_URL = System.getenv().get("SALESFORCE_PROPERTIES_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

	public List<Property> getProperties(String gateId) {
		List<Property> properties = new ArrayList<Property>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_ESTATES_URL);
		urlBuilder.append("?");
		urlBuilder.append("codigo_unico_portal=");
		urlBuilder.append(gateId);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting properties to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC8AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS 7 , B, E, BJ4\",\"Codigo_unico_inmueble__c\":\"I21864951\",\"Tipo_finca__c\":\"SH\",\"CUPS__c\":\"ES123456789012345678\",\"Bloque__c\":\"B\",\"Escalera__c\":\"E\",\"Planta__c\":\"BJ\",\"Puerta__c\":\"4\",\"Estado__c\":\"01\",\"Canon_IRC__c\":6.35,\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC8AAM\"},{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC9AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS7\",\"Codigo_unico_inmueble__c\":\"I16621155\",\"Tipo_finca__c\":\"SH\",\"Estado__c\":\"01\",\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC9AAM\"}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return properties;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return properties;
		}

		// The response format is [{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC8AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS 7 , B, E, BJ4\",\"Codigo_unico_inmueble__c\":\"I21864951\",\"Tipo_finca__c\":\"SH\",\"CUPS__c\":\"ES123456789012345678\",\"Bloque__c\":\"B\",\"Escalera__c\":\"E\",\"Planta__c\":\"BJ\",\"Puerta__c\":\"4\",\"Estado__c\":\"01\",\"Canon_IRC__c\":6.35,\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC8AAM\"},{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC9AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS7\",\"Codigo_unico_inmueble__c\":\"I16621155\",\"Tipo_finca__c\":\"SH\",\"Estado__c\":\"01\",\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC9AAM\"}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject propertyJson = null;
			try {
				propertyJson = responseJson.getJSONObject(i);
				Property property = new Property();
				property.setAddress(propertyJson.optString("Direccion_completa__c"));
				property.setPropertyId(propertyJson.optString("Codigo_unico_inmueble__c"));
				property.setBlock(propertyJson.optString("Bloque__c"));
				property.setLadder(propertyJson.optString("Escalera__c"));
				property.setFloor(propertyJson.optString("Planta__c"));
				property.setDoor(propertyJson.optString("Puerta__c"));
				property.setStatus(propertyJson.getString("Estado__c"));
				property.setContractStatus(propertyJson.optString("SAP_Estado_contrato_SAP__c"));
				properties.add(property);
			} catch (JSONException e) {
				System.out.println("Salesforce response: " + response.body());
				if(propertyJson != null) {
					System.out.println("Json Object with error: " + propertyJson.toString());
				}
				e.printStackTrace();
			}
		}
		return properties;
	}

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
		// We have to sent the number as empty
		urlBuilder.append("&numero=");

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
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES\",\"Numero__c\":\"5\",\"Codigo_unico_portal__c\":\"22\",\"Segundo_numero_de_policia__c\":null}]");
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
				estate.setAddressName(estateJson.getString("Nombre_de_via__c"));
				estate.setNumber(estateJson.getString("Numero__c"));
				estate.setGateId(estateJson.getString("Codigo_unico_portal__c"));
				estate.setAnnex(estateJson.optString("Segundo_numero_de_policia__c"));
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

		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			if(response != null) {
				System.out.println(response.body());
			}
			e.printStackTrace();
			return null;
		}

	  JSONObject json;
		try {
			json = new JSONObject(response.body());
		} catch (JSONException e) {
			System.out.println(response.body());
			e.printStackTrace();
			return null;
		}

		try {
			return json.getString("access_token");
		} catch (JSONException e) {
				System.out.println(json.toString());
				e.printStackTrace();
			return null;
		}
	}
}
