package com.liferay.solarBudget.internal.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.vulcan.pagination.Page;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.liferay.solarBudget.dto.v1_0.PostalCode;

public class Geocode{
    static String GEOCODE_LOGIN_URL = System.getenv().get("GEOCODE_LOGIN_URL");
    static String GEOCODE_MUNICIPALITIES_URL = System.getenv().get("GEOCODE_MUNICIPALITIES_URL");
    private String getGeocodeToken() {

        JSONObject jsonRequestBody = new JSONObject();
        try {
            jsonRequestBody.put("UserName", System.getenv().get("GEOCODE_USER_NAME"));
            jsonRequestBody.put("UserAccess", System.getenv().get("GEOCODE_USER_ACCESS"));
            jsonRequestBody.put("UserService", System.getenv().get("GEOCODE_USER_SERVICE"));
        } catch (JSONException e) {
          e.printStackTrace();
        }

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(Geocode.GEOCODE_LOGIN_URL)).
			header("Content-Type", "application/json").
			POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody.toString())).
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
			return json.getString("UserToken") + "@" + json.getString("UserDomain");
		} catch (JSONException e) {
			System.out.println(json.toString());
			e.printStackTrace();
			return null;
		}
    }
    


    public Page<PostalCode> getMunicipalities(String postalCode) {
        List<PostalCode> postalCodes = new ArrayList<PostalCode>();
        String url = Geocode.GEOCODE_MUNICIPALITIES_URL + "/" + postalCode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
            uri(URI.create(url)).
            header("Content-Type", "application/json").
            header("codSesion", getGeocodeToken()).
            GET().
            build();
    
        System.out.println("Solicitando municipios a " + url);
        System.out.println(">   CP " + postalCode);
    
        HttpResponse<String> response;
        try {
          response = client.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println(">    Respuesta " + response.body());
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
          return null;
        }
    
        try {
            JSONObject jsonResponse =  new JSONObject( "{ response: "  + response.body() + "}");
            JSONArray municipalities = jsonResponse.getJSONArray("response");

            for (int i = 0 ; i < municipalities.length(); i++) {
                JSONObject data = municipalities.getJSONObject(i);
                PostalCode receivedPostalCode = new PostalCode();
                receivedPostalCode.setMunicipalityId(data.getString("codMunicipio"));
                receivedPostalCode.setMunicipalityName(data.getString("desPoblacion"));
                receivedPostalCode.setProvinceId(data.getString("codProvincia"));
                postalCodes.add(receivedPostalCode);           
            }
        } catch (JSONException e) {
          e.printStackTrace();
          return null;
        }
    
        return Page.of(postalCodes);
    
      }
}
