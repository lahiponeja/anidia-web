package com.liferay.solarBudget.internal.resource.v1_0;

import com.liferay.solarBudget.resource.v1_0.PostalCodeResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/postal-code.properties",
	scope = ServiceScope.PROTOTYPE, service = PostalCodeResource.class
)
public class PostalCodeResourceImpl extends BasePostalCodeResourceImpl {
}