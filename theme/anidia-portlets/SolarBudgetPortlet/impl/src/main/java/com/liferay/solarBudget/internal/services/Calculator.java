package com.liferay.solarBudget.internal.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.solarBudget.dto.v1_0.SolarBudget;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetRequest;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetSize;
import com.liferay.solarBudget.dto.v1_0.SolarOutputInverter;
import com.liferay.solarBudget.dto.v1_0.SuperiorInstallation;
import com.liferay.solarBudget.dto.v1_0.SuperiorSize;

import org.json.JSONException;
import org.json.JSONObject;

public class Calculator {
  static String SOLAR_BUDGET_REQUEST_URL = System.getenv().get("SOLAR_BUDGET_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");

	public SolarBudget createSolarBudget(SolarBudgetRequest solarBudgetRequest) {

    JSONObject jsonRequest = new JSONObject();
    SolarBudget responseBudget = new SolarBudget();
    try {
      jsonRequest.put("EntryKey", "Web");
      jsonRequest.put("HouseType", solarBudgetRequest.getHouseTypeAsString());
      jsonRequest.put("MonthlyConsumption", solarBudgetRequest.getMonthlyConsumption());
      jsonRequest.put("RoofType", solarBudgetRequest.getRoofTypeAsString());
      System.out.println(jsonRequest);
    } catch (JSONException e) {
      e.printStackTrace();
      return responseBudget;
    }

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(Calculator.SOLAR_BUDGET_REQUEST_URL)).
			header("x-auth-token", Calculator.SOLUSOFT_SECRET).
			header("Content-Type", "application/json").
			POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString())).
      build();

    System.out.println("Solicitando presupuesto a " + Calculator.SOLAR_BUDGET_REQUEST_URL);
    System.out.println(">    Detalle de presupuesto " + jsonRequest.toString());

    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(">    Respuesta " + response.body());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return null;
    }

    try {
      JSONObject jsonResponse =  new JSONObject(response.body());
 
      JSONObject jsonBudget = jsonResponse.getJSONObject("data").getJSONArray("items").getJSONObject(0);
      responseBudget.setPanelsType(jsonBudget.getString("PanelsType"));
      responseBudget.setTotalPrice(jsonBudget.get("TotalPrice").toString());
      responseBudget.setSize(this.createSize(jsonBudget.getJSONObject("Size")));
      responseBudget.setInverter(this.createInverter(jsonBudget.getJSONObject("Inverter")));
      responseBudget.setPanelsExtra(jsonBudget.getJSONObject("PanelsExtra").getString("UnitPrice"));
      responseBudget.setTriphasicExtra(jsonBudget.getJSONObject("TriphasicExtra").getString("UnitPrice"));
      responseBudget.setInverterExtra(jsonBudget.getJSONObject("InverterExtra").getString("UnitPrice"));
      responseBudget.setRoofExtra(jsonBudget.getJSONObject("RoofExtra").getString("UnitPrice"));
      responseBudget.setPergolaExtra(jsonBudget.getJSONObject("PergolaExtra").getString("UnitPrice"));
      responseBudget.setPipelineExtra(jsonBudget.getJSONObject("PipelineExtra").getString("UnitPrice"));
      responseBudget.setCarCharger(jsonBudget.getJSONObject("CarCharcher").getString("UnitPrice"));
      responseBudget.setBattery(jsonBudget.getJSONObject("Battery").getString("UnitPrice"));
      responseBudget.setAdditionalPanelsInstallation(jsonBudget.getJSONObject("AdditionalPanelsInstallation").getString("UnitPrice"));

      JSONObject jsonSuperiorInstallation = jsonBudget.getJSONObject("SuperiorInstallation");
      responseBudget.setSuperiorInstallation(this.createSuperiorInstallation(jsonSuperiorInstallation));

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

    return responseBudget;

  }
  private SuperiorInstallation createSuperiorInstallation(JSONObject jsonSuperiorInstallation) throws JSONException {
    SuperiorInstallation superiorInstallation = new SuperiorInstallation();
    
    superiorInstallation.setSuperiorSize(this.createSuperiorSize(jsonSuperiorInstallation.getJSONObject("SuperiorSize")));
    superiorInstallation.setPanelsType(jsonSuperiorInstallation.getString("PanelType"));
    superiorInstallation.setInverterType(jsonSuperiorInstallation.getString("InverterType"));
    superiorInstallation.setExtraFornius(jsonSuperiorInstallation.getString("ExtraFornius"));
    superiorInstallation.setPanelsExtra(jsonSuperiorInstallation.getString("PanelsExtra"));
    superiorInstallation.setTriphasicExtra(jsonSuperiorInstallation.getString("TriphasicExtra"));
    superiorInstallation.setInverterExtra(jsonSuperiorInstallation.getString("InverterExtra"));
    superiorInstallation.setRoofExtra(jsonSuperiorInstallation.getString("RoofExtra"));
    superiorInstallation.setPergolaExtra(jsonSuperiorInstallation.getString("PergolaExtra"));
    superiorInstallation.setPipelineExtra(jsonSuperiorInstallation.getString("PipelineExtra"));
    superiorInstallation.setCarCharger(jsonSuperiorInstallation.getString("CarCharcher"));
    superiorInstallation.setBattery(jsonSuperiorInstallation.getString("Battery"));
    superiorInstallation.setAdditionalPanelsInstallation(jsonSuperiorInstallation.getString("AdditionalPanelsInstallation"));
    return superiorInstallation;
  }
  private SuperiorSize createSuperiorSize(JSONObject jsonSuperiorSize) throws JSONException {
    SuperiorSize superiorSize = new SuperiorSize();
    superiorSize.setValue(jsonSuperiorSize.get("Value") != null ? jsonSuperiorSize.get("Value").toString() : "" );
    superiorSize.setPrice(jsonSuperiorSize.get("Price") != null ? jsonSuperiorSize.get("Price").toString() : "" );
    superiorSize.setBasePanels(jsonSuperiorSize.get("BasePanels") != null ? jsonSuperiorSize.get("BasePanels").toString() : "" );
    return superiorSize;
  }

  private SolarBudgetSize createSize(JSONObject jsonSize) throws JSONException {
    SolarBudgetSize size = new SolarBudgetSize();
    size.setValue(jsonSize.get("Value") != null ? jsonSize.get("Value").toString() : "" );
    size.setUnitPrice(jsonSize.get("UnitPrice") != null ? jsonSize.get("UnitPrice").toString() : "" );
    size.setPrice(jsonSize.get("Price") != null ? jsonSize.get("Price").toString() : "" );
    size.setBasePanels(jsonSize.get("BasePanels") != null ? jsonSize.get("BasePanels").toString() : "" );
    size.setTotalPanels(jsonSize.get("TotalPanels") != null ? jsonSize.get("TotalPanels").toString() : "" );
    return size;
  }
  private SolarOutputInverter createInverter(JSONObject jsonExtra) throws JSONException {
    SolarOutputInverter inverter = new SolarOutputInverter();
    inverter.setBrand(jsonExtra.get("Brand") != null ? jsonExtra.get("Brand").toString() : "" );
    inverter.setModel(jsonExtra.get("Model") != null ? jsonExtra.get("Model").toString() : "" );
    inverter.setPrice(jsonExtra.get("Price") != null ? jsonExtra.get("Price").toString() : "" );
    return inverter;
  }
}
