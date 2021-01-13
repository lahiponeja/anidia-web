package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.InstallerResource;
import javax.ws.rs.QueryParam;
import javax.validation.constraints.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import com.liferay.solarBudget.dto.v1_0.Installer;
import com.liferay.solarBudget.internal.services.Availability;
/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/installer.properties",
	scope = ServiceScope.PROTOTYPE, service = InstallerResource.class
)
public class InstallerResourceImpl extends BaseInstallerResourceImpl {

	@Override
	public Installer getAvailability(@NotNull String postalCode, @NotNull String municipalityId) {
		Availability availability = new Availability();
		return availability.checkAvailability(postalCode, municipalityId);
	}
}