package com.liferay.gasBudget.internal.resource.v1_0;

import com.liferay.gasBudget.dto.v1_0.*;
import com.liferay.gasBudget.internal.services.*;
import com.liferay.gasBudget.resource.v1_0.*;
import org.osgi.service.component.annotations.*;

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