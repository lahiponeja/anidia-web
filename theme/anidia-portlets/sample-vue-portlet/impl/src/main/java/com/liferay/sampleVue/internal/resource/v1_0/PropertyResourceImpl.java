package com.liferay.sampleVue.internal.resource.v1_0;

import com.liferay.sampleVue.resource.v1_0.PropertyResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/property.properties",
	scope = ServiceScope.PROTOTYPE, service = PropertyResource.class
)
public class PropertyResourceImpl extends BasePropertyResourceImpl {
}