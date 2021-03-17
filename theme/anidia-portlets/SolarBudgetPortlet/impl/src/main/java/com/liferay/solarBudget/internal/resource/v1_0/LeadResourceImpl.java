package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.dto.v1_0.*;
import com.liferay.solarBudget.internal.services.*;
import com.liferay.solarBudget.resource.v1_0.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/lead.properties",
	scope = ServiceScope.PROTOTYPE, service = LeadResource.class
)
public class LeadResourceImpl extends BaseLeadResourceImpl {

	@Override
	public Lead postLead(Lead lead) throws Exception {
		SalesforceService service = new SalesforceService();
		return service.createLead(lead);
	}
}