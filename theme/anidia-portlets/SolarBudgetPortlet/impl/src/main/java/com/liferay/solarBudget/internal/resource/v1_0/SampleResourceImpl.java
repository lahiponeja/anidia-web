package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.SampleResource;
import com.liferay.solarBudget.dto.v1_0.Sample;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/sample.properties",
	scope = ServiceScope.PROTOTYPE, service = SampleResource.class
)
public class SampleResourceImpl extends BaseSampleResourceImpl {
	@Override
	@GET
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "sampleId")})
	@Path("/samples/{sampleId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Sample")})
	public Sample getSample(@NotNull @Parameter(hidden = true) @PathParam("sampleId") Integer
	sampleId) {
		Sample sample = new Sample();
		System.out.println("Devuelvo   " + sample);
		return sample;
	}
}