package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.EstateResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import com.liferay.solarBudget.internal.services.Geocode;
import com.liferay.solarBudget.dto.v1_0.Estate;
import com.liferay.portal.vulcan.pagination.Page;

import javax.validation.constraints.NotNull;
/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/estate.properties",
	scope = ServiceScope.PROTOTYPE, service = EstateResource.class
)
public class EstateResourceImpl extends BaseEstateResourceImpl {
	@Override
	public Page<Estate> getEstatesAddressPage(@NotNull String populationId, @NotNull String addressId) {
			Geocode geocode = new Geocode();
			return Page.of(geocode.getEstates(populationId, addressId));

			// return Page.of([new Estate()]);
	}
}