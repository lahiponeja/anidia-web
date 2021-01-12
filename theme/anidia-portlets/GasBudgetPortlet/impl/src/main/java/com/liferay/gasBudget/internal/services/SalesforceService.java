package com.liferay.gasBudget.internal.services;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.liferay.gasBudget.dto.v1_0.*;
import com.liferay.gasBudget.internal.dto.*;
import com.liferay.gasBudget.internal.exception.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.*;
import java.net.http.*;
import java.util.*;
import org.json.*;


public class SalesforceService {

	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_ADDRESSES_URL = System.getenv().get("SALESFORCE_ADDRESSES_URL");
	static String SALESFORCE_ESTATES_URL = System.getenv().get("SALESFORCE_ESTATES_URL");
	static String SALESFORCE_PROPERTIES_URL = System.getenv().get("SALESFORCE_PROPERTIES_URL");
	static String SALESFORCE_LEAD_URL = System.getenv().get("SALESFORCE_LEAD_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

	public List<Property> getProperties(String gateId) {
		List<Property> properties = new ArrayList<Property>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_PROPERTIES_URL);
		urlBuilder.append("?");
		urlBuilder.append("codigo_unico_portal=");
		urlBuilder.append(gateId);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting properties to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC8AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS 7 , B, E, BJ4\",\"Codigo_unico_inmueble__c\":\"I21864951\",\"Tipo_finca__c\":\"SH\",\"CUPS__c\":\"ES123456789012345678\",\"Bloque__c\":\"B\",\"Escalera__c\":\"E\",\"Planta__c\":\"BJ\",\"Puerta__c\":\"4\",\"Estado__c\":\"01\",\"Canon_IRC__c\":6.35,\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC8AAM\"},{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC9AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS7\",\"Codigo_unico_inmueble__c\":\"I16621155\",\"Tipo_finca__c\":\"SH\",\"Estado__c\":\"01\",\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC9AAM\"}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return properties;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return properties;
		}

