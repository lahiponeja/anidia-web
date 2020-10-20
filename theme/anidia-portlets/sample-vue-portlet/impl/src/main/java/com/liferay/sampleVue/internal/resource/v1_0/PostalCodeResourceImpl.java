package com.liferay.sampleVue.internal.resource.v1_0;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.sampleVue.resource.v1_0.PostalCodeResource;
import com.liferay.sampleVue.dto.v1_0.PostalCode;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/postal-code.properties",
	scope = ServiceScope.PROTOTYPE, service = PostalCodeResource.class
)
public class PostalCodeResourceImpl extends BasePostalCodeResourceImpl {
		/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/sample-vue/v1.0/postal-codes'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Get all the postal codes from the Anidia database"
	)
	@Path("/postal-codes")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "PostalCode")})
	public Page<PostalCode> getPostalCodesPage() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("postal-codes.csv");
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<PostalCode> postalCodes = new ArrayList<PostalCode>();

		while ((line = csvFile.readLine()) != null) {
			// each lines has only the postal codes
			String[] columns = line.split(";", -1);
			PostalCode postalCode = new PostalCode();
			postalCode.setPostalCode(columns[0]);
			postalCodes.add(postalCode);
		}
		return Page.of(postalCodes);
	}
}