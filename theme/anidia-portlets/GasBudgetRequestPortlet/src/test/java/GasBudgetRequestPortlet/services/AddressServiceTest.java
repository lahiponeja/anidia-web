package GasBudgetRequestPortlet.services;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class AddressServiceTest {

	@Test
	public void testGetMunicipalties() throws IOException  {
		AddressService service = new AddressService();
		Assert.assertFalse(service.getMunicipalties().isEmpty());
		Assert.assertEquals(14718, service.getMunicipalties().size());
	}

	@Test
	public void testSalesforceToken() throws IOException  {
		AddressService service = new AddressService();
		Assert.assertNotNull(service.getSalesforceToken());
	}

}
