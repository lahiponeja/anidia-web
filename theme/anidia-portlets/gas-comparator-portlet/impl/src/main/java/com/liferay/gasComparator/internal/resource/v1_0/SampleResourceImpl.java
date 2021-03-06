package com.liferay.gasComparator.internal.resource.v1_0;

import com.liferay.gasComparator.resource.v1_0.SampleResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/sample.properties",
	scope = ServiceScope.PROTOTYPE, service = SampleResource.class
)
public class SampleResourceImpl extends BaseSampleResourceImpl {
}