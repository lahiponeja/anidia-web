package APCustom.portlet;

import APCustom.constants.APCustomPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

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
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
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
	
	@ProcessAction(name="actionMethod1")
	public void sampleActionMethod(ActionRequest request, ActionResponse response)
			throws IOException, PortletException, PortalException, SystemException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
        		WebKeys.THEME_DISPLAY);
		
		List<JournalArticle> journalArticles =  new ArrayList<JournalArticle>();
        String structureName = "FAQ";
        String structureKey = getStructureKey(structureName);  
		long groupId = themeDisplay.getScopeGroupId();
		
		//TO DO: Does this fetch expired webcontents too?
		journalArticles = JournalArticleLocalServiceUtil.getStructureArticles(groupId, structureKey);

		System.out.println(toJson(journalArticles));
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
	
	public String toJson(List<JournalArticle> Articles)throws JSONException {
		//TO DO : Obtain current languaje to retrieve content: maybe with  themeDisplay.getLocale().toLanguageTag() 

		for (JournalArticle entry : Articles) {
			json = json.concat(toJsonAux(entry.getContentByLocale("en")) + ",");		
		}

		return ("[" + json + "]");;
	}
	
	public String toJsonAux(String content) throws JSONException {
		 return (JSONFactoryUtil.convertXMLtoJSONMLArray(content));	
	}
}
