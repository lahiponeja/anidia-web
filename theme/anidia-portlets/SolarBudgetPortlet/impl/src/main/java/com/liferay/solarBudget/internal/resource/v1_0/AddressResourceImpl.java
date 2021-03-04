package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.AddressResource;
import com.liferay.solarBudget.internal.services.FakeGeocode;
import com.liferay.solarBudget.internal.services.Geocode;
import com.liferay.solarBudget.dto.v1_0.Address;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import javax.validation.constraints.NotNull;
import com.liferay.portal.vulcan.pagination.Page;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/address.properties",
	scope = ServiceScope.PROTOTYPE, service = AddressResource.class
)
public class AddressResourceImpl extends BaseAddressResourceImpl {

	public Page<Address> getAddressesPostalCodePage(@NotNull String populationId, @NotNull String postalCode) {
		// Faking estates response since CTI is down
		// Geocode geocode = new Geocode();
		FakeGeocode geocode = new FakeGeocode();
		return Page.of(geocode.getAddresses(populationId, postalCode));
	}
}