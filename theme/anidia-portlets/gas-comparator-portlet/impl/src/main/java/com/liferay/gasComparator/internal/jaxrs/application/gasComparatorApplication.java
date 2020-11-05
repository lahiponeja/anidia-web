package com.liferay.gasComparator.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author David Brenes
 * @generated
 */
@Component(
	property = {
		"osgi.jaxrs.application.base=/gas-comparator",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=Liferay.gasComparator"
	},
	service = Application.class
)
@Generated("")
public class gasComparatorApplication extends Application {
}