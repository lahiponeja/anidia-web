package com.liferay.solarBudget.internal.services;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.liferay.solarBudget.dto.v1_0.*;
import com.liferay.solarBudget.internal.dto.*;
import com.liferay.solarBudget.internal.exception.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.*;
import java.net.http.*;
import java.util.*;
import org.json.*;
import com.liferay.portal.kernel.log.*;


public class SalesforceService {

	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_LEAD_URL = System.getenv().get("SALESFORCE_SOLAR_LEAD_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

  private Log _log = LogFactoryUtil.getLog(SalesforceService.class.getName());

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
				_log.info(response.body());
			}
			e.printStackTrace();
			return null;
		}

		JSONObject json;
		try {
			json = new JSONObject(response.body());
		} catch (JSONException e) {
			_log.info(response.body());
			e.printStackTrace();
			return null;
		}

		try {
			return json.getString("access_token");
		} catch (JSONException e) {
			_log.info(json.toString());
			e.printStackTrace();
			return null;
		}
	}

	public Lead createLead(Lead lead) {
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_LEAD_URL);

		_log.info("URL Crear lead: " + urlBuilder.toString());
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			POST(HttpRequest.BodyPublishers.ofString(mapRequestBody(lead))).
			build();

		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200 && response.statusCode() != 201) {
				throw new PortletException(2, "Error creating lead");
			}
		} catch (IOException | InterruptedException | PortletException e) {
			if(response != null) {
				_log.info(response.body());
			}
			e.printStackTrace();
			return null;
		}

		_log.info("** Lead creado correctamente ** " + response.statusCode());
		_log.info("** Respuesta creado lead ** " + response.body());
		return lead;
	}

	private String mapRequestBody(Lead lead){

		ObjectMapper mapper = new ObjectMapper();
		String mapperString = "";
		try {
			mapperString = mapper.writeValueAsString(mapToSendLeadRequest(lead));
			_log.info("** String lead request: " + mapperString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mapperString;
	}

	/**
	 *
	 * @param lead
	 * @return
	 */
	private SendLeadRequest mapToSendLeadRequest(Lead lead) {

		SendLeadRequest sendLeadRequest = new SendLeadRequest();

		sendLeadRequest.setPersonalData(mapToPersonalDataRequest(lead.getPersonalData()));
    sendLeadRequest.setCalculatorSolar(mapToCalculatorSolar(lead.getCalculatorSolar()));
    sendLeadRequest.getPersonalData().setPrivacyPolicy("GasServicios");

		return sendLeadRequest;
	}

	/**
	 *
	 * @param calculatorSolar
	 * @return
	 */
	private CalculatorSolarRequest mapToCalculatorSolar(CalculatorSolar calculatorSolar) {
		CalculatorSolarRequest calculatorSolarRequest = new CalculatorSolarRequest();
		calculatorSolarRequest.setSuperiorInstallation(calculatorSolar.getSuperiorInstallation());
		calculatorSolarRequest.setTotalPrice(calculatorSolar.getFinalPrice());
		calculatorSolarRequest.setIvaPrice(calculatorSolar.getIvaPrice());
		calculatorSolarRequest.setTotalPriceIva(calculatorSolar.getFinalPriceIva());
		calculatorSolarRequest.setInstallerCode(calculatorSolar.getInstallerCode());

		calculatorSolarRequest.setSelectedExtras(maptoSelectedExtras(calculatorSolar.getSelectedExtras()));
		calculatorSolarRequest.setInput(mapToSolarInputRequest(calculatorSolar.getInput()));
		calculatorSolarRequest.setOutput(mapToSolarOutputRequest(calculatorSolar.getOutput()));

		return calculatorSolarRequest;
	}

		/**
	 *
	 * @param selectedExtras
	 * @return
	 */
	private SelectedSolarExtrasRequest maptoSelectedExtras(SelectedExtras selectedExtras) {
		SelectedSolarExtrasRequest extras = new SelectedSolarExtrasRequest();
		extras.setExtraPanels(selectedExtras.getExtraPanels());
		extras.setTriphasicExtra(selectedExtras.getTriphasicExtra());
		extras.setRoofExtra(selectedExtras.getRoofExtra());
		extras.setPergolaExtra(selectedExtras.getPergolaExtra());
		extras.setPipelineUnderground(selectedExtras.getPipelineUnderground());
		extras.setBattery(selectedExtras.getBattery());
		extras.setCarCharger(selectedExtras.getCarCharger());
		extras.setInverterExtra(selectedExtras.getInverterExtra());
		return extras;
	}

	/**
	 *
	 * @param calculatorSolarInput
	 * @return
	 */
	private SolarInputRequest mapToSolarInputRequest(SolarBudgetRequest calculatorSolarInput) {
		SolarInputRequest inputRequest = new SolarInputRequest();
		inputRequest.setHouseType(calculatorSolarInput.getHouseTypeAsString());
		inputRequest.setMonthlyConsumption(calculatorSolarInput.getMonthlyConsumption());
		inputRequest.setAnnualConsumption(calculatorSolarInput.getAnnualConsumption());
		return inputRequest;
	}

	/**
	 *
	 * @param calculatorSolarOutput
	 * @return
	 */
	private SolarOutputRequest mapToSolarOutputRequest(SolarBudget calculatorSolarOutput) {
		SolarOutputRequest outputRequest = new SolarOutputRequest();
		outputRequest.setPanelsType(calculatorSolarOutput.getPanelsType());
		outputRequest.setPanelsExtra(calculatorSolarOutput.getPanelsExtra());
		outputRequest.setTriphasicExtra(calculatorSolarOutput.getTriphasicExtra());
		outputRequest.setInverterExtra(calculatorSolarOutput.getInverterExtra());
		outputRequest.setRoofExtra(calculatorSolarOutput.getRoofExtra());
		outputRequest.setPergolaExtra(calculatorSolarOutput.getPergolaExtra());
		outputRequest.setPipelineExtra(calculatorSolarOutput.getPipelineExtra());
		outputRequest.setCarCharger(calculatorSolarOutput.getCarCharger());
		outputRequest.setBattery(calculatorSolarOutput.getBattery());
		outputRequest.setAdditionalPanelsInstallation(calculatorSolarOutput.getAdditionalPanelsInstallation());
		outputRequest.setTotalPrice(calculatorSolarOutput.getTotalPrice());
		outputRequest.setSize(mapToSolarSize(calculatorSolarOutput.getSize()));
		outputRequest.setInverter(mapToSolarInverter(calculatorSolarOutput.getInverter()));
		outputRequest.setTotalPowerInstalled(calculatorSolarOutput.getTotalPowerInstalled());
		outputRequest.setSuperiorInstallation(mapToSolarSuperiorInstallation(calculatorSolarOutput.getSuperiorInstallation()));

		return outputRequest;
	}
	/**
	 *
	 * @param budgetSize
	 * @return
	 */
	private SolarSize mapToSolarSize(SolarBudgetSize budgetSize) {
		SolarSize size = new SolarSize();
		size.setValue(budgetSize.getValue());
		size.setUnitPrice(budgetSize.getUnitPrice());
		size.setPrice(budgetSize.getPrice());
		size.setBasePanels(budgetSize.getBasePanels());
		size.setTotalPanels(budgetSize.getTotalPanels());
		return size;
	}

	/**
	 *
	 * @param solarInverter
	 * @return
	 */
	private SolarInverter mapToSolarInverter(SolarOutputInverter solarInverter) {
		SolarInverter inverter = new SolarInverter();
		inverter.setBrand(solarInverter.getBrand());
		inverter.setModel(solarInverter.getModel());
		inverter.setPrice(solarInverter.getPrice());

		return inverter;
	}

	/**
	 *
	 * @param superiorInstallation
	 * @return
	 */
	private SolarSuperiorInstallation mapToSolarSuperiorInstallation(SuperiorInstallation superiorInstallation) {
		SolarSuperiorInstallation sInstallation = new SolarSuperiorInstallation();
		sInstallation.setPanelsType(superiorInstallation.getPanelsType());
		sInstallation.setPanelsExtra(superiorInstallation.getPanelsExtra());
		sInstallation.setTriphasicExtra(superiorInstallation.getTriphasicExtra());
		sInstallation.setInverterExtra(superiorInstallation.getInverterExtra());
		sInstallation.setInverterType(superiorInstallation.getInverterType());
		sInstallation.setRoofExtra(superiorInstallation.getRoofExtra());
		sInstallation.setPergolaExtra(superiorInstallation.getPergolaExtra());
		sInstallation.setPipelineExtra(superiorInstallation.getPipelineExtra());
		sInstallation.setCarCharger(superiorInstallation.getCarCharger());
		sInstallation.setBattery(superiorInstallation.getBattery());
		sInstallation.setExtraFornius(superiorInstallation.getExtraFornius());
		sInstallation.setAdditionalPanelsInstallation(superiorInstallation.getAdditionalPanelsInstallation());
		sInstallation.setSuperiorSize(mapToSolarSuperiorSize(superiorInstallation.getSuperiorSize()));
		sInstallation.setTotalPowerInstalled(superiorInstallation.getTotalPowerInstalled());

		return sInstallation;
	}

		/**
	 *
	 * @param superiorSize
	 * @return
	 */
	private SolarSuperiorSize mapToSolarSuperiorSize(SuperiorSize superiorSize) {
		SolarSuperiorSize size = new SolarSuperiorSize();
		size.setValue(superiorSize.getValue());
		size.setPrice(superiorSize.getPrice());
		size.setBasePanels(superiorSize.getBasePanels());
		return size;
	}


	/**
	 *
	 * @param personalData
	 * @return
	 */
	private PersonalDataRequest mapToPersonalDataRequest(PersonalData personalData) {
		PersonalDataRequest personalDataRequest = new PersonalDataRequest();

		personalDataRequest.setAcceptNotCom(personalData.getAcceptNotCom());
		personalDataRequest.setEmail(personalData.getEmail());
		personalDataRequest.setFirstName(personalData.getFirstName());
		personalDataRequest.setLastName(personalData.getLastName());
		personalDataRequest.setPhone(personalData.getPhone());

		if (personalData.getProperty() != null) {
			personalDataRequest.setAddressBloq(personalData.getProperty().getBlock());
			personalDataRequest.setAddressDoor(personalData.getProperty().getDoor());
			personalDataRequest.setAddressPlant(personalData.getProperty().getFloor());
			personalDataRequest.setAddressStair(personalData.getProperty().getLadder());
			personalDataRequest.setCodInmueble(personalData.getProperty().getPropertyId());
			personalDataRequest.setDescInmueble(personalData.getProperty().getAddress());
		}

		if (personalData.getEstate() != null) {
			personalDataRequest.setAddressNumber(personalData.getEstate().getNumber());
			personalDataRequest.setAddressStreet(personalData.getEstate().getAddressName());
			personalDataRequest.setCodFinca(personalData.getEstate().getGateId());
		}

		if (personalData.getPostalCode() != null) {
			personalDataRequest.setAddressPostalCode(personalData.getPostalCode().getPostalCode());
			personalDataRequest.setAddressTown(personalData.getPostalCode().getMunicipalityName());
			personalDataRequest.setCodMunicipio(personalData.getPostalCode().getProvinceId() + personalData.getPostalCode().getMunicipalityId());
		}

		if (personalData.getProdInterest() != null) {
			personalDataRequest.setProdInterest(personalData.getProdInterest().getValue());
		}

		return personalDataRequest;
	}

}