		// The response format is [{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC8AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS 7 , B, E, BJ4\",\"Codigo_unico_inmueble__c\":\"I21864951\",\"Tipo_finca__c\":\"SH\",\"CUPS__c\":\"ES123456789012345678\",\"Bloque__c\":\"B\",\"Escalera__c\":\"E\",\"Planta__c\":\"BJ\",\"Puerta__c\":\"4\",\"Estado__c\":\"01\",\"Canon_IRC__c\":6.35,\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC8AAM\"},{\"attributes\":{\"type\":\"Account\",\"url\":\"/services/data/v44.0/sobjects/Account/0011p00001hcUC9AAM\"},\"Direccion_completa__c\":\"CM HEREDEROS7\",\"Codigo_unico_inmueble__c\":\"I16621155\",\"Tipo_finca__c\":\"SH\",\"Estado__c\":\"01\",\"Finca__c\":\"0011p00001hcRY7AAM\",\"Id\":\"0011p00001hcUC9AAM\"}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject propertyJson = null;
			try {
				propertyJson = responseJson.getJSONObject(i);
				Property property = new Property();
				property.setPropertyId(propertyJson.optString("Codigo_unico_inmueble__c"));
				property.setBlock(propertyJson.optString("Bloque__c"));
				property.setLadder(propertyJson.optString("Escalera__c"));
				property.setFloor(propertyJson.optString("Planta__c"));
				property.setDoor(propertyJson.optString("Puerta__c"));
				property.setStatus(propertyJson.getString("Estado__c"));
				property.setContractStatus(propertyJson.optString("SAP_Estado_contrato_SAP__c"));

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
				System.out.println("Salesforce response: " + response.body());
				if(propertyJson != null) {
					System.out.println("Json Object with error: " + propertyJson.toString());
				}
				e.printStackTrace();
			}
		}
		return properties;
	}

	public List<Estate> getEstates(String municipalityId, String postalCode, String addressKind, String addressName) {
		List<Estate> estates = new ArrayList<Estate>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();

		try {
			urlBuilder.append(SALESFORCE_ESTATES_URL);
			urlBuilder.append("?");
			urlBuilder.append("municipio_ine=");
			urlBuilder.append(municipalityId);
			urlBuilder.append("&codigo_postal=");
			urlBuilder.append(postalCode);
			urlBuilder.append("&tipo_y_nombre_de_via=");
			urlBuilder.append(URLEncoder.encode(addressKind, StandardCharsets.UTF_8.toString()));
			urlBuilder.append("+");
			urlBuilder.append(URLEncoder.encode(addressName, StandardCharsets.UTF_8.toString()));
			// We have to sent the number as empty
			urlBuilder.append("&numero=");
			urlBuilder.append("&limit=500");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return estates;
		}

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting estates to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES\",\"Numero__c\":\"5\",\"Codigo_unico_portal__c\":\"22\",\"Segundo_numero_de_policia__c\":null}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return estates;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return estates;
		}

		// The response format is [{"attributes":{"type":"AggregateResult"},"Tipo_de_via__c":"CL","Nombre_de_via__c":"TEJEDORES","Numero__c":"5","Codigo_unico_portal__c":"22","Segundo_numero_de_policia__c":null}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject estateJson = null;
			try {
				estateJson = responseJson.getJSONObject(i);
				Estate estate = new Estate();
				estate.setAddressKind(estateJson.getString("Tipo_de_via__c"));
				estate.setAddressName(estateJson.getString("Nombre_de_via__c"));
				estate.setNumber(estateJson.getString("Numero__c"));
				estate.setGateId(estateJson.getString("Codigo_unico_portal__c"));
				estate.setAnnex(estateJson.optString("Segundo_numero_de_policia__c"));
				estates.add(estate);
			} catch (JSONException e) {
				System.out.println("Salesforce response: " + response.body());
				if(estateJson != null) {
					System.out.println("Json Object with error: " + estateJson.toString());
				}
				e.printStackTrace();
			}
		}
		return estates;
	}


	public List<Address> getAddresses(String municipalityId, String postalCode) {

		List<Address> addresses = new ArrayList<Address>();
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_ADDRESSES_URL);
		urlBuilder.append("?");
		urlBuilder.append("municipio_ine=");
		urlBuilder.append(municipalityId);
		urlBuilder.append("&codigo_postal=");
		urlBuilder.append(postalCode);
		urlBuilder.append("&limit=500");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			GET().
			build();

		HttpResponse<String> response = null;
		JSONArray responseJson;

		System.out.println("Requesting addresses to " + urlBuilder.toString());

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			responseJson = new JSONArray(response.body());
//			responseJson = new JSONArray("[{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES\"},{\"attributes\":{\"type\":\"AggregateResult\"},\"Tipo_de_via__c\":\"CL\",\"Nombre_de_via__c\":\"TEJEDORES 2\"}]");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return addresses;
		} catch (JSONException e) {
			if(response != null) {
				System.out.println("Salesforce response: " + response.body());
			}
			e.printStackTrace();
			return addresses;
		}

		// The response format is [{"attributes":{"type":"AggregateResult"},"Tipo_de_via__c":"CL","Nombre_de_via__c":"TEJEDORES"}]
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject addressJson = null;
			try {
				addressJson = responseJson.getJSONObject(i);
				Address address = new Address();
				address.setKind(addressJson.optString("Tipo_de_via__c"));
				if(address.getKind() == null) {
					address.setKind("");
				}
				address.setName(addressJson.getString("Nombre_de_via__c"));
				addresses.add(address);
			} catch (JSONException e) {
				System.out.println("Salesforce response: " + response.body());
				if(addressJson != null) {
					System.out.println("Json Object with error: " + addressJson.toString());
				}
				e.printStackTrace();
			}
		}
		return addresses;
	}

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
			return json.getString("access_token");
		} catch (JSONException e) {
			System.out.println(json.toString());
			e.printStackTrace();
			return null;
		}
	}

	public Lead createLead(Lead lead) {
		String token = this.getSalesforceToken();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_LEAD_URL);

		System.out.println("URL Crear lead: " + urlBuilder.toString());
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			header("Authorization", "Bearer " + token).
			POST(HttpRequest.BodyPublishers.ofString(mapRequestBody(lead))).
			build();

		HttpResponse<String> response = null;
		/*try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200 && response.statusCode() != 201) {
				throw new PortletException(2, "Error creating lead");
			}
		} catch (IOException | InterruptedException | PortletException e) {
			if(response != null) {
				System.out.println(response.body());
			}
			e.printStackTrace();
			return null;
		}

		System.out.println("** Lead creado correctamente ** " + response.statusCode());
		System.out.println("** Respuesta creado lead ** " + response.body());*/
		return lead;
	}

	private String mapRequestBody(Lead lead){

		ObjectMapper mapper = new ObjectMapper();
		String mapperString = "";
		try {
			mapperString = mapper.writeValueAsString(mapToSendLeadRequest(lead));
			System.out.println("** String lead request: " + mapperString);
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
		if (lead.getCalculatorGas() != null){
			sendLeadRequest.setCalculatorGas(mapToCalculatorGas(lead.getCalculatorGas()));
			sendLeadRequest.getPersonalData().setPrivacyPolicy("GasServicios");
		}
		else if (lead.getCalculatorSolar() != null){
			sendLeadRequest.setCalculatorSolar(mapToCalculatorSolar(lead.getCalculatorSolar()));
			sendLeadRequest.getPersonalData().setPrivacyPolicy("SolarServicios");
		}
		
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
		calculatorSolarRequest.setFinalPrice(calculatorSolar.getFinalPrice());
		calculatorSolarRequest.setInstallatorId(calculatorSolar.getInstallatorId());

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
		inputRequest.setRoofType(calculatorSolarInput.getRoofTypeAsString());
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
	 * @param calculatorGas
	 * @return
	 */
	private CalculatorGasRequest mapToCalculatorGas(CalculatorGas calculatorGas) {
		CalculatorGasRequest calculatorGasRequest = new CalculatorGasRequest();
		calculatorGasRequest.setInput(mapToInputRequest(calculatorGas.getInput()));
		calculatorGasRequest.setOutput(mapToOutputRequest(calculatorGas.getOutput()));

		return calculatorGasRequest;
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

	/**
	 *
	 * @param calculatorGasInput
	 * @return
	 */
	private InputRequest mapToInputRequest(CalculatorGasInput calculatorGasInput) {
		InputRequest inputRequest = new InputRequest();
		inputRequest.setaCSUse(calculatorGasInput.getAcsUse());
		inputRequest.setBathroomNumber(calculatorGasInput.getBathroomNumber());
		inputRequest.setBoilerLocation(calculatorGasInput.getBoilerLocation());
		inputRequest.setExtras(mapToExtrasInput(calculatorGasInput.getExtras()));
		inputRequest.setFloorNumber(calculatorGasInput.getFloorNumber());
		inputRequest.setGasNaturalUse(calculatorGasInput.getGasNaturalUse());
		inputRequest.setHeatingUse(calculatorGasInput.getHeatingUse());
		inputRequest.setHouseType(calculatorGasInput.getHouseType());
		inputRequest.setKitchenUse(calculatorGasInput.getKitchenUse());
		inputRequest.setPersonsWater(this.translatePersonsWater(calculatorGasInput.getPersonsWater()));
		inputRequest.setPropertyMeters(calculatorGasInput.getPropertyMeters());
		inputRequest.setStaysNumber(Long.valueOf(calculatorGasInput.getStaysNumber()));
		inputRequest.setZipCode(calculatorGasInput.getZipCode());

		return inputRequest;
	}

	/**
	 *
	 * @param calculatorGasInputExtras
	 * @return
	 */
	private ExtrasInput mapToExtrasInput(CalculatorGasInputExtras calculatorGasInputExtras) {
		ExtrasInput extrasInput = new ExtrasInput();
		extrasInput.setControllHeatingFloor(Boolean.valueOf(calculatorGasInputExtras.getControllHeatingFloor()));
		extrasInput.setConvertDeviceKitchen(Boolean.valueOf(calculatorGasInputExtras.getConvertDeviceKitchen()));
		extrasInput.setHasVentilationGrill(Boolean.valueOf(calculatorGasInputExtras.getHasVentilationGrill()));
		extrasInput.setMetersBoilerToWindow(calculatorGasInputExtras.getMetersBoilerToWindow());
		extrasInput.setConnectDeviceToKitchen(Boolean.valueOf(calculatorGasInputExtras.getConnectDeviceToKitchen()));
		String replaceMetersWaterIntake = calculatorGasInputExtras.getMetersWaterIntake().substring(2);
		extrasInput.setMetersWaterIntake(replaceMetersWaterIntake);
		extrasInput.setRadiatorsBathroom(calculatorGasInputExtras.getRadiatorsBathroom());

		return extrasInput;
	}

	/**
	 *
	 * @param calculatorGasOutput
	 * @return
	 */
	private OutputRequest mapToOutputRequest(CalculatorGasOutput calculatorGasOutput) {
		OutputRequest outputRequest = new OutputRequest();
		outputRequest.setBaseBadget(calculatorGasOutput.getBaseBadget());
		outputRequest.setBonus(calculatorGasOutput.getBonus());
		outputRequest.setEquipment(calculatorGasOutput.getEquipment());
		outputRequest.setExtras(mapToExtrasOutput(calculatorGasOutput.getExtras()));
		outputRequest.setIva21(calculatorGasOutput.getIva21());
		outputRequest.setProposedPack(calculatorGasOutput.getProposedPack());
		outputRequest.setTotalBudget(calculatorGasOutput.getTotalBudget());
		outputRequest.setTotalPVP(calculatorGasOutput.getTotalPVP());

		return outputRequest;
	}

	/**
	 *
	 * @param calculatorGasOutputExtras
	 * @return
	 */
	private ExtrasOutput mapToExtrasOutput(CalculatorGasOutputExtras calculatorGasOutputExtras) {
		ExtrasOutput extrasOutput = new ExtrasOutput();
		extrasOutput.setControllHeatingFloor(calculatorGasOutputExtras.getControllHeatingFloor());
		extrasOutput.setConvertDeviceKitchen(calculatorGasOutputExtras.getConvertDeviceKitchen());
		extrasOutput.setExtraTotalPrice(calculatorGasOutputExtras.getExtraTotalPrice());
		extrasOutput.setHasVentilationGrill(calculatorGasOutputExtras.getHasVentilationGrill());
		extrasOutput.setConnectDeviceToKitchen(calculatorGasOutputExtras.getConnectDeviceToKitchen());
		extrasOutput.setMetersBoilerToWindow(calculatorGasOutputExtras.getMetersBoilerToWindow());
		String replaceMetersWaterIntake = calculatorGasOutputExtras.getMetersWaterIntake().substring(2);
		extrasOutput.setMetersWaterIntake(replaceMetersWaterIntake);
		extrasOutput.setRadiatorsBathroom(calculatorGasOutputExtras.getRadiatorsBathroom());

		return extrasOutput;
	}

	private String translatePersonsWater(String original) {
    switch (original) {
      case "Hasta 2":
        return "Hasta 2 personas";
      case "Entre 3-4":
        return "De 3-4 personas";
      case "Más de 4":
        return "5 o más personas";
    }
    return null;
  }

}
