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

import org.json.*;
import com.liferay.solarBudget.dto.v1_0.PostalCode;
import com.liferay.solarBudget.dto.v1_0.Property;
public class Geocode{
    static String GEOCODE_LOGIN_URL = System.getenv().get("GEOCODE_LOGIN_URL");
    static String GEOCODE_MUNICIPALITIES_URL = System.getenv().get("GEOCODE_MUNICIPALITIES_URL");
    static String GEOCODE_PROPERTIES_URL = System.getenv().get("GEOCODE_PROPERTIES_URL");

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
    


    public List<PostalCode> getMunicipalities(String postalCode) {
        List<PostalCode> postalCodes = new ArrayList<PostalCode>();
        String url = Geocode.GEOCODE_MUNICIPALITIES_URL + "/" + postalCode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
            uri(URI.create(url)).
            header("Content-Type", "application/json").
            header("codSesion", getGeocodeToken()).
            GET().
            build();
    
        HttpResponse<String> response = null;
        JSONArray responseJson;
        System.out.println("Requesting municipalities to " +url);
        try {
          response = client.send(request, HttpResponse.BodyHandlers.ofString());
          responseJson = new JSONArray(response.body());
         } catch (IOException | InterruptedException e) {
          e.printStackTrace();
          return postalCodes;
        } catch (JSONException e) {
          if(response != null) {
            System.out.println("Geocode response: " + response.body());
          }
          e.printStackTrace();
          return postalCodes;
        }
    

        for (int i = 0 ; i < responseJson.length(); i++) {
          JSONObject postalcodeJson = null;
          try {
            postalcodeJson = responseJson.getJSONObject(i);
            PostalCode postalCodeData = new PostalCode();
            postalCodeData.setMunicipalityId(postalcodeJson.getString("codMunicipio"));
            postalCodeData.setMunicipalityName(postalcodeJson.getString("desPoblacion"));
            postalCodeData.setProvinceId(postalcodeJson.getString("codProvincia"));
            postalCodeData.setPopulationId(postalcodeJson.getString("codPoblacion"));
            postalCodes.add(postalCodeData);  
          } catch (JSONException e) {
            System.out.println("Geocode response: " + response.body());
            if(postalcodeJson != null) {
              System.out.println("Json Object with error: " + postalcodeJson.toString());
            }
            e.printStackTrace();
          }         
        }
        return postalCodes;  
      }

    
    public List<Property> getProperties( String postalCode, String municipalityId,
    String streetId, String portalNumber) {
      List<Property> properties = new ArrayList<Property>();


      StringBuilder urlBuilder = new StringBuilder();
      urlBuilder.append(Geocode.GEOCODE_PROPERTIES_URL);
      urlBuilder.append("?");
      urlBuilder.append("&codProvincia=");
      urlBuilder.append(postalCode.substring(0, 2));
      urlBuilder.append("&codMunicipio=");
      urlBuilder.append(municipalityId);
      urlBuilder.append("&codVia=");
      urlBuilder.append(streetId);
      urlBuilder.append("&numPortal=");
      urlBuilder.append(portalNumber);
      urlBuilder.append("&sistemaCoordenada=ETRS89");

      String url = Geocode.GEOCODE_MUNICIPALITIES_URL + "/" + postalCode;
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().
          uri(URI.create(urlBuilder.toString())).
          header("Content-Type", "application/json").
          header("codSesion", getGeocodeToken()).
          GET().
          build();
  
      System.out.println("Requesting properties to " + url);


      HttpResponse<String> response = null;
      JSONArray responseJson;

      try {
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        responseJson = new JSONArray(response.body());
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        return properties;
      } catch (JSONException e) {
        if(response != null) {
          System.out.println("Geocode response: " + response.body());
        }
        e.printStackTrace();
        return properties;
      }
          
          for (int i = 0 ; i < responseJson.length(); i++) {
              JSONObject propertyJson = null;
              try {
                propertyJson = responseJson.getJSONObject(i);
                Property property = new Property();
                property.setPropertyId(propertyJson.optString("referenciaCatastral"));
                property.setBlock(propertyJson.optString("bloque"));
                property.setLadder(propertyJson.optString("escalera"));
                property.setFloor(propertyJson.optString("piso"));
                property.setDoor(propertyJson.optString("puerta"));
                //property.setContractStatus("POR DEFINIR");
        
                StringBuilder completeAddress = new StringBuilder();
                if(property.getBlock() != null && !property.getBlock().equals("")) {
                  completeAddress.append("Bloque ");
                  completeAddress.append(property.getBlock());
                  completeAddress.append(" ");
                }
                if(property.getLadder() != null && !property.getLadder().equals("")) {
                  completeAddress.append("Escalera ");
                  completeAddress.append(property.getLadder());
                  completeAddress.append(" ");
                }
                if(property.getFloor() != null && !property.getFloor().equals("")) {
                  completeAddress.append("Piso ");
                  completeAddress.append(property.getFloor());
                  completeAddress.append(" ");
                }
                if(property.getDoor() != null && !property.getDoor().equals("")) {
                  completeAddress.append("Puerta ");
                  completeAddress.append(property.getDoor());
                }
        
                if(completeAddress.toString().equals("")) {
                  property.setAddress(propertyJson.optString("Direccion_completa__c"));
                } else {
                  property.setAddress(completeAddress.toString());
                }   
                
                properties.add(property);
              } catch (JSONException e) {
                System.out.println("Geocode response: " + response.body());
                if(propertyJson != null) {
                  System.out.println("Json Object with error: " + propertyJson.toString());
                }
                e.printStackTrace();
            }
          }  
    return properties;
  }
}
