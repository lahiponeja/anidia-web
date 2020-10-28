package com.liferay.gasComparator.internal.services;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.liferay.gasComparator.dto.v1_0.*;
import com.liferay.gasComparator.internal.dto.*;
import com.liferay.gasComparator.internal.exception.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
import org.json.*;


public class GasComparator {

    static String GAS_COMPARATOR_CONSUMPTION_REQUEST_URL = System.getenv()
        .get("GAS_COMPARATOR_CONSUMPTION_REQUEST_URL");
    static String GAS_COMPARATOR_USE_REQUEST_URL = System.getenv()
        .get("GAS_COMPARATOR_USE_REQUEST_URL");
    static String SOLUSOFT_SECRET = System.getenv().get("SOLUSOFT_SECRET");

    public GasConsumptionComparison compareByDirectConsumption(
        GasCalculatedConsumption gasCalculatedConsumption) throws PortletException {

        GasConsumptionComparison gasConsumptionComparison = new GasConsumptionComparison();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(mapGasComparationRequest(
                gasCalculatedConsumption));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(GAS_COMPARATOR_CONSUMPTION_REQUEST_URL)).
                header("x-auth-token", SOLUSOFT_SECRET).
                header("Content-Type", "application/json").
                POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).
                build();

            System.out.println("Solicitando comaprativa a " + GAS_COMPARATOR_CONSUMPTION_REQUEST_URL);
            System.out.println(">    Detalle de comparativa " + jsonRequest);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(">    Respuesta " + response.body());

            gasConsumptionComparison = mapJsonResponseToComparison(response);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new PortletException(1, "Error creating request");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new PortletException(2, "Error sending request");
        }

        return gasConsumptionComparison;
    }

    private GasComparationRequest mapGasComparationRequest(
        GasCalculatedConsumption gasCalculatedConsumption) {

        GasComparationRequest gasComparationRequest = new GasComparationRequest();
        gasComparationRequest.setAcsUse(gasCalculatedConsumption.getAcsUse() ? "Si" : "No");
        switch (gasCalculatedConsumption.getEnergyType().getValue()) {
            case "glp":
                gasComparationRequest.setAnualConsumptionGLP(
                    gasCalculatedConsumption.getElectricityConsumption());
                gasComparationRequest.setAnualConsumptionButane(0);
                gasComparationRequest.setAnualConsumptionGOC(0);
                gasComparationRequest.setAnualConsuptionElectricity(0);
                break;
            case "goc":
                gasComparationRequest.setAnualConsumptionGLP(0);
                gasComparationRequest.setAnualConsumptionButane(0);
                gasComparationRequest.setAnualConsumptionGOC(
                    gasCalculatedConsumption.getElectricityConsumption());
                gasComparationRequest.setAnualConsuptionElectricity(0);
                break;
            case "electricity":
                gasComparationRequest.setAnualConsumptionGLP(0);
                gasComparationRequest.setAnualConsumptionButane(0);
                gasComparationRequest.setAnualConsumptionGOC(0);
                gasComparationRequest.setAnualConsuptionElectricity(
                    gasCalculatedConsumption.getElectricityConsumption());
                break;
            case "butane":
                gasComparationRequest.setAnualConsumptionGLP(0);
                gasComparationRequest.setAnualConsumptionButane(
                    gasCalculatedConsumption.getElectricityConsumption());
                gasComparationRequest.setAnualConsumptionGOC(0);
                gasComparationRequest.setAnualConsuptionElectricity(0);
                break;
        }
        gasComparationRequest.setHeatingUse(gasCalculatedConsumption.getHeatingUse() ? "Si" : "No");
        gasComparationRequest.setKitchenUse(gasCalculatedConsumption.getKitchenUse() ? "Si" : "No");

        return gasComparationRequest;
    }

    public GasConsumptionComparison compareByUse(GasConsumptionByUse gasConsumptionByUse)
        throws PortletException {

        JSONObject jsonRequest = new JSONObject();

        try {

            jsonRequest.put("Province", gasConsumptionByUse.getProvince());
            jsonRequest.put("ACSIndividual", gasConsumptionByUse.getAcsIndividual() ? "Si" : "No");
            jsonRequest.put("ACSUse", gasConsumptionByUse.getAcsUse());
            jsonRequest.put("NumberOfPeople", gasConsumptionByUse.getNumberOfPeople());
            jsonRequest
                .put("HeatingIndividual", gasConsumptionByUse.getHeatingIndividual() ? "Si" : "No");
            jsonRequest.put("HeatingUse", gasConsumptionByUse.getHeatingUse());
            jsonRequest
                .put("SingleFamilyHouse", gasConsumptionByUse.getSingleFamilyHouse() ? "Si" : "No");
            jsonRequest.put("LastFloor", gasConsumptionByUse.getLastFloor() ? "Si" : "No");
            jsonRequest.put("SurfaceHouse", gasConsumptionByUse.getSurfaceHouse());
            jsonRequest.put("KitchenUse", gasConsumptionByUse.getKitchenUse());
            jsonRequest.put("WeeklyKitchenUse", gasConsumptionByUse.getWeeklyKitchenUse());

        } catch (JSONException e) {
            e.printStackTrace();
            throw new PortletException(1, "Error creating request");
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
            uri(URI.create(GAS_COMPARATOR_USE_REQUEST_URL)).
            header("x-auth-token", SOLUSOFT_SECRET).
            header("Content-Type", "application/json").
            POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString())).
            build();

        System.out.println("Solicitando comaprativa a " + GAS_COMPARATOR_USE_REQUEST_URL);
        System.out.println(">    Detalle de comparativa " + jsonRequest.toString());

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(">    Respuesta " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new PortletException(2, "Error sending request");
        }

        return this.mapJsonResponseToComparison(response);

    }

    private GasConsumptionComparison mapJsonResponseToComparison(HttpResponse<String> response)
        throws PortletException {

        try {

            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject jsonBudget = jsonResponse.getJSONObject("data").getJSONArray("items")
                .getJSONObject(0);

            if (jsonBudget.getString("CurrentEnergyCost").equals("#VALUE!")) {
                throw new PortletException(4, "No hay servicio");
            }

            GasConsumptionComparison gasConsumptionComparison = new GasConsumptionComparison();

            gasConsumptionComparison
                .setConsumptionRequired(jsonBudget.getString("HouseConsumptionRequired"));
            gasConsumptionComparison.setCurrentCost(jsonBudget.getString("CurrentEnergyCost"));
            gasConsumptionComparison.setFutureCost(jsonBudget.getString("GNCost"));
            gasConsumptionComparison.setSavings(jsonBudget.getString("GNSaving"));

            return gasConsumptionComparison;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PortletException(3, "Error in the response");
        }

    }

}
