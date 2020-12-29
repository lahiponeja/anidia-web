package com.liferay.gasBudget.internal.services;

public class SalesforceServiceTest {

    /*@Before
    public void loadSalesforceEnvVars() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream(
            "portlet.properties"));

        SalesforceService.SALESFORCE_TOKEN_URL = props.getProperty("SALESFORCE_TOKEN_URL");
        SalesforceService.SALESFORCE_ADDRESSES_URL = props.getProperty("SALESFORCE_ADDRESSES_URL");
        SalesforceService.SALESFORCE_ESTATES_URL = props.getProperty("SALESFORCE_ESTATES_URL");
        SalesforceService.SALESFORCE_PROPERTIES_URL = props.getProperty("SALESFORCE_PROPERTIES_URL");
        SalesforceService.SALESFORCE_LEAD_URL = props.getProperty("SALESFORCE_LEAD_URL");
        SalesforceService.SALESFORCE_PASSWORD = props.getProperty("SALESFORCE_PASSWORD");
        SalesforceService.SALESFORCE_CLIENT_SECRET = props.getProperty("SALESFORCE_CLIENT_SECRET");
        SalesforceService.SALESFORCE_CLIENT_ID = props.getProperty("SALESFORCE_CLIENT_ID");
        SalesforceService.SALESFORCE_USERNAME = props.getProperty("SALESFORCE_USERNAME");
    }

    @Test
    public void testSalesforceToken() {
        SalesforceService service = new SalesforceService();
        try {
            service.getSalesforceToken();
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAddresses() {
        SalesforceService service = new SalesforceService();
        try {
            service.getAddresses("05", "05500");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProperties() {
        SalesforceService service = new SalesforceService();
        try {
            service.getProperties("4");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEstates() {
        SalesforceService service = new SalesforceService();
        try {
            service.getEstates("", "", "", "");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLead() {

        SalesforceService service = new SalesforceService();
        Lead lead = new Lead();
        lead.setPersonalData(mapPersonalData());
        lead.setCalculatorGas(mapCalculatorGas());

        try {
            service.createLead(lead);
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }


    private PersonalData mapPersonalData() {
        PersonalData personalData = new PersonalData();
        personalData.setAcceptNotCom(true); //Boolean
        personalData.setEmail("junittest@test.com"); //String
        personalData.setEstate(mapEstate()); //Estate
        personalData.setFirstName("Test"); //String
        //personalData.setLastName(); //String
        //personalData.setPhone(); //String
        personalData.setPostalCode(mapPostalCode()); //Postalcode
        personalData.setProdInterest(mapProdInterest()); //ProdInterest
        personalData.setProperty(mapProperty()); //Property

        return personalData;
    }

    private CalculatorGas mapCalculatorGas() {
        CalculatorGas calculatorGas = new CalculatorGas();
        calculatorGas.setInput(mapCalculatorGasInput());
        calculatorGas.setOutput(mapCalculatorGasOutput());

        return calculatorGas;
    }

    private CalculatorGasInput mapCalculatorGasInput() {
        CalculatorGasInput calculatorGasInput = new CalculatorGasInput();
        //calculatorGasInput.setAcsUse(); //String
        //calculatorGasInput.setBathroomNumber(); //String
        //calculatorGasInput.setBoilerLocation(); //String
        calculatorGasInput.setExtras(mapCalculatorGasInputExtras()); //CalculatorGasInputExtras
        //calculatorGasInput.setFloorNumber(); //String
        //calculatorGasInput.setGasNaturalUse(); //String
        //calculatorGasInput.setHeatingUse(); //String
        //calculatorGasInput.setHouseType(); //String
        //calculatorGasInput.setKitchenUse(); //String
        //calculatorGasInput.setPersonsWater(); //String
        //calculatorGasInput.setPropertyMeters(); //String
        //calculatorGasInput.setStaysNumber(); //String
        //calculatorGasInput.setZipCode(); //String

        return calculatorGasInput;
    }

    private CalculatorGasInputExtras mapCalculatorGasInputExtras() {
        CalculatorGasInputExtras calculatorGasInputExtras = new CalculatorGasInputExtras();
        //calculatorGasInputExtras.setConnectDeviceToKitchen(); //String
        //calculatorGasInputExtras.setControllHeatingFloor(); //String
        //calculatorGasInputExtras.setConvertDeviceKitchen(); //String
        //calculatorGasInputExtras.setHasVentilationGrill(); //String
        //calculatorGasInputExtras.setMetersBoilerToWindow(); //Integer
        //calculatorGasInputExtras.setMetersWaterIntake(); //String
        //calculatorGasInputExtras.setRadiatorsBathroom(); //String

        return calculatorGasInputExtras;
    }

    private CalculatorGasOutput mapCalculatorGasOutput() {
        CalculatorGasOutput calculatorGasOutput = new CalculatorGasOutput();
        //calculatorGasOutput.setBaseBadget(); //String
        //calculatorGasOutput.setBonus(); //String
        //calculatorGasOutput.setEquipment(); //String
        calculatorGasOutput.setExtras(mapCalculatorGasOutputExtras()); //CalculatorGasOutputExtras
        //calculatorGasOutput.setIva21(); //String
        //calculatorGasOutput.setProposedPack(); //String
        //calculatorGasOutput.setTotalBudget(); //String
        //calculatorGasOutput.setTotalPVP(); //String

        return calculatorGasOutput;
    }

    private CalculatorGasOutputExtras mapCalculatorGasOutputExtras() {
        CalculatorGasOutputExtras calculatorGasOutputExtras = new CalculatorGasOutputExtras();
        //calculatorGasOutputExtras.setControllHeatingFloor(); //String
        //calculatorGasOutputExtras.setConvertDeviceKitchen(); //String
        //calculatorGasOutputExtras.setExtraTotalPrice(); //Number
        //calculatorGasOutputExtras.setHasVentilationGrill(); //String
        //calculatorGasOutputExtras.setMetersBoilerToWindow(); //String
        //calculatorGasOutputExtras.setMetersWaterIntake(); //String
        //calculatorGasOutputExtras.setRadiatorsBathroom(); //String

        return calculatorGasOutputExtras;
    }

    private Estate mapEstate() {
        Estate estate = new Estate();
        //estate.setAnnex(); //String
        //estate.setGateId(); //String
        //estate.setNumber(); //String
        //estate.setAddressName(); //String
        //estate.setAddressKind(); //String

        return estate;
    }

    private PostalCode mapPostalCode() {
        PostalCode postalCode = new PostalCode();
        //postalCode.setMunicipalityId(); //String
        //postalCode.setMunicipalityName(); //String
        //postalCode.setPostalCode(); //String
        //postalCode.setProvinceId(); //String

        return postalCode;
    }

    private PersonalData.ProdInterest mapProdInterest() {
        return null;
    }

    private Property mapProperty() {
        Property property = new Property();
        //property.setContractStatus(); //String
        //property.setStatus(); //String
        //property.setDoor(); //String
        //property.setFloor(); //String
        //property.setLadder(); //String
        //property.setBlock(); //String
        //property.setPropertyId(); //String
        //property.setAddress(); //String

        return property;
    }*/
}
