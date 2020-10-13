package ContactFormPortlet.services;


import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SalesforceServiceTest {
	@Before
	public void loadSalesforceEnvVars() throws IOException  {
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("salesforce.properties"));

		SalesforceService.SALESFORCE_TOKEN_URL = props.getProperty("SALESFORCE_TOKEN_URL");
		SalesforceService.SALESFORCE_PASSWORD = props.getProperty("SALESFORCE_PASSWORD");
		SalesforceService.SALESFORCE_CLIENT_SECRET = props.getProperty("SALESFORCE_CLIENT_SECRET");
		SalesforceService.SALESFORCE_CLIENT_ID = props.getProperty("SALESFORCE_CLIENT_ID");
		SalesforceService.SALESFORCE_USERNAME = props.getProperty("SALESFORCE_USERNAME");
	}

	@Test
	public void testSalesforceToken() throws IOException  {
		AddressService service = new AddressService();
		Assert.assertNotNull(service.getSalesforceToken());
	}

}
