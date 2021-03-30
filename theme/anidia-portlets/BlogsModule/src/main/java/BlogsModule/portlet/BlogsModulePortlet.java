package BlogsModule.portlet;

import BlogsModule.constants.BlogsModulePortletKeys;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.FuzzyQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*    
 * @author danieldelapena
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
		int pageSize = 7;

		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");
		String searchTag = ParamUtil.getString(renderRequest,"searchTag");
		int page = ParamUtil.getInteger(renderRequest,"page");

		if ( searchTag!= null && !searchTag.equals("")) {
			blogEntries = getBlogsByTag(themeDisplay,searchTag,(page - 1) * pageSize,pageSize * page);
		} else if (searchTerm!= null && !searchTerm.equals("")) {
			blogEntries = getBlogsBySearch(themeDisplay, searchTerm);
		} else {
			blogEntries = BlogsEntryLocalServiceUtil.getBlogsEntries((page - 1) * pageSize,pageSize * page);
		}

		JSONObject jsonReturn = new JSONObject();
		jsonReturn.append("data", new JSONObject());

		blogEntries.forEach(entry -> {
			if(!entry.isExpired() && !entry.isInTrash() && !entry.isDraft()) {
				JSONObject jsonEntry = new JSONObject();
				jsonEntry.put("title",entry.getTitle());
				jsonEntry.put("subtitle",entry.getSubtitle());
				jsonEntry.put("date",entry.getDisplayDate());
				jsonEntry.put("content",entry.getContent());
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


	private List<BlogsEntry> getBlogsByTag(ThemeDisplay themeDisplay, String tagName, int start, int end) {
		List<BlogsEntry> blogsEntries = new ArrayList<>();
		AssetTag assetTag;
		try {
			assetTag = AssetTagLocalServiceUtil.getTag(themeDisplay.getScopeGroupId(), tagName);
		} catch (PortalException e) {
			return blogsEntries;
		}

		AssetEntryQuery query = new AssetEntryQuery();
		query.setAnyTagIds(new long[]{assetTag.getTagId()});
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

	private List<BlogsEntry> getBlogsBySearch(ThemeDisplay themeDisplay, String searchTerm) {
		List<BlogsEntry> blogsEntries = new ArrayList<>();
		FuzzyQuery contentQuery = queries.fuzzy("content",searchTerm);
		BooleanQuery booleanQuery = queries.booleanQuery().addMustQueryClauses(contentQuery);

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
