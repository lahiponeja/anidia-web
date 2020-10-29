package APCustom.portlet;

import APCustom.constants.APCustomPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;


import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;

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
public class APCustomPortlet extends MVCPortlet {
	@ProcessAction(name="actionMethod1")
	public void sampleActionMethod(ActionRequest request, ActionResponse response)
			throws IOException, PortletException, PortalException, SystemException{
		System.out.println("Inside my dear portlet logic controller");
		
		
		
		List<JournalArticle> journalArticleList = new ArrayList<JournalArticle>();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		assetEntryQuery.setAnyCategoryIds(new long[] { 12704 }); //category Id
		assetEntryQuery.setOrderByCol1("modifiedDate");
		assetEntryQuery.setEnd(5);
		List<AssetEntry> assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
		
		System.out.println(assetEntryList.get(0));
		
	}/*
	public static List<JournalArticle> getSelectedWebContents(List<AssetEntry> assetEntries) {
		List<JournalArticle> journalArticles = new ArrayList<JournalArticle>();
		JournalArticleResource journalArticleResource = null;
		JournalArticle journalArticle = null;
		for (AssetEntry entry : assetEntries) {
			try {
				journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(entry.getClassPK());
				journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			journalArticles.add(journalArticle);
		}
		return journalArticles;
	}

       public static List<AssetEntry> getSelectedAssetEntry(List<String> categoryName){
		   List<AssetEntry>assetEntry = new ArrayList<AssetEntry>();
		try {
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			//assetEntryQuery.setAnyCategoryIds(getCategoryIdByName(categoryName));// Represent Any ie OR
			assetEntryQuery.setAllCategoryIds(getCategoryIdByName(categoryName)); // Represent Both ie AND
			assetEntryQuery.setClassName(JournalArticle.class.getName());
			List<String>structureNames = new ArrayList<String>();
			structureNames.add("MOBILE_STRUCTURE");
			long [] structureIds= getStructureIdByName(structureNames);
			assetEntryQuery.setClassTypeIds(structureIds);
			assetEntry = AssetEntryServiceUtil.getEntries(assetEntryQuery);	
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} 
		return assetEntry;
	}
		
	public static long[] getCategoryIdByName(List<String> categoryName) {
		long[] allCategoryIds = null;
		List<Long> allCategoriesList = new ArrayList<Long>();
		try {

			List<AssetCategory> assetCategory = AssetCategoryLocalServiceUtil.getAssetCategories(0, AssetCategoryLocalServiceUtil.getAssetCategoriesCount());
			for (AssetCategory asset : assetCategory) {
				for (String name : categoryName) {
					if (asset.getName().equalsIgnoreCase(name)) {
						allCategoriesList.add(asset.getCategoryId());
						continue;
					}
				}
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		if ((allCategoriesList != null) && (allCategoriesList.size() > 0)) {
			allCategoryIds = new long[allCategoriesList.size()];
			for (int i = 0; i < allCategoriesList.size(); i++) {
				allCategoryIds[i] = allCategoriesList.get(i);
			}
		}
		return allCategoryIds;
	}
	
	public static long[] getStructureIdByName(List<String> structureNames) {
		long[] allStructureIds = new long[structureNames.size()];
		try {
			List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getDDMStructures(0, DDMStructureLocalServiceUtil.getDDMStructuresCount());
			int counter = 0;
			for (DDMStructure structure : ddmStructures) {
				for (String name : structureNames) {
					if (structure.getName(Locale.ENGLISH)
							.equalsIgnoreCase(name)) {
						allStructureIds[counter] = structure.getStructureId();
						counter++;
						continue;
					}
				}
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return allStructureIds;
	}
	*/
}
