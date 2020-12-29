package com.liferay.solarBudget.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author David Brenes
 * @generated
 */
@Component(
	property = {
		"osgi.jaxrs.application.base=/solar-budget",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=Liferay.SolarBudget"
	},
	service = Application.class
)
@Generated("")
public class SolarBudgetApplication extends Application {
}