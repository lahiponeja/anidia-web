package com.liferay.sampleVue.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.sampleVue.dto.v1_0.Sample;
import com.liferay.sampleVue.resource.v1_0.SampleResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
public class Query {

	public static void setSampleResourceComponentServiceObjects(
		ComponentServiceObjects<SampleResource>
			sampleResourceComponentServiceObjects) {

		_sampleResourceComponentServiceObjects =
			sampleResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {sample(sampleId: ___){name, id}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Sample sample(@GraphQLName("sampleId") Integer sampleId)
		throws Exception {

		return _applyComponentServiceObjects(
			_sampleResourceComponentServiceObjects,
			this::_populateResourceContext,
			sampleResource -> sampleResource.getSample(sampleId));
	}

	@GraphQLName("SamplePage")
	public class SamplePage {

		public SamplePage(Page samplePage) {
			items = samplePage.getItems();
			page = samplePage.getPage();
			pageSize = samplePage.getPageSize();
			totalCount = samplePage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Sample> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(SampleResource sampleResource)
		throws Exception {

		sampleResource.setContextAcceptLanguage(_acceptLanguage);
		sampleResource.setContextCompany(_company);
		sampleResource.setContextHttpServletRequest(_httpServletRequest);
		sampleResource.setContextHttpServletResponse(_httpServletResponse);
		sampleResource.setContextUriInfo(_uriInfo);
		sampleResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<SampleResource>
		_sampleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}