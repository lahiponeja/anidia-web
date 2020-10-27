package com.liferay.gasBudget.internal.resource.v1_0;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.gasBudget.internal.services.SalesforceService;
import com.liferay.gasBudget.resource.v1_0.EstateResource;
import com.liferay.gasBudget.dto.v1_0.Estate;

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
	properties = "OSGI-INF/liferay/rest/v1_0/estate.properties",
	scope = ServiceScope.PROTOTYPE, service = EstateResource.class
)
public class EstateResourceImpl extends BaseEstateResourceImpl {

		/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/gas-budget/v1.0/estates/{municipalityId}/{postalCode}/{addressKind}/{addressName}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Get all the estates for a whole address from Anidia CRM"
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "municipalityId"),
			@Parameter(in = ParameterIn.PATH, name = "postalCode"),
			@Parameter(in = ParameterIn.PATH, name = "addressKind"),
			@Parameter(in = ParameterIn.PATH, name = "addressName")
		}
	)
	@Path("/estates/{municipalityId}/{postalCode}/{addressKind}/{addressName}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Estate")})
	public Page<Estate> getEstatesAddressNamePage(
			@NotNull @Parameter(hidden = true) @PathParam("municipalityId")
				String municipalityId,
			@NotNull @Parameter(hidden = true) @PathParam("postalCode") String
				postalCode,
			@NotNull @Parameter(hidden = true) @PathParam("addressKind") String
				addressKind,
			@NotNull @Parameter(hidden = true) @PathParam("addressName") String
				addressName)
		throws Exception {

			SalesforceService salesforce = new SalesforceService();

		return Page.of(salesforce.getEstates(municipalityId, postalCode, addressKind, addressName));
	}


}