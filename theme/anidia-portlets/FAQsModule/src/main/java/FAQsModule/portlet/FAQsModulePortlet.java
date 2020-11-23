package FAQsModule.portlet;

import FAQsModule.constants.FAQsModulePortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;

import com.liferay.portal.kernel.xml.*;

import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
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
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<JournalArticle> journalArticles =  new ArrayList<JournalArticle>();
        String structureName = "FAQ";
        String structureKey = getStructureKey(structureName);   
        
		long groupId = themeDisplay.getScopeGroupId();
		String language = themeDisplay.getLanguageId();

		journalArticles = getLatestVersionArticle(JournalArticleLocalServiceUtil.getStructureArticles(groupId, structureKey));
		
		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");
		String contentJson = "";
		try {
			contentJson = toJsonString(journalArticles, language, searchTerm);
		} catch (JSONException | DocumentException | org.json.JSONException e) {
			e.printStackTrace();
		}

		renderRequest.setAttribute("usedSearchTerm", searchTerm);
        renderRequest.setAttribute("contentJson", contentJson);

		super.doView(renderRequest, renderResponse);
	}
	
	
	public List<JournalArticle> getLatestVersionArticle(List<JournalArticle> totalArticles) {
		List<JournalArticle> journalList = new ArrayList<JournalArticle>();
		JournalArticle latestArticle ;
		for (JournalArticle journalArticle : totalArticles) {
			try {
				 latestArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticle.getResourcePrimKey());
				if (journalList.contains(latestArticle)) {
					continue;
				} else {
					journalList.add(latestArticle);
				}
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
		}
		return journalList;
	}
	

	public String getStructureKey(String strucName) throws PortletException {
		DynamicQuery queryForStructure =DDMStructureLocalServiceUtil.dynamicQuery().add(PropertyFactoryUtil
				.forName("name").like("%" + strucName + "%"));
		List structures = DDMStructureLocalServiceUtil.dynamicQuery(queryForStructure, 0, 1); 
		DDMStructure specifiedStructure = null;
		if(structures != null && structures.size() != 0){
			specifiedStructure = (DDMStructure) structures.get(0);		
			return specifiedStructure.getStructureKey();
			
		}else{
			System.out.println(strucName +" structure not found");
			throw new PortletException("FAQsModule: " + strucName + " structure not found");
		}	
	}
	
	public String toJsonString(List<JournalArticle> articles, String language, String searchTerm) throws JSONException, DocumentException, org.json.JSONException {
		LinkedHashSet<String> setOfCategories = new LinkedHashSet<String>();

		JSONObject jsonFullData = new JSONObject();	
		JSONObject jsonAssetCategories =  new JSONObject();
		JSONArray faqsData = new JSONArray();
		
		sortByPriority(articles);
		
		for (JournalArticle entry : articles) {
			if(!entry.isExpired() && !entry.isInTrash() && !entry.isDraft()) { 
				
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle", entry.getResourcePrimKey());				
				List<AssetCategory> assetCategories =  new ArrayList<AssetCategory>();
				assetCategories= AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(assetEntry.getEntryId());
				
				Document document = SAXReaderUtil.read(entry.getContentByLocale(language));		 
				Node questionNode = document.selectSingleNode("/root/dynamic-element[@name='Pregunta']/dynamic-content");
				String question = questionNode.getText();	 
				Node answerNode = document.selectSingleNode("/root/dynamic-element[@name='Respuesta']/dynamic-content");
				String answer = answerNode.getText();
				
				if (searchTerm.isEmpty() || 
					question.toLowerCase().contains(searchTerm.toLowerCase())||
					answer.toLowerCase().contains(searchTerm.toLowerCase())){
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
	
	public void sortByPriority(List<JournalArticle> articless){
		Collections.sort(articless,new Comparator<JournalArticle>(){
            public int compare(JournalArticle o1, JournalArticle o2)
            {
            	double priorityO1 = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle",o1.getResourcePrimKey()).getPriority();
            	double priorityO2 = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle",o2.getResourcePrimKey()).getPriority();
                if (priorityO1 == priorityO2){
                    return 0;
                }
                else if (priorityO1 < priorityO2){
                    return -1;
                }
                return 1;
            }
        });
	}
}
