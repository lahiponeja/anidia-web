package com.liferay.gasBudget.internal.resource.v1_0;

import com.liferay.gasBudget.resource.v1_0.GasBudgetResource;
import com.liferay.gasBudget.dto.v1_0.GasBudget;
import com.liferay.gasBudget.dto.v1_0.GasBudgetRequest;
import com.liferay.gasBudget.internal.services.Calculator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/gas-budget.properties", scope = ServiceScope.PROTOTYPE, service = GasBudgetResource.class)
public class GasBudgetResourceImpl extends BaseGasBudgetResourceImpl {

	@Override
	public GasBudget postGasBudget(GasBudgetRequest gasBudgetRequest) throws Exception {
		Calculator calculator = new Calculator();
		return calculator.createGasBudget(gasBudgetRequest);
	}


}