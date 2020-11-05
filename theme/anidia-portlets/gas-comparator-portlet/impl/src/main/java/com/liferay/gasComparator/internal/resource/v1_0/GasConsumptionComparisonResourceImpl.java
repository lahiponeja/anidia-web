package com.liferay.gasComparator.internal.resource.v1_0;

import com.liferay.gasComparator.dto.v1_0.*;
import com.liferay.gasComparator.internal.services.*;
import com.liferay.gasComparator.resource.v1_0.*;
import org.osgi.service.component.annotations.*;

/**
 * @author David Brenes
 */
@Component(
    properties = "OSGI-INF/liferay/rest/v1_0/gas-consumption-comparison.properties",
    scope = ServiceScope.PROTOTYPE,
    service = GasConsumptionComparisonResource.class
)
public class GasConsumptionComparisonResourceImpl
    extends BaseGasConsumptionComparisonResourceImpl {

    @Override
    public GasConsumptionComparison postSavingsByConsumption(
        GasCalculatedConsumption gasCalculatedConsumption)
        throws Exception {
        GasComparator gasComparator = new GasComparator();
        return gasComparator.compareByDirectConsumption(gasCalculatedConsumption);
    }

    public GasConsumptionComparison postSavingsByUse(
        GasConsumptionByUse gasConsumptionByUse)
        throws Exception {
        GasComparator gasComparator = new GasComparator();
        return gasComparator.compareByUse(gasConsumptionByUse);
    }
}