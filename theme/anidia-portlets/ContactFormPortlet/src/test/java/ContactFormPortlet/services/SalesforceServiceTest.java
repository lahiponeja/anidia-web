package ContactFormPortlet.services;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class AddressServiceTest {

	@Test
	public void testSalesforceToken() throws IOException  {
		AddressService service = new AddressService();
		Assert.assertNotNull(service.getSalesforceToken());
	}

}
