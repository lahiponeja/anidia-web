<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject"%><%@ 
page import="com.liferay.portal.kernel.json.JSONArray" %>

<portlet:defineObjects />


<portlet:actionURL name="actionMethod1" var="sampleActionMethodURL">
</portlet:actionURL>
<form action="${sampleActionMethodURL}" method="post">
	<input type="submit" value="Get FAQS"> 
</form>

<%
String content = (String)renderRequest.getAttribute("contentJson");
JSONObject contentJson = JSONFactoryUtil.createJSONObject(content);
JSONArray contentArray = contentJson.getJSONArray("data");
%>

<%
if (contentArray!=null){

	for (int i = 0 ; i < contentArray.length(); i++) {
		JSONObject item = contentArray.getJSONObject(i);
		JSONArray categoriesArray = item.getJSONArray("Categories");
%>
		<h4>FAQ <%= i+1 %></h4>
		<p> Pregunta:  <%= item.get("question") %></p>
		<p> Respuesta:  <%= item.get("answer") %></p>
		<p> Categotías:  <%= categoriesArray %></p>
<%
	}
}
%>

