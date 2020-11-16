package FAQsModule.portlet;

import FAQsModule.constants.FAQsModulePortletKeys;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=APCustom",
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
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
        		WebKeys.THEME_DISPLAY);
		
		List<JournalArticle> journalArticles =  new ArrayList<JournalArticle>();
        String structureName = "FAQ";
        String structureKey = getStructureKey(structureName);   
        
		long groupId = themeDisplay.getScopeGroupId();
		String language = themeDisplay.getLanguageId();

		journalArticles = getLatestVersionArticle(JournalArticleLocalServiceUtil.getStructureArticles(groupId, structureKey));
		
		String searchTerm = ParamUtil.getString(renderRequest, "searchTerm");
		String contentJson = "";
		try {
			contentJson = toJsonString(journalArticles, language,searchTerm);
		} catch (JSONException | DocumentException e) {
			e.printStackTrace();
		}

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
	
	public String toJsonString(List<JournalArticle> Articles, String language, String searchTerm)throws JSONException, DocumentException {
		Set<String> setOfCategories = new HashSet<String>();
		String jsonContent = "";
		for (JournalArticle entry : Articles) {
			if(!entry.isExpired() && !entry.isInTrash()) { 
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle",entry.getResourcePrimKey());				
				List<AssetCategory> assetCategories =  new ArrayList<AssetCategory>();
				assetCategories= AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(assetEntry.getEntryId());
				 
				Document document = SAXReaderUtil.read(entry.getContentByLocale(language));		 
				Node QuestionNode = document.selectSingleNode("/root/dynamic-element[@name='Question']/dynamic-content");
				String question = QuestionNode.getText();	 
				Node AnswerNode = document.selectSingleNode("/root/dynamic-element[@name='Answer']/dynamic-content");
				String answer = AnswerNode.getText();
				
				if (searchTerm.isEmpty() || 
					question.toLowerCase().contains(searchTerm.toLowerCase())||
					answer.toLowerCase().contains(searchTerm.toLowerCase())){
					jsonContent = jsonContent.concat("{ \"question\": \"" + question + "\", \"answer\": \"" + answer + "\", \"Categories\": [");
				 
					for (AssetCategory category:assetCategories) {
						jsonContent = jsonContent.concat("\"" + category.getTitle(language) + "\", ");
						setOfCategories.add(category.getTitle(language));
						 }
					jsonContent = jsonContent.concat("]}, ");
				 }
			}
		}
		jsonContent = ("{ \"data\": [" + jsonContent + "] , \"foundCategories\": " + setOfCategories.toString()+"}");
		return (jsonContent);
	}
	
}

