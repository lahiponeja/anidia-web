package com.liferay.gasBudget.internal.resource.v1_0;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.gasBudget.internal.services.SalesforceService;
import com.liferay.gasBudget.resource.v1_0.AddressResource;
import com.liferay.gasBudget.dto.v1_0.Address;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/address.properties",
	scope = ServiceScope.PROTOTYPE, service = AddressResource.class
)
public class AddressResourceImpl extends BaseAddressResourceImpl {
	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/gas-budget/v1.0/addresses/{municipalityId}/{postalCode}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Get all the addresses for a municipality and postal code from Anidia CRM"
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "municipalityId"),
			@Parameter(in = ParameterIn.PATH, name = "postalCode")
		}
	)
	@Path("/addresses/{municipalityId}/{postalCode}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Address")})
	public Page<Address> getAddressesPostalCodePage(
			@NotNull @Parameter(hidden = true) @PathParam("municipalityId")
				String municipalityId,
			@NotNull @Parameter(hidden = true) @PathParam("postalCode") String
				postalCode)
		throws Exception {
			SalesforceService salesforce = new SalesforceService();

		return Page.of(salesforce.getAddresses(municipalityId, postalCode));
	}
}