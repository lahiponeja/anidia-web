package com.liferay.sampleVue.internal.resource.v1_0;

import com.liferay.sampleVue.dto.v1_0.*;
import com.liferay.sampleVue.internal.services.*;
import com.liferay.sampleVue.resource.v1_0.*;
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