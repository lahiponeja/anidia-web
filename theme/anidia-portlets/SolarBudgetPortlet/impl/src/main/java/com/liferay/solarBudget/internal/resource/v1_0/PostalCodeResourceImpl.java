package com.liferay.solarBudget.internal.resource.v1_0;
import javax.validation.constraints.NotNull;
import com.liferay.solarBudget.resource.v1_0.PostalCodeResource;
import com.liferay.portal.vulcan.pagination.Page;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import com.liferay.solarBudget.internal.services.Geocode;
import com.liferay.solarBudget.dto.v1_0.PostalCode;
/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/postal-code.properties",
	scope = ServiceScope.PROTOTYPE, service = PostalCodeResource.class
)
public class PostalCodeResourceImpl extends BasePostalCodeResourceImpl {

	@Override
	public Page<PostalCode> getMunicipalityPostalCodePage(@NotNull String postalCode){
		Geocode geocode = new Geocode();
		return geocode.getMunicipalities(postalCode);

	}


}