// package ContactFormPortlet.services;


// import ContactFormPortlet.dto.*;
// import ContactFormPortlet.exception.*;
// import java.io.*;
// import java.util.*;
// import org.junit.*;

// public class SalesforceServiceTest {
// 	@Before
// 	public void loadSalesforceEnvVars() throws IOException  {
// 		Properties props = new Properties();
// 		props.load(getClass().getResourceAsStream("portlet.properties"));

// 		SalesforceService.SALESFORCE_TOKEN_URL = props.getProperty("SALESFORCE_TOKEN_URL");
// 		SalesforceService.SALESFORCE_LEAD_URL = props.getProperty("SALESFORCE_LEAD_URL");
// 		SalesforceService.SALESFORCE_PASSWORD = props.getProperty("SALESFORCE_PASSWORD");
// 		SalesforceService.SALESFORCE_CLIENT_SECRET = props.getProperty("SALESFORCE_CLIENT_SECRET");
// 		SalesforceService.SALESFORCE_CLIENT_ID = props.getProperty("SALESFORCE_CLIENT_ID");
// 		SalesforceService.SALESFORCE_USERNAME = props.getProperty("SALESFORCE_USERNAME");
// 	}

// 	@Test
// 	public void testSalesforceToken() {
// 		SalesforceService service = new SalesforceService();
// 		try {
// 			Assert.assertNotNull(service.getSalesforceToken());
// 		} catch (PortletException e) {
// 			e.printStackTrace();
// 		}
// 	}


// 	@Test
// 	public void testSendLead()   {
// 		try {
// 			SalesforceService service = new SalesforceService();
// 			LeadDTO lead = new LeadDTO();
// 			lead.setFirstName("Test First Name");
// 			lead.setLastName("Test Last Name");
// 			lead.setEmail("email@test.com");
// 			lead.setPhonePrefix("+34");
// 			lead.setPhoneNumber("665443212");
// 			lead.setProductType("Gas");
// 			service.sendLead(lead);
// 		} catch (ValidationException | PortletException e) {
// 			e.printStackTrace();
// 		}
// 	}

// }
