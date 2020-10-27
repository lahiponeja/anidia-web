package com.liferay.gasBudget.internal.resource.v1_0;

import javax.validation.constraints.NotNull;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.gasBudget.internal.services.SalesforceService;
import com.liferay.gasBudget.resource.v1_0.PropertyResource;
import com.liferay.gasBudget.dto.v1_0.Property;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/property.properties", scope = ServiceScope.PROTOTYPE, service = PropertyResource.class)
public class PropertyResourceImpl extends BasePropertyResourceImpl {

	@Override
	public Page<Property> getPropertiesGatePage(@NotNull String gateId) throws Exception {
		SalesforceService salesforce = new SalesforceService();

		return Page.of(salesforce.getProperties(gateId));
	}

}