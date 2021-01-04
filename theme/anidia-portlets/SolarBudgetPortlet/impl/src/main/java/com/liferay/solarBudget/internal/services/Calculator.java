package com.liferay.solarBudget.internal.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.solarBudget.dto.v1_0.SolarBudget;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetRequest;
//import com.liferay.solarBudget.dto.v1_0.GasBudgetRequest.MetersWaterIntake;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetExtraStringValue;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetExtraYesNoValue;
//import com.liferay.solarBudget.dto.v1_0.GasBudgetRequest.PersonsWater;

import org.json.JSONException;
import org.json.JSONObject;

public class Calculator {
  static String SOLAR_BUDGET_REQUEST_URL = System.getenv().get("SOLAR_BUDGET_REQUEST_URL");
	static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");

	public SolarBudget createSolarBudget(SolarBudgetRequest solarBudgetRequest) {

    JSONObject jsonRequest = new JSONObject();
    SolarBudget responseBudget = new SolarBudget();

    try {
      jsonRequest.put("EntryKey", solarBudgetRequest.getEntryKey());
      jsonRequest.put("HouseType", solarBudgetRequest.getHouseType());
      jsonRequest.put("MonthlyConsumption", solarBudgetRequest.getMonthlyConsumption());
      jsonRequest.put("AnnualConsumption", solarBudgetRequest.getAnnualConsumption());
      jsonRequest.put("AdditionalPanels", solarBudgetRequest.getAdditionalPanels());
      jsonRequest.put("PanelsCanTell", solarBudgetRequest.getPanelsCanTell());
      jsonRequest.put("PanelsNumber", solarBudgetRequest.getPanelsNumber());
      jsonRequest.put("PanelsSelected", solarBudgetRequest.getPanelsSelected());
      jsonRequest.put("InstallationType", solarBudgetRequest.getInstallationType());
      jsonRequest.put("InverterOversized", solarBudgetRequest.getInverterOversized());
      jsonRequest.put("InverterType", solarBudgetRequest.getInverterOversized());
      jsonRequest.put("RoofType", solarBudgetRequest.getRoofType());
      jsonRequest.put("Pergola", solarBudgetRequest.getPergola());
      jsonRequest.put("PipelineUnderground", solarBudgetRequest.getPipelineUnderground());
      jsonRequest.put("PipelineMeters", solarBudgetRequest.getPipelineMeters());
      jsonRequest.put("CarCharger", solarBudgetRequest.getCarCharger());
      jsonRequest.put("NeedBattery", solarBudgetRequest.getNeedBattery());
      jsonRequest.put("HasAdditionalPanels", solarBudgetRequest.getHasAdditionalPanels());
      jsonRequest.put("NumberAdditionalPanels", solarBudgetRequest.getNumberAdditionalPanels());

      jsonRequest.put("ElectricalAppliances", new JSONObject());
      jsonRequest.getJSONObject("ElectricalAppliances").put("ElectricalAppliance1", solarBudgetRequest.getElectricalAppliances().getElectricalAppliance1());
      jsonRequest.getJSONObject("ElectricalAppliances").put("ElectricalAppliance2",solarBudgetRequest.getElectricalAppliances().getElectricalAppliance2());
      jsonRequest.getJSONObject("ElectricalAppliances").put("ElectricalAppliance3", solarBudgetRequest.getElectricalAppliances(). getElectricalAppliance3());


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
      /*
      JSONObject jsonBudget = jsonResponse.getJSONObject("data").getJSONArray("items").getJSONObject(0);

      responseBudget.setProposedPack(jsonBudget.getString("ProposedPack"));
      responseBudget.setEquipment(jsonBudget.getString("Equipment"));
      responseBudget.setBaseBudget(jsonBudget.getString("BaseBadget"));
      responseBudget.setBonus(jsonBudget.getString("Bonus"));
      responseBudget.setTotalBudget(jsonBudget.getString("TotalBudget"));
      responseBudget.setVat(jsonBudget.getString("Iva21"));
      responseBudget.setTotalPrice(jsonBudget.getString("TotalPVP"));

      JSONObject jsonExtras = jsonBudget.getJSONObject("Extras");

      responseBudget.setMetersBoilerToWindow(this.createExtra(jsonExtras.getJSONObject("MetersBoilerToWindow")));
      responseBudget.setMetersWaterIntake(this.createExtra(jsonExtras.getJSONObject("MetersWaterIntake")));
      responseBudget.setHasVentilationGrill(this.createExtra(jsonExtras.getJSONObject("HasVentilationGrill")));
      responseBudget.setControllHeatingFloor(this.createExtra(jsonExtras.getJSONObject("ControllHeatingFloor")));
      responseBudget.setConnectDeviceToKitchen(this.createExtra(jsonExtras.getJSONObject("ConnectDeviceToKitchen")));
      responseBudget.setConvertDeviceKitchen(this.createExtra(jsonExtras.getJSONObject("ConvertDeviceKitchen")));
      responseBudget.setRadiatorsBathroom(this.createExtra(jsonExtras.getJSONObject("RadiatorsBathroom")));

      responseBudget.setExtraTotalPrice(jsonExtras.getString("ExtraTotalPrice"));
      */
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

    return responseBudget;

  }
/*
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
  }*/

}
