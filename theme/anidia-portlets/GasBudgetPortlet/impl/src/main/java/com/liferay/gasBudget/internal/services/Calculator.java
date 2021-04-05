package com.liferay.gasBudget.internal.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import com.liferay.gasBudget.dto.v1_0.*;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.gasBudget.dto.v1_0.GasBudgetRequest.MetersWaterIntake;
import com.liferay.gasBudget.dto.v1_0.GasBudgetRequest.PersonsWater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.Log;

public class Calculator {
  static String GAS_BUDGET_REQUEST_URL = System.getenv().get("GAS_BUDGET_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");
  private static Log _log = LogFactoryUtil.getLog(Calculator.class);

	public GasBudgetResult createGasBudget(GasBudgetRequest gasBudgetRequest) {


    JSONObject jsonRequest = new JSONObject();
    GasBudgetResult responseBudget = new GasBudgetResult();

    try {
      jsonRequest.put("ZipCode", gasBudgetRequest.getPostalCode());
      jsonRequest.put("HouseType", gasBudgetRequest.getHouseTypeAsString());
      jsonRequest.put("PropertyMeters", gasBudgetRequest.getPropertyMetersAsString());
      jsonRequest.put("StaysNumber", gasBudgetRequest.getStaysNumber());
      jsonRequest.put("BathroomNumber", gasBudgetRequest.getBathroomNumber().toString());
      jsonRequest.put("FloorNumber", gasBudgetRequest.getFloorNumber().toString());
      jsonRequest.put("GasNaturalUse", gasBudgetRequest.getGasNaturalUseAsString() );
      jsonRequest.put("ACSUse", gasBudgetRequest.getAcsUseAsString());
      jsonRequest.put("KitchenUse", gasBudgetRequest.getKitchenUseAsString());
      jsonRequest.put("HeatingUse", gasBudgetRequest.getHeatingUseAsString());
      jsonRequest.put("PersonsWater", this.translatePersonsWater(gasBudgetRequest.getPersonsWater()));
      jsonRequest.put("BoilerLocation", gasBudgetRequest.getBoilerLocationAsString());
      jsonRequest.put("Extras", new JSONObject());

      jsonRequest.getJSONObject("Extras").put("MetersBoilerToWindow", gasBudgetRequest.getMetersBoilerToWindow());
      jsonRequest.getJSONObject("Extras").put("MetersWaterIntake", this.translateMetersWaterIntake(gasBudgetRequest.getMetersWaterIntake()));
      jsonRequest.getJSONObject("Extras").put("HasVentilationGrill", gasBudgetRequest.getHasVentilationGrill() ? "Si" : "No");
      jsonRequest.getJSONObject("Extras").put("ControllHeatingFloor", gasBudgetRequest.getControllHeatingFloor() ? "Si" : "No");
      jsonRequest.getJSONObject("Extras").put("ConnectDeviceToKitchen", gasBudgetRequest.getConnectDeviceToKitchen() ? "Si" : "No");
      jsonRequest.getJSONObject("Extras").put("ConvertDeviceKitchen", gasBudgetRequest.getConvertDeviceKitchen() ? "Si" : "No");
      jsonRequest.getJSONObject("Extras").put("RadiatorsBathroom", gasBudgetRequest.getRadiatorsBathroom());

    } catch (JSONException e) {
      e.printStackTrace();
      return responseBudget;
    }

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(Calculator.GAS_BUDGET_REQUEST_URL)).
			header("x-auth-token", Calculator.SOLUSOFT_SECRET).
			header("Content-Type", "application/json").
			POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString())).
      build();

    _log.info("Solicitando presupuesto a " + Calculator.GAS_BUDGET_REQUEST_URL);
    _log.info(">    Detalle de presupuesto " + jsonRequest.toString());

    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      _log.info(">    Respuesta " + response.body());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return null;
    }

    try {
      JSONObject jsonResponse =  new JSONObject(response.body());
      JSONObject jsonBudget = jsonResponse.getJSONObject("data").getJSONArray("items").getJSONObject(0);

      responseBudget.setPrincipalBudget(new GasBudget());
      responseBudget.getPrincipalBudget().setProposedPack(jsonBudget.getString("ProposedPack"));
      responseBudget.getPrincipalBudget().setEquipment(jsonBudget.getString("Equipment"));
      responseBudget.getPrincipalBudget().setBaseBudget(jsonBudget.getString("BaseBadget"));
      responseBudget.getPrincipalBudget().setBonus(jsonBudget.getString("Bonus"));
      responseBudget.getPrincipalBudget().setTotalBudget(jsonBudget.getString("TotalBudget"));
      responseBudget.getPrincipalBudget().setVat(jsonBudget.getString("Iva21"));
      responseBudget.getPrincipalBudget().setTotalPrice(jsonBudget.getString("TotalPVP"));

      JSONObject jsonExtras = jsonBudget.getJSONObject("Extras");

      responseBudget.getPrincipalBudget().setMetersBoilerToWindow(this.createExtra(jsonExtras.getJSONObject("MetersBoilerToWindow")));
      responseBudget.getPrincipalBudget().setMetersWaterIntake(this.createExtra(jsonExtras.getJSONObject("MetersWaterIntake")));
      responseBudget.getPrincipalBudget().setHasVentilationGrill(this.createExtra(jsonExtras.getJSONObject("HasVentilationGrill")));
      responseBudget.getPrincipalBudget().setControllHeatingFloor(this.createExtra(jsonExtras.getJSONObject("ControllHeatingFloor")));
      responseBudget.getPrincipalBudget().setConnectDeviceToKitchen(this.createExtra(jsonExtras.getJSONObject("ConnectDeviceToKitchen")));
      responseBudget.getPrincipalBudget().setConvertDeviceKitchen(this.createExtra(jsonExtras.getJSONObject("ConvertDeviceKitchen")));
      responseBudget.getPrincipalBudget().setRadiatorsBathroom(this.createExtra(jsonExtras.getJSONObject("RadiatorsBathroom")));
      responseBudget.getPrincipalBudget().setExtraTotalPrice(jsonExtras.getString("ExtraTotalPrice"));


      JSONArray alternatives = jsonBudget.getJSONArray("AlternativePacks");

      GasBudget[] resultAlternativePacks = new GasBudget[alternatives.length()];
      for (int i=0; i < alternatives.length(); i++) {
        GasBudget aux  = new GasBudget();

        aux.setProposedPack(alternatives.getJSONObject(i).getString("ProposedPack"));
        aux.setEquipment(alternatives.getJSONObject(i).getString("Equipment"));
        aux.setBaseBudget(alternatives.getJSONObject(i).getString("BaseBadget"));
        aux.setBonus(alternatives.getJSONObject(i).getString("Bonus"));
        aux.setTotalBudget(alternatives.getJSONObject(i).getString("TotalBudget"));
        aux.setVat(alternatives.getJSONObject(i).getString("Iva21"));
        aux.setTotalPrice(alternatives.getJSONObject(i).getString("TotalPVP"));

        aux.setMetersBoilerToWindow(this.createExtra(jsonExtras.getJSONObject("MetersBoilerToWindow")));
        aux.setMetersWaterIntake(this.createExtra(jsonExtras.getJSONObject("MetersWaterIntake")));
        aux.setHasVentilationGrill(this.createExtra(jsonExtras.getJSONObject("HasVentilationGrill")));
        aux.setControllHeatingFloor(this.createExtra(jsonExtras.getJSONObject("ControllHeatingFloor")));
        aux.setConnectDeviceToKitchen(this.createExtra(jsonExtras.getJSONObject("ConnectDeviceToKitchen")));
        aux.setConvertDeviceKitchen(this.createExtra(jsonExtras.getJSONObject("ConvertDeviceKitchen")));
        aux.setRadiatorsBathroom(this.createExtra(jsonExtras.getJSONObject("RadiatorsBathroom")));
        aux.setExtraTotalPrice(jsonExtras.getString("ExtraTotalPrice"));

        resultAlternativePacks[i] = aux;

      }

      responseBudget.setGasBudgets(resultAlternativePacks);

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

    return responseBudget;

  }

  private GasBudgetExtra createExtra(JSONObject jsonExtra) throws JSONException {
    GasBudgetExtra tempExtra = new GasBudgetExtra();
    tempExtra.setValue(jsonExtra.getString("Value"));
    tempExtra.setPrice(jsonExtra.getString("Price"));
    return tempExtra;
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
