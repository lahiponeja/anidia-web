package com.liferay.sampleVue.internal.resource.v1_0;

import com.liferay.sampleVue.resource.v1_0.GasBudgetResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/gas-budget.properties",
	scope = ServiceScope.PROTOTYPE, service = GasBudgetResource.class
)
public class GasBudgetResourceImpl extends BaseGasBudgetResourceImpl {
}