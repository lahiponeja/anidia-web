package com.liferay.gasComparator.resource.v1_0;

import com.liferay.gasComparator.dto.v1_0.GasCalculatedConsumption;
import com.liferay.gasComparator.dto.v1_0.GasConsumptionComparison;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/gas-comparator/v1.0
 *
 * @author David Brenes
 * @generated
 */
@Generated("")
@ProviderType
public interface GasConsumptionComparisonResource {

	public GasConsumptionComparison postSavingsByUse(
			GasCalculatedConsumption gasCalculatedConsumption)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(User contextUser);

}