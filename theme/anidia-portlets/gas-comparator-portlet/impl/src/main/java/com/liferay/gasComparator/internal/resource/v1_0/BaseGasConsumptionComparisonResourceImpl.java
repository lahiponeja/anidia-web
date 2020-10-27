package com.liferay.gasComparator.internal.resource.v1_0;

import com.liferay.gasComparator.dto.v1_0.*;
import com.liferay.gasComparator.resource.v1_0.*;
import com.liferay.petra.function.*;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.vulcan.accept.language.*;
import com.liferay.portal.vulcan.util.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import java.util.*;
import javax.annotation.*;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseGasConsumptionComparisonResourceImpl
	implements GasConsumptionComparisonResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/gas-comparator/v1.0/savings-by-consumption' -d $'{"acsUse": ___, "electricityConsumption": ___, "energyType": ___, "heatingUse": ___, "kitchenUse": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/xml")
	@Operation(
		description = "Calculates the savings when the user knows the amount of energy spent,"
	)
	@POST
	@Path("/savings-by-consumption")
	@Produces("application/xml")
	@Tags(value = {})
	public GasConsumptionComparison postSavingsByConsumption(
			GasCalculatedConsumption gasCalculatedConsumption)
		throws Exception {

		return new GasConsumptionComparison();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/gas-comparator/v1.0/savings-by-use' -d $'{"acsIndividual": ___, "acsUse": ___, "heatingIndividual": ___, "heatingUse": ___, "kitchenUse": ___, "lastFloor": ___, "numberOfPeople": ___, "province": ___, "singleFamilyHouse": ___, "surfaceHouse": ___, "weeklyKitchenUse": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/xml")
	@Operation(
		description = "Calculates the savings inferred from the user habits"
	)
	@POST
	@Path("/savings-by-use")
	@Produces("application/xml")
	@Tags(value = {})
	public GasConsumptionComparison postSavingsByUse(
			GasConsumptionByUse gasConsumptionByUse)
		throws Exception {

		return new GasConsumptionComparison();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(User contextUser) {
		this.contextUser = contextUser;
	}

	protected void preparePatch(
		GasConsumptionComparison gasConsumptionComparison,
		GasConsumptionComparison existingGasConsumptionComparison) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected UriInfo contextUriInfo;
	protected User contextUser;

}