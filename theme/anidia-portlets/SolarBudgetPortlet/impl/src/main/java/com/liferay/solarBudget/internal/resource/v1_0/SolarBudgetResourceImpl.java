package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.SolarBudgetResource;
import com.liferay.solarBudget.dto.v1_0.SolarBudgetRequest;
import com.liferay.solarBudget.dto.v1_0.SolarBudget;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/solar-budget.properties",
	scope = ServiceScope.PROTOTYPE, service = SolarBudgetResource.class
)
public class SolarBudgetResourceImpl extends BaseSolarBudgetResourceImpl {

	@Override
	public SolarBudget postSolarBudget(SolarBudgetRequest solarBudgetRequest)throws Exception {
		return new SolarBudget();
	}
}