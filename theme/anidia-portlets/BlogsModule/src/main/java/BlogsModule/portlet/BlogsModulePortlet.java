package BlogsModule.portlet;

import BlogsModule.constants.BlogsModulePortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.FuzzyQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/*    
 * @author Fran Serrano
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=anidia",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=BlogsModule",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BlogsModulePortletKeys.BLOGSMODULE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class BlogsModulePortlet extends MVCPortlet {

	@Reference
	protected Queries queries;

	@Reference
	protected Searcher searcher;

	@Reference
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<BlogsEntry> blogEntries;
		int pageSize = BlogsModulePortletKeys.PAGE_SIZE;

		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");
		String searchTag = ParamUtil.getString(renderRequest,"searchTag");
		int page = ParamUtil.getInteger(renderRequest,"page") == 0 ? 1 : ParamUtil.getInteger(renderRequest,"page");

		String searchCategory = "";
		String url = PortalUtil.getHttpServletRequest(renderRequest).getRequestURI();
		
		if (url.contains(BlogsModulePortletKeys.GAS_CATEGORY)) {
			searchCategory = BlogsModulePortletKeys.GAS_CATEGORY;
		} else if (url.contains(BlogsModulePortletKeys.SOLAR_CATEGORY)) {
			searchCategory = BlogsModulePortletKeys.SOLAR_CATEGORY;
		} 

		if (searchTerm!= null && !searchTerm.equals("")) {
			blogEntries = getBlogsBySearch(themeDisplay, searchCategory, searchTerm);
		} else  {
			blogEntries = getBlogsByTagAndCategory(themeDisplay, searchCategory,searchTag,(page - 1) * pageSize,pageSize * page);
		}

		JSONObject jsonReturn = new JSONObject();

		blogEntries.forEach(entry -> {
			if(!entry.isExpired() && !entry.isInTrash() && !entry.isDraft()) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("title",entry.getTitle());
				jsonEntry.put("subtitle",entry.getSubtitle());
				jsonEntry.put("date",entry.getDisplayDate());

				byte[] utf8;
				try {
					utf8 = entry.getContent().getBytes("UTF8");
					jsonEntry.put("content",new String(Base64.getEncoder().encode(utf8)));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				jsonEntry.put("url",entry.getUrlTitle());

				JSONArray tags = new JSONArray();
				AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(),
						entry.getEntryId()).forEach(tag -> tags.put(tag.getName()));
				jsonEntry.put("tags",tags);

				try {
					jsonEntry.put("image",entry.getCoverImageURL(themeDisplay));
				} catch (PortalException e) {
					e.printStackTrace();
				}

				jsonReturn.append("data",jsonEntry);
			}
		});

		renderRequest.setAttribute("searchTerm", searchTerm);
        renderRequest.setAttribute("searchTag",searchTag);
		renderRequest.setAttribute("page",page);
		renderRequest.setAttribute("contentJson", jsonReturn.toString());

		super.doView(renderRequest, renderResponse);
	}

	private long getCategoryId(String categoryName) {
		AssetCategory assetCategory;
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(AssetCategory.class,
				PortalClassLoaderUtil.getClassLoader())
				.add(PropertyFactoryUtil.forName("name").eq(categoryName));
		assetCategory = (AssetCategory) AssetCategoryLocalServiceUtil.dynamicQuery(query, 0, 1).get(0);
		return assetCategory.getCategoryId();
	}

	private long getAssetTagId(ThemeDisplay themeDisplay, String tagName) {
		AssetTag assetTag;
		try {
			assetTag = AssetTagLocalServiceUtil.getTag(themeDisplay.getScopeGroupId(), tagName);
		} catch (PortalException e) {
			return -1;
		}
		return assetTag.getTagId();
	}


	private List<BlogsEntry> getBlogsByTagAndCategory(ThemeDisplay themeDisplay, String categoryName, String tagName, int start, int end) {
		List<BlogsEntry> blogsEntries = new ArrayList<>();

		AssetEntryQuery query = new AssetEntryQuery();
		if (categoryName != null && !categoryName.equals("")) {
			query.setAnyCategoryIds(new long[]{getCategoryId(categoryName)});
		}
		if (tagName != null && !tagName.equals("")) {
			query.setAnyTagIds(new long[]{getAssetTagId(themeDisplay, tagName)});
		}
		query.setClassName(BlogsEntry.class.getName());
		query.setStart(start);
		query.setEnd(end);

		return AssetEntryLocalServiceUtil.getEntries(query).stream().map(i -> {
			try {
				return BlogsEntryLocalServiceUtil.getBlogsEntry(i.getClassPK());
			} catch (PortalException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

	}

	private List<BlogsEntry> getBlogsBySearch(ThemeDisplay themeDisplay, String categoryName, String searchTerm) {
		List<BlogsEntry> blogsEntries = new ArrayList<>();
		FuzzyQuery contentQuery = queries.fuzzy(Field.CONTENT,searchTerm);
		TermQuery categoryQuery = queries.term(Field.ASSET_CATEGORY_IDS, getCategoryId(categoryName));

		BooleanQuery booleanQuery = queries.booleanQuery()
				.addMustQueryClauses(contentQuery,categoryQuery);

		SearchRequestBuilder searchRequestBuilder =	searchRequestBuilderFactory.builder();
		searchRequestBuilder.emptySearchEnabled(true);
		searchRequestBuilder.withSearchContext(
				searchContext -> {
					searchContext.setCompanyId(themeDisplay.getCompanyId());
					searchContext.setKeywords(searchTerm);
				});

		SearchRequest searchRequest = searchRequestBuilder.query(booleanQuery).build();
		SearchResponse searchResponse = searcher.search(searchRequest);

		searchResponse.getSearchHits().getSearchHits().forEach(searchHit -> {
			Document doc = searchHit.getDocument();
			if (doc.getString("entryClassName").equals("com.liferay.blogs.model.BlogsEntry")) {
				try {
					blogsEntries.add(BlogsEntryLocalServiceUtil.getBlogsEntry(doc.getInteger("entryClassPK")));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		});

		return blogsEntries;
	}

}
