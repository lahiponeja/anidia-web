package FAQsModule.portlet;

import FAQsModule.constants.FAQsModulePortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

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
		"javax.portlet.display-name=FAQsModule",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FAQsModulePortletKeys.FAQSMODULE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class FAQsModulePortlet extends MVCPortlet {
	
	public static final String COLLECTION_NAME = "repertorio-faqs";
	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		long groupId = themeDisplay.getScopeGroupId();
		String language = themeDisplay.getLanguageId();

		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");
		String contentJson = "";
		
		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
		try {
			assetEntries = getEntriesList(groupId, COLLECTION_NAME);
			contentJson = toJsonString(assetEntries, language, searchTerm);
		} catch (DocumentException | org.json.JSONException | PortalException e) {
			e.printStackTrace();
		}

		renderRequest.setAttribute("usedSearchTerm", searchTerm);
        renderRequest.setAttribute("contentJson", contentJson);  
		super.doView(renderRequest, renderResponse);	
	}
	
	public List<AssetEntry> getEntriesList(long groupId, String assetListName) throws PortalException {
        AssetListEntry assetListEntry = AssetListEntryLocalServiceUtil.getAssetListEntry(groupId, assetListName);
        List<AssetEntry> entriesList = _assetListAssetEntryProvider.getAssetEntries(assetListEntry, 0);
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
