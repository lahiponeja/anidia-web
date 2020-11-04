package APCustom.portlet;

import APCustom.constants.APCustomPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Field;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
		"javax.portlet.name=" + APCustomPortletKeys.APCUSTOM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
//TO DO: Remove unused imports
public class APCustomPortlet extends MVCPortlet {
	Set<String> selectedCategories = new HashSet<String>();

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        String contentJson = ParamUtil.get(renderRequest, "contentJson", "");
        renderRequest.setAttribute("contentJson", contentJson);
        
        String setOfCategories = ParamUtil.get(renderRequest, "setOfCategories", "");
        renderRequest.setAttribute("setOfCategories", setOfCategories);
		super.doView(renderRequest, renderResponse);
	}
	
	@ProcessAction(name="actionMethod1")
	public void actionMethod(ActionRequest request, ActionResponse response)
			throws IOException, PortletException, PortalException, SystemException, DocumentException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
        		WebKeys.THEME_DISPLAY);
		
		List<JournalArticle> journalArticles =  new ArrayList<JournalArticle>();
        String structureName = "FAQ";
        String structureKey = getStructureKey(structureName);  
        
		long groupId = themeDisplay.getScopeGroupId();
		String languaje = themeDisplay.getLanguageId();

		journalArticles = getLatestVersionArticle(JournalArticleLocalServiceUtil.getStructureArticles(groupId, structureKey));
		
		
		String contentsJson = toJsonString(journalArticles, languaje);
		Set<String> setOfCategories = getSetOfCategories(journalArticles, languaje);
		
		response.getRenderParameters().setValue("setOfCategories", setOfCategories.toString());
		response.getRenderParameters().setValue("contentJson", contentsJson);
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
	
	public Set<String> getSetOfCategories(List<JournalArticle> Articles, String Language){
		Set<String> setOfCategories = new HashSet<String>();
		
		for (JournalArticle entry : Articles) {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle",entry.getResourcePrimKey());				
			List<AssetCategory> assetCategories =  new ArrayList<AssetCategory>();
			assetCategories= AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(assetEntry.getEntryId());
			 for (AssetCategory category:assetCategories) {
				 setOfCategories.add(category.getTitle(Language));
			 }
		}
		return setOfCategories;
		
	}

	public String getStructureKey(String strucName) {
		DynamicQuery queryForStructure =DDMStructureLocalServiceUtil.dynamicQuery().add(PropertyFactoryUtil
				.forName("name").like("%" + strucName + "%"));
		List structures = DDMStructureLocalServiceUtil.dynamicQuery(queryForStructure, 0, 1); 
		DDMStructure specifiedStructure = null;
		if(structures != null && structures.size() != 0){
			specifiedStructure = (DDMStructure) structures.get(0);		
			return specifiedStructure.getStructureKey();
			
		}else{
			System.out.println(strucName +" structure not found");
			return null;
		}	
	}
	
	public String toJsonString(List<JournalArticle> Articles, String language)throws JSONException, DocumentException {
		String json = "";
		for (JournalArticle entry : Articles) {
			if(!entry.isExpired() && !entry.isInTrash()) { 

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry("com.liferay.journal.model.JournalArticle",entry.getResourcePrimKey());				
			List<AssetCategory> assetCategories =  new ArrayList<AssetCategory>();
			assetCategories= AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(assetEntry.getEntryId());
			 

			 Document document = SAXReaderUtil.read(entry.getContentByLocale(language));		 
			 Node QuestionNode = document.selectSingleNode("/root/dynamic-element[@name='Question']/dynamic-content");
			 String Question = QuestionNode.getText();	 
			 Node AnswerNode = document.selectSingleNode("/root/dynamic-element[@name='Answer']/dynamic-content");
			 String Answer = AnswerNode.getText();
			
			 json = json.concat("{ \"question\": \"" + Question + "\", \"answer\": \"" + Answer + "\", \"Categories\": [");
			 
			 for (AssetCategory category:assetCategories) {
				 System.out.println(getCategoryIdByName(category.getName()));
				 json = json.concat("\"" + category.getTitle(language) + "\", ");	 
			 }
			 json = json.concat("]}, ");
			}
		}
		json = ("{ \"data\": [" + json + "]}");
		System.out.println(json);
		return (json);
	}
	
	public long getCategoryIdByName(String name) {
		long id;
		DynamicQuery categoryQuery = AssetCategoryLocalServiceUtil.dynamicQuery();
		System.out.println(name);
		categoryQuery.add(PropertyFactoryUtil.forName("name").eq(name));
		List<AssetCategory> categoriesData = AssetCategoryLocalServiceUtil.dynamicQuery(categoryQuery);
		id = categoriesData.get(0).getCategoryId();
		return id;
	}
	
	
	
	public void addSelectedCategory() {
		
	}
}
