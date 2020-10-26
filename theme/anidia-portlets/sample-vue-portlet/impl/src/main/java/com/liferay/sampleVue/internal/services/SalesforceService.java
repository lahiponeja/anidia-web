package com.liferay.sampleVue.internal.services;

import com.liferay.sampleVue.dto.v1_0.*;
import com.liferay.sampleVue.internal.dto.*;
import com.liferay.sampleVue.internal.exception.*;
import java.util.*;
import org.apache.commons.lang3.*;
import org.springframework.http.*;
import org.springframework.web.client.*;


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

	/**
	 *
	 * @param gateId
	 * @return
	 * @throws PortletException
	 */
	public List<Property> getProperties(String gateId) throws PortletException {

		RestTemplate restTemplate = new RestTemplate();
		List<Property> properties;

		try {
			String accessToken = getSalesforceToken();

			String propertiesEndPoint = getPropertiesUrl(gateId);
			HttpEntity<?> httpEntity = new HttpEntity(getHeaderToken(accessToken));

			ResponseEntity<PropertiesResponse> responseEntity = restTemplate.exchange(propertiesEndPoint,
				HttpMethod.GET, httpEntity, PropertiesResponse.class);

			properties = mapPropertiesResponseToPropertyList(
				Objects.requireNonNull(responseEntity.getBody()));

		} catch (HttpClientErrorException e) {
			throw new PortletException(1, e.getMessage());
		}

		return properties;
	}

	/**
	 *
	 * @param municipalityId
	 * @param postalCode
	 * @param addressKind
	 * @param addressName
	 * @return
	 * @throws PortletException
	 */
	public List<Estate> getEstates(String municipalityId, String postalCode, String addressKind,
		String addressName) throws PortletException {

		RestTemplate restTemplate = new RestTemplate();
		List<Estate> estates;

		try {
			String accessToken = getSalesforceToken();
			String estatesEndPoint = getEstatesUrl(municipalityId, postalCode, addressKind,
				addressName);
			HttpEntity<?> httpEntity = new HttpEntity<>(getHeaderToken(accessToken));

			ResponseEntity<EstatesResponse> responseEntity = restTemplate.exchange(estatesEndPoint,
				HttpMethod.GET, httpEntity, EstatesResponse.class);
			estates = mapEstatesResponseToEstateList(Objects.requireNonNull(responseEntity.getBody()));

		} catch (HttpClientErrorException e) {
			throw new PortletException(1, e.getMessage());
		}

		return estates;
	}

	/**
	 *
	 * @param municipalityId
	 * @param postalCode
	 * @return
	 * @throws PortletException
	 */
	public List<Address> getAddresses(String municipalityId, String postalCode)
		throws PortletException {

		RestTemplate restTemplate = new RestTemplate();
		List<Address> addresses;

		try {
			String accessToken = getSalesforceToken();
			String addressesEndPoint = getAddressesUrl(municipalityId, postalCode);
			HttpEntity<?> httpEntity = new HttpEntity(getHeaderToken(accessToken));

			ResponseEntity<AddressesResponse> responseEntity = restTemplate.exchange(addressesEndPoint,
				HttpMethod.GET, httpEntity, AddressesResponse.class);

			addresses = mapAddressesResponseToAddressList(
				Objects.requireNonNull(responseEntity.getBody()));

		} catch (HttpClientErrorException e) {
			throw new PortletException(1, e.getMessage());
		}

		return addresses;
	}

	/**
	 *
	 * @param lead
	 */
	public Lead createLead(Lead lead) throws PortletException {

		RestTemplate restTemplate = new RestTemplate();

		String accessToken = getSalesforceToken();
		//validateSendLeadRequest(lead);

		SendLeadRequest sendLeadRequest = mapToSendLeadRequest(lead);
		HttpEntity<SendLeadRequest> entity = new HttpEntity<>(sendLeadRequest,
			getHeaderToken(accessToken));

		String url = getSendLeadUrl();
		restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);

		return lead;
	}

	/**
	 *
	 * @param lead
	 * @return
	 */
	private SendLeadRequest mapToSendLeadRequest(Lead lead) {

		SendLeadRequest sendLeadRequest = new SendLeadRequest();
		sendLeadRequest.setCalculatorGasRequest(mapToCalculatorGas(lead.getCalculatorGas()));
		sendLeadRequest.setPersonalDataRequest(mapToPersonalDataRequest(lead.getPersonalData()));

		return sendLeadRequest;
	}

	/**
	 *
	 * @param calculatorGas
	 * @return
	 */
	private CalculatorGasRequest mapToCalculatorGas(CalculatorGas calculatorGas) {
		CalculatorGasRequest calculatorGasRequest = new CalculatorGasRequest();
		calculatorGasRequest.setInputRequest(mapToInputRequest(calculatorGas.getInput()));
		calculatorGasRequest.setOutputRequest(mapToOutputRequest(calculatorGas.getOutput()));

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
		personalDataRequest.setAddressBloq(personalData.getProperty().getBlock());
		personalDataRequest.setAddressDoor(personalData.getProperty().getDoor());
		personalDataRequest.setAddressNumber(personalData.getEstate().getNumber());
		personalDataRequest.setAddressPlant(personalData.getProperty().getFloor());
		personalDataRequest.setAddressPostalCode(personalData.getPostalCode().getPostalCode());
		personalDataRequest.setAddressStair(personalData.getProperty().getLadder());
		personalDataRequest.setAddressStreet(personalData.getEstate().getAddressName());
		personalDataRequest.setAddressTown(personalData.getPostalCode().getMunicipalityName());
		personalDataRequest.setEmail(personalData.getEmail());
		personalDataRequest.setFirstName(personalData.getFirstName());
		personalDataRequest.setLastName(personalData.getLastName());
		personalDataRequest.setPhone(personalData.getPhone());
		personalDataRequest.setProdInterest(personalData.getProdInterest().getValue());

		return personalDataRequest;
	}

	/**
	 *
	 * @param calculatorGasInput
	 * @return
	 */
	private InputRequest mapToInputRequest(CalculatorGasInput calculatorGasInput) {
		InputRequest inputRequest = new InputRequest();
		inputRequest.setACSUse(calculatorGasInput.getAcsUse());
		inputRequest.setBathroomNumber(calculatorGasInput.getBathroomNumber());
		inputRequest.setBoilerLocation(calculatorGasInput.getBoilerLocation());
		inputRequest.setExtrasInput(mapToExtrasInput(calculatorGasInput.getExtras()));
		inputRequest.setFloorNumber(calculatorGasInput.getFloorNumber());
		inputRequest.setGasNaturalUse(calculatorGasInput.getGasNaturalUse());
		inputRequest.setHeatingUse(calculatorGasInput.getHeatingUse());
		inputRequest.setHouseType(calculatorGasInput.getHouseType());
		inputRequest.setKitchenUse(calculatorGasInput.getKitchenUse());
		inputRequest.setPersonsWater(calculatorGasInput.getPersonsWater());
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
		extrasInput.setControllHeatingFloor(calculatorGasInputExtras.getControllHeatingFloor());
		extrasInput.setConvertDeviceKitchen(calculatorGasInputExtras.getConvertDeviceKitchen());
		extrasInput.setHasVentilationGrill(calculatorGasInputExtras.getHasVentilationGrill());
		extrasInput.setMetersBoilerToWindow(calculatorGasInputExtras.getMetersBoilerToWindow());
		extrasInput.setMetersWaterIntake(calculatorGasInputExtras.getMetersWaterIntake());
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
		outputRequest.setExtrasOutput(mapToExtrasOutput(calculatorGasOutput.getExtras()));
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
		extrasOutput.setMetersBoilerToWindow(calculatorGasOutputExtras.getMetersBoilerToWindow());
		extrasOutput.setMetersWaterIntake(calculatorGasOutputExtras.getMetersWaterIntake());
		extrasOutput.setRadiatorsBathroom(calculatorGasOutputExtras.getRadiatorsBathroom());

		return extrasOutput;
	}

	/**
	 *
	 * @param propertiesResponse
	 * @return
	 */
	private List<Property> mapPropertiesResponseToPropertyList(
		PropertiesResponse propertiesResponse) {

		List<Property> properties = new ArrayList<>();
		for (PropertyResponse propertyResponse : propertiesResponse.getPropertyResponses()) {
			properties.add(mapPropertyResponseToProperty(propertyResponse));
		}

		return properties;
	}

	/**
	 *
	 * @param propertyResponse
	 * @return
	 */
	private Property mapPropertyResponseToProperty(PropertyResponse propertyResponse) {
		Property property = new Property();
		property.setAddress(propertyResponse.getDireccionCompletaC());
		property.setPropertyId(propertyResponse.getCodigoUnicoInmuebleC());
		property.setBlock(propertyResponse.getBloqueC());
		property.setLadder(propertyResponse.getEscaleraC());
		property.setFloor(propertyResponse.getPlantaC());
		property.setDoor(propertyResponse.getPuertaC());
		property.setStatus(propertyResponse.getEstadoC());
		//property.setContractStatus(property.); //TODO: Qué campo mapeamos aquí??

		return property;
	}

	/**
	 * Map EstatesResponse object to Estate list object
	 *
	 * @param estatesResponse
	 * @return
	 */
	private List<Estate> mapEstatesResponseToEstateList(EstatesResponse estatesResponse) {
		List<Estate> estates = new ArrayList<>();
		for (EstateResponse estateResponse : estatesResponse.getEstateResponses()){
			estates.add(mapEstateResponseToEstate(estateResponse));
		}
		return estates;
	}

	/**
	 * Map EstateResponse object to Estate object
	 *
	 * @param estateResponse
	 * @return
	 */
	private Estate mapEstateResponseToEstate(EstateResponse estateResponse) {
		Estate estate = new Estate();
		estate.setAddressKind(estateResponse.getTipoDeViaC());
		estate.setAddressName(estateResponse.getNombreDeViaC());
		estate.setNumber(estateResponse.getNumeroC());
		estate.setGateId(estateResponse.getCodigoUnicoPortalC());
		estate.setAnnex(estateResponse.getSegundoNumeroDePoliciaC());

		return estate;
	}

	/**
	 * Map AddressesResponse object to Address list object
	 *
	 * @param addressesResponse
	 * @return
	 */
	private List<Address> mapAddressesResponseToAddressList(AddressesResponse addressesResponse) {
		List<Address> addresses = new ArrayList<>();
		for (AddressResponse addressResponse : addressesResponse.getAddressResponses()) {
			addresses.add(mapAddressResponseToAddress(addressResponse));
		}
		return addresses;
	}

	/**
	 * Map AddressResponse object to Address object
	 *
	 * @param addressResponse
	 * @return
	 */
	private Address mapAddressResponseToAddress(AddressResponse addressResponse) {
		Address address = new Address();
		address.setKind(addressResponse.getTipoDeViaC());
		address.setName(addressResponse.getNombreDeViaC());

		return address;
	}

	/**
	 * Method that returns the access token
	 *
	 * @return
	 * @throws PortletException
	 */
	public String getSalesforceToken() throws PortletException {

		RestTemplate restTemplate = new RestTemplate();

		String tokenUrl = getTokenUrl();
		AccessTokenResponse response = restTemplate.postForObject(
			tokenUrl, null, AccessTokenResponse.class);

		if (response != null && StringUtils.isNotEmpty(response.getAccessToken())) {
			return response.getAccessToken();
		} else {
			throw new PortletException(2, "Can not retrieve the access token");
		}
	}

	/**
	 *
	 * @param authToken
	 * @return
	 */
	private HttpHeaders getHeaderToken(String authToken) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBearerAuth(authToken);
		return httpHeaders;
	}

	/**
	 *
	 * @return
	 */
	private String getTokenUrl() {
		return SALESFORCE_TOKEN_URL + "?grant_type=password&client_id=" + SALESFORCE_CLIENT_ID + "&client_secret=" + SALESFORCE_CLIENT_SECRET + "&username=" + SALESFORCE_USERNAME + "&password=" + SALESFORCE_PASSWORD;
	}

	/**
	 *
	 * @param gateId
	 * @return
	 */
	private String getPropertiesUrl(String gateId) {
		return SALESFORCE_PROPERTIES_URL + "?codigo_unico_portal=" + gateId;
	}

	private String getEstatesUrl(String municipalityId, String postalCode, String addressKind,
		String addressName) {

		//Param numero must be empty
		return SALESFORCE_ESTATES_URL + "?municipio_ine=" + municipalityId + "&codigo_postal=" + postalCode + "&tipo_y_nombre_de_via=" + addressKind + "+" + addressName + "&numero=";
	}

	/**
	 *
	 * @param municipalityId
	 * @param postalCode
	 * @return
	 */
	private String getAddressesUrl(String municipalityId, String postalCode) {
		return SALESFORCE_ADDRESSES_URL + "?municipio_ine=" + municipalityId + "&codigo_postal=" + postalCode;
	}

	/**
	 *
	 * @return
	 */
	private String getSendLeadUrl(){
		return SALESFORCE_LEAD_URL;
	}
}
