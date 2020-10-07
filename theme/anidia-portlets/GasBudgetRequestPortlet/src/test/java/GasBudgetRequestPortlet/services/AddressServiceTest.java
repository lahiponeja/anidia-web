import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import GasBudgetRequestPortlet.services.AddressService;

public class AddressServiceTest {

	@Test
	public void testGetMunicipalties() throws IOException  {

		AddressService service = new AddressService();
		Assert.assertFalse(service.getMunicipalties().isEmpty());
		Assert.assertEquals(14718, service.getMunicipalties().size());
	}

}
