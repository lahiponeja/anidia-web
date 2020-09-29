<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(PortalUtil.getOriginalServletRequest(request), "redirect");
}

redirect = PortalUtil.escapeRedirect(redirect);

boolean showBackURL = GetterUtil.getBoolean(request.getAttribute("view.jsp-showBackURL"));

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/view.jsp");

	redirect = portletURL.toString();
}

AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRendererFactory<?> assetRendererFactory = (AssetRendererFactory<?>)request.getAttribute("view.jsp-assetRendererFactory");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");

long previewClassNameId = ParamUtil.getLong(request, "previewClassNameId");
long previewClassPK = ParamUtil.getLong(request, "previewClassPK");

boolean print = GetterUtil.getBoolean(request.getAttribute("view.jsp-print"));

assetPublisherDisplayContext.setLayoutAssetEntry(assetEntry);

assetEntry = assetPublisherDisplayContext.incrementViewCounter(assetEntry);

String title = assetRenderer.getTitle(locale);

PortletURL viewFullContentURL = assetPublisherHelper.getBaseAssetViewURL(liferayPortletRequest, liferayPortletResponse, assetRenderer, assetEntry);

if (print) {
	viewFullContentURL.setParameter("viewMode", Constants.PRINT);
}

String viewInContextURL = assetRenderer.getURLViewInContext(liferayPortletRequest, liferayPortletResponse, HttpUtil.setParameter(viewFullContentURL.toString(), "redirect", currentURL));

Map<String, Object> fragmentsEditorData = HashMapBuilder.<String, Object>put(
	"fragments-editor-item-id", PortalUtil.getClassNameId(assetRenderer.getClassName()) + "-" + assetRenderer.getClassPK()
).put(
	"fragments-editor-item-type", "fragments-editor-mapped-item"
).build();
%>

		<liferay-asset:asset-display
			assetEntry="<%= assetEntry %>"
			assetRenderer="<%= assetRenderer %>"
			assetRendererFactory="<%= assetRendererFactory %>"
			showExtraInfo="<%= assetPublisherDisplayContext.isShowExtraInfo() %>"
		/>
