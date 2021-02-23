package com.liferay.solarBudget.internal.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.solarBudget.dto.v1_0.Installer;

import com.liferay.portal.kernel.log.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Availability {
  static String SOLAR_AVAILABILITY_REQUEST_URL = System.getenv().get("SOLAR_AVAILABILITY_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");
	private Log log = LogFactoryUtil.getLog(Availability.class.getName());

	public Installer checkAvailability(String postalCode, String municipalityId) {
    Installer installer = new Installer();
    String url =  Availability.SOLAR_AVAILABILITY_REQUEST_URL + "zipCode=" + postalCode +"&ineCode=" + municipalityId;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
      uri(URI.create(url)).
			header("x-auth-token", Availability.SOLUSOFT_SECRET).
      header("Content-Type", "application/json").
			GET().
      build();

    log.info("Consultando disponibilidad llamando a " + url);


    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      log.info(">    Respuesta " + response.body());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return null;
    }

    try {
      JSONObject jsonResponse =  new JSONObject(response.body());
      /*
      JSONObject jsonInstaller = jsonResponse.getJSONObject("data").getJSONArray("items").getJSONObject(0).getJSONArray("Installers").getJSONObject(0);
      installer.setInstallerCode(jsonInstaller.getString("InstallerCode"));
      installer.setInstallerName(jsonInstaller.getString("InstallerName"));*/
      installer.setInstallerCode("162");
      installer.setInstallerName("InstallerName");

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

    return installer;

  }

}
