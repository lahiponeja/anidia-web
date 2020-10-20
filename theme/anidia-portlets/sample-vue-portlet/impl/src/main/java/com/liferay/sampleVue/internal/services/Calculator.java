package com.liferay.sampleVue.internal.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import com.liferay.sampleVue.dto.v1_0.GasBudget;
import com.liferay.sampleVue.dto.v1_0.GasBudgetRequest;
import com.liferay.sampleVue.dto.v1_0.GasBudgetRequest.PersonsWater;

public class Calculator {
  static String GAS_BUDGET_REQUEST_URL = System.getenv().get("GAS_BUDGET_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");

	public GasBudget createGasBudget(GasBudgetRequest gasBudgetRequest) {

    JSONObject jsonRequest = new JSONObject();

    jsonRequest.put("ZipCode", gasBudgetRequest.getPostalCode());
    jsonRequest.put("HouseType", gasBudgetRequest.getHouseType());
    jsonRequest.put("PropertyMeters", gasBudgetRequest.getPropertyMeters());
    jsonRequest.put("StaysNumber", gasBudgetRequest.getStaysNumber());
    jsonRequest.put("BathroomNumber", gasBudgetRequest.getBathroomNumber());
    jsonRequest.put("FloorNumber", gasBudgetRequest.getFloorNumber());
    jsonRequest.put("GasNaturalUse", gasBudgetRequest.getGasNaturalUse() );
    jsonRequest.put("ACSUse", gasBudgetRequest.getAcsUse());
    jsonRequest.put("KitchenUse", gasBudgetRequest.getKitchenUse());
    jsonRequest.put("HeatingUse", gasBudgetRequest.getHeatingUse());
    jsonRequest.put("PersonsWater", this.translatePersonsWater(gasBudgetRequest.getPersonsWater()));
    jsonRequest.put("BoilerLocation", gasBudgetRequest.getBoilerLocation());
    jsonRequest.put("Extras", new JSONObject());

    jsonRequest.getJSONObject("PersonalData").put("MetersBoilerToWindow", gasBudgetRequest.getMetersBoilerToWindow());
    jsonRequest.getJSONObject("PersonalData").put("MetersWaterIntake", this.translateMetersWaterIntake(gasBudgetRequest.getMetersWaterIntake()));
    jsonRequest.getJSONObject("PersonalData").put("HasVentilationGrill", gasBudgetRequest.getHasVentilationGrill() ? "Si" : "No");
    jsonRequest.getJSONObject("PersonalData").put("ControllHeatingFloor", gasBudgetRequest.getControllHeatingFloor() ? "Si" : "No");
    jsonRequest.getJSONObject("PersonalData").put("ConnectDeviceToKitchen", gasBudgetRequest.getConnectDeviceToKitchen() ? "Si" : "No");
    jsonRequest.getJSONObject("PersonalData").put("ConvertDeviceKitchen", gasBudgetRequest.getConvertDeviceKitchen() ? "Si" : "No");
    jsonRequest.getJSONObject("PersonalData").put("RadiatorsBathroom", gasBudgetRequest.getRadiatorsBathroom());

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(Calculator.GAS_BUDGET_REQUEST_URL)).
			header("x-auth-token", Calculator.SOLUSOFT_SECRET).
			header("Content-Type", "application/json").
			POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString())).
      build();

    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }



  }

  private String translatePersonsWater(PersonsWater original) {
    switch (original.getValue()) {
      case "Hasta 2":
        return "Hasta 2 personas";
      case "Entre 3-4":
        return "De 3-4 personas";
      case "Más de 4":
        return "5 o más personas";
    }
    return null;
  }

  private String translateMetersWaterIntake(MetersWaterIntake original) {
    switch (original.getValue()) {
      case "m.0":
        return "0";
      case "m.1":
        return "1";
      case "m.2":
        return "2";
      case "m.3":
        return "3";
      case "m.4":
        return "4";
    }
    return null;
  }

}
