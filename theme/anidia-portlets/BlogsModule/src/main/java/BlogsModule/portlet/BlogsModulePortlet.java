package BlogsModule.portlet;

import BlogsModule.constants.BlogsModulePortletKeys;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.blogs.service.*;
import com.liferay.blogs.model.*;
import com.liferay.blogs.exception.*;
import com.liferay.blogs.configuration.*;
import com.liferay.blogs.constants.*;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;

import com.liferay.portal.kernel.xml.*;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.asset.list.asset.entry.provider.AssetListAssetEntryProvider;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalServiceUtil;


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
	
	public static final String COLLECTION_NAME = "repertorio-faqs";
	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@ProcessAction(name = "prueba")
	public void prueba(ActionRequest request, ActionResponse response) {
		request.setAttribute("mensaje","HOla "+ParamUtil.getString(request, "nombreEscritor"));
	}

	@ProcessAction(name = "prueba2")
	public void prueba2(ActionRequest request, ActionResponse response) {
		request.setAttribute("mensaje2","HOla pepillllo");
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");

		int entriesCount = BlogsEntryLocalServiceUtil.getBlogsEntriesCount();
		List<BlogsEntry> blogEntries = BlogsEntryLocalServiceUtil.getBlogsEntries(0,entriesCount);
		//List<BlogsEntry> blogEntries = getBlogsByTag(themeDisplay,"etiqueta-primavera",0,entriesCount)

		JSONObject jsonReturn = new JSONObject();
		blogEntries.forEach(entry -> {
			JSONObject jsonEntry = new JSONObject();
			jsonEntry.put("title",entry.getTitle());
			jsonEntry.put("subtitle",entry.getSubtitle());
			jsonEntry.put("date",entry.getDisplayDate());
			jsonEntry.put("content",entry.getContent());

			JSONArray tags = new JSONArray();
			AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(),entry.getEntryId()).forEach(tag -> {
				tags.put(tag.getName());
			});
			jsonEntry.put("tags",tags);


			try {
				jsonEntry.put("image",entry.getCoverImageURL(themeDisplay));
			} catch (PortalException e) {
				e.printStackTrace();
			}

			jsonReturn.append("data",jsonEntry);
		});

		renderRequest.setAttribute("usedSearchTerm", searchTerm);
        renderRequest.setAttribute("contentJson", jsonReturn.toString());
		super.doView(renderRequest, renderResponse);
	}


	private List<BlogsEntry> getBlogsByTag(ThemeDisplay themeDisplay, String tagName, int start, int end)
			throws PortalException {
		 AssetTag assetTag = AssetTagLocalServiceUtil.getTag(themeDisplay.getScopeGroupId(), tagName);

		 AssetEntryQuery query = new AssetEntryQuery();
		 query.setAnyTagIds(new long[]{assetTag.getTagId()});
		 query.setClassName(BlogsEntry.class.getName());
		 query.setStart(start);
		 query.setEnd(end);

		 List<BlogsEntry> blogEntries = AssetEntryLocalServiceUtil.getEntries(query).stream().map(i -> {
		 try {
		 	return BlogsEntryLocalServiceUtil.getBlogsEntry(i.getClassPK());
		 } catch (PortalException e) {
		 	e.printStackTrace();
		 }
		 	return null;
		 }).collect(Collectors.toList());

		 return blogEntries;
	}
	
	public List<AssetEntry> getEntriesList(long groupId, String assetListName) throws PortalException {
        AssetListEntry assetListEntry = AssetListEntryLocalServiceUtil.getAssetListEntry(groupId, assetListName);
        List<AssetEntry> entriesList = _assetListAssetEntryProvider.getAssetEntries(assetListEntry, 0);

        BlogsEntry aa = BlogsEntryLocalServiceUtil.getBlogsEntry(37507);
        return entriesList;
	} 

	public String toJsonString(List<AssetEntry> entries, String language, String searchTerm) throws JSONException, DocumentException, org.json.JSONException {
		
		LinkedHashSet<String> setOfCategories = new LinkedHashSet<String>();
		JSONObject jsonFullData = new JSONObject();	
		JSONArray faqsData = new JSONArray();
		
		for (AssetEntry entry : entries) {
			
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(entry.getClassPK());
			
			if(!journalArticle.isExpired() && !journalArticle.isInTrash() && !journalArticle.isDraft()) { 
				
				List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(entry.getEntryId());
				
				Document document = SAXReaderUtil.read(journalArticle.getContentByLocale(language));		 
				String question = getStructureFieldContent("Pregunta", document);	 
				String answer = getStructureFieldContent("Respuesta", document);	
				
				if (searchFilter(searchTerm, question, answer)) {
					JSONObject jsonAssetFields =  new JSONObject();
					List<String> assetCategoryNames = new ArrayList<String>();
	
					for (AssetCategory category:assetCategories) {
						setOfCategories.add(category.getTitle(language));
						assetCategoryNames.add(category.getTitle(language));
					}
					jsonAssetFields.put("question", question);
					jsonAssetFields.put("answer", answer);
					jsonAssetFields.put("categories", assetCategoryNames);	
					jsonFullData.append("data", jsonAssetFields);
					faqsData.put(jsonAssetFields);
				 }
			}
		}
		jsonFullData.put("foundCategories", setOfCategories);
		jsonFullData.put("data",faqsData);
		return jsonFullData.toString();
	}
	
	public String getStructureFieldContent(String fieldName, Document document) {
		Node questionNode = document.selectSingleNode("/root/dynamic-element[@name='" + fieldName + "']/dynamic-content");
		String content = questionNode.getText();
		return content;
	}
	
	public boolean searchFilter(String searchTerm, String question, String answer) {
		return searchTerm.isEmpty() 
				||question.toLowerCase().contains(searchTerm.toLowerCase()) 
			    || answer.toLowerCase().contains(searchTerm.toLowerCase());
	}

}
