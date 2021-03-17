package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.PropertyResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import com.liferay.solarBudget.internal.services.Geocode;
import com.liferay.solarBudget.dto.v1_0.Property;
import com.liferay.portal.vulcan.pagination.Page;

import javax.validation.constraints.NotNull;
/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/property.properties",
	scope = ServiceScope.PROTOTYPE, service = PropertyResource.class
)
public class PropertyResourceImpl extends BasePropertyResourceImpl {

	@Override
	public Page<Property> getPropertiesPage(@NotNull String postalCode, @NotNull String municipalityId,
		@NotNull String addressId, @NotNull String portalNumber) {
			Geocode geocode = new Geocode();
			return Page.of(geocode.getProperties(postalCode, municipalityId, addressId, portalNumber));
	}
}