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

import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.Log;

public class Calculator {
  static String SOLAR_BUDGET_REQUEST_URL = System.getenv().get("SOLAR_BUDGET_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");
  private static Log _log = LogFactoryUtil.getLog(Calculator.class);

	public SolarBudget createSolarBudget(SolarBudgetRequest solarBudgetRequest) {

    JSONObject jsonRequest = new JSONObject();
    SolarBudget responseBudget = new SolarBudget();
    try {
      jsonRequest.put("EntryKey", "Web");
      jsonRequest.put("HouseType", solarBudgetRequest.getHouseTypeAsString());
      jsonRequest.put("PanelsSelected", (solarBudgetRequest.getPanelsType() == null) ? "Standard" : solarBudgetRequest.getPanelsTypeAsString());
      jsonRequest.put("MonthlyConsumption", solarBudgetRequest.getMonthlyConsumption());
      jsonRequest.put("AnnualConsumption", solarBudgetRequest.getAnnualConsumption());
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

    _log.info("Solicitando presupuesto a " + Calculator.SOLAR_BUDGET_REQUEST_URL);
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
      responseBudget.setPanelsType(jsonBudget.getString("PanelsType"));
      responseBudget.setTotalPrice(this.sanitizePrice(jsonBudget.get("TotalPrice").toString()));
      responseBudget.setSize(this.createSize(jsonBudget.getJSONObject("Size")));
      responseBudget.setInverter(this.createInverter(jsonBudget.getJSONObject("Inverter")));
      responseBudget.setPanelsExtra(this.sanitizePrice(jsonBudget.getJSONObject("PanelsExtra").getString("UnitTaxPrice")));
      responseBudget.setTriphasicExtra(this.sanitizePrice(jsonBudget.getJSONObject("TriphasicExtra").getString("UnitTaxPrice")));
      responseBudget.setInverterExtra(this.sanitizePrice(jsonBudget.getJSONObject("InverterExtra").getString("UnitTaxPrice")));
      responseBudget.setSuperiorInverterExtra(this.sanitizePrice(jsonBudget.getJSONObject("Inverter").getString("PriceExtra")));
      responseBudget.setRoofExtra(this.sanitizePrice(jsonBudget.getJSONObject("RoofExtra").getString("UnitTaxPrice")));
      responseBudget.setPergolaExtra(this.sanitizePrice(jsonBudget.getJSONObject("PergolaExtra").getString("UnitTaxPrice")));
      responseBudget.setPipelineExtra(this.sanitizePrice(jsonBudget.getJSONObject("PipelineExtra").getString("UnitTaxPrice")));
      responseBudget.setCarCharger(this.sanitizePrice(jsonBudget.getJSONObject("CarCharcher").getString("UnitTaxPrice")));
      responseBudget.setBattery(this.sanitizePrice(jsonBudget.getJSONObject("Battery").getString("UnitTaxPrice")));
      responseBudget.setAdditionalPanelsInstallation(this.sanitizePrice(jsonBudget.getJSONObject("AdditionalPanelsInstallation").getString("UnitTaxPrice")));
      responseBudget.setTotalPowerInstalled(this.sanitizePrice(jsonBudget.getString("TotalPowerInstalled")));

      JSONObject jsonSuperiorInstallation = jsonBudget.getJSONObject("SuperiorInstallation");
      responseBudget.setSuperiorInstallation(this.createSuperiorInstallation(jsonSuperiorInstallation));

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

    return responseBudget;

  }

  private String sanitizePrice(String price) {
    return price.replaceAll("€", "").replace(".", "").replace(",", ".");
  }

  private SuperiorInstallation createSuperiorInstallation(JSONObject jsonSuperiorInstallation) throws JSONException {
    SuperiorInstallation superiorInstallation = new SuperiorInstallation();

    superiorInstallation.setSuperiorSize(this.createSuperiorSize(jsonSuperiorInstallation.getJSONObject("SuperiorSize")));
    superiorInstallation.setPanelsType(jsonSuperiorInstallation.getString("PanelType"));
    superiorInstallation.setInverterType(jsonSuperiorInstallation.getString("InverterType"));
    superiorInstallation.setExtraFornius(jsonSuperiorInstallation.getString("ExtraFornius"));
    superiorInstallation.setPanelsExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("PanelsExtra").getString("UnitTaxPrice")));
    superiorInstallation.setTriphasicExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("TriphasicExtra").getString("UnitTaxPrice")));
    superiorInstallation.setInverterExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("InverterExtra").getString("UnitTaxPrice")));
    superiorInstallation.setSuperiorInverterExtra(this.sanitizePrice(jsonSuperiorInstallation.getString("ExtraFornius")));
    superiorInstallation.setRoofExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("RoofExtra").getString("UnitTaxPrice")));
    superiorInstallation.setPergolaExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("PergolaExtra").getString("UnitTaxPrice")));
    superiorInstallation.setPipelineExtra(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("PipelineExtra").getString("UnitTaxPrice")));
    superiorInstallation.setCarCharger(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("CarCharcher").getString("UnitTaxPrice")));
    superiorInstallation.setBattery(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("Battery").getString("UnitTaxPrice")));
    superiorInstallation.setAdditionalPanelsInstallation(this.sanitizePrice(jsonSuperiorInstallation.getJSONObject("AdditionalPanelsInstallation").getString("UnitTaxPrice")));
    superiorInstallation.setTotalPowerInstalled(this.sanitizePrice(jsonSuperiorInstallation.getString("TotalPowerInstalled")));

    return superiorInstallation;
  }
  private SuperiorSize createSuperiorSize(JSONObject jsonSuperiorSize) throws JSONException {
    SuperiorSize superiorSize = new SuperiorSize();
    superiorSize.setValue(jsonSuperiorSize.get("Value") != null ? this.sanitizePrice(jsonSuperiorSize.get("Value").toString()) : "" );
    superiorSize.setPrice(jsonSuperiorSize.get("Price") != null ? this.sanitizePrice(jsonSuperiorSize.get("Price").toString()) : "" );
    superiorSize.setBasePanels(jsonSuperiorSize.get("BasePanels") != null ? jsonSuperiorSize.get("BasePanels").toString() : "" );
    return superiorSize;
  }

  private SolarBudgetSize createSize(JSONObject jsonSize) throws JSONException {
    SolarBudgetSize size = new SolarBudgetSize();
    size.setValue(jsonSize.get("Value") != null ? jsonSize.get("Value").toString() : "" );
    size.setUnitPrice(jsonSize.get("UnitPrice") != null ? this.sanitizePrice(jsonSize.get("UnitPrice").toString()) : "" );
    size.setPrice(jsonSize.get("Price") != null ? this.sanitizePrice(jsonSize.get("Price").toString()) : "" );
    size.setBasePanels(jsonSize.get("BasePanels") != null ? jsonSize.get("BasePanels").toString() : "" );
    size.setTotalPanels(jsonSize.get("TotalPanels") != null ? jsonSize.get("TotalPanels").toString() : "" );
    return size;
  }
  private SolarOutputInverter createInverter(JSONObject jsonExtra) throws JSONException {
    SolarOutputInverter inverter = new SolarOutputInverter();
    inverter.setBrand(jsonExtra.get("Brand") != null ? jsonExtra.get("Brand").toString() : "" );
    inverter.setModel(jsonExtra.get("Model") != null ? jsonExtra.get("Model").toString() : "" );
    inverter.setPrice(jsonExtra.get("Price") != null ? this.sanitizePrice(jsonExtra.get("Price").toString()) : "" );
    return inverter;
  }
}
