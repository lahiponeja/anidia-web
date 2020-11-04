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
	<input type="text" name="<portlet:namespace/>searchTerm" id="<portlet:namespace/>searchTerm"/><br/>
	<input type="submit" value="Get FAQS"> 
</form>

<%
String setOfCategories = (String)renderRequest.getAttribute("setOfCategories");
String content = (String)renderRequest.getAttribute("contentJson");
JSONObject contentJson = JSONFactoryUtil.createJSONObject(content);
JSONArray contentArray = contentJson.getJSONArray("data");
%>
<p> Categotías encontradas: <%= setOfCategories %> </p>
<%
if (contentArray!=null){

	for (int i = 0 ; i < contentArray.length(); i++) {
		JSONObject item = contentArray.getJSONObject(i);
		JSONArray categoriesArray = item.getJSONArray("Categories");
		String categoriesText = "";
		for (Object category : categoriesArray){
			categoriesText = categoriesText.concat(category.toString() + " ");
		}
%>
		<h4>FAQ <%= i+1 %></h4>
		<p> Pregunta:  <%= item.get("question") %></p>
		<p> Respuesta:  <%= item.get("answer") %></p>
		<p> Categotías: <%= categoriesText %> </p>



<%
	}
}
%>

