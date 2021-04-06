<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@
		page import="com.liferay.portal.kernel.json.JSONArray" %><%@
		page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%><%@
		page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<portlet:defineObjects />
<%!
	public String decodeContent(String encodedContent) throws UnsupportedEncodingException {
		byte[] dec = Base64.getDecoder().decode(encodedContent.getBytes());
		return new String(dec, "UTF8");
	}
%>

<%
	String searchTerm = (String)renderRequest.getAttribute("searchTerm");
	String categoryName = (String)renderRequest.getAttribute("categoryName");
	String content = (String)renderRequest.getAttribute("contentJson");
	JSONObject contentJson = JSONFactoryUtil.createJSONObject(content);
	JSONArray contentArray = contentJson.getJSONArray("data");

	List<String> categories = new ArrayList<String>();
	for (int j = 0 ; j < contentArray.length(); j++) {
		contentArray.getJSONObject(j).getJSONArray("tags").forEach(tag ->{
			categories.add(tag.toString());
		});
	}

%>
<%= searchTerm %>

<div id="blogsDivId"  class="bg-white pl-s pr-s pt-s pb-s">
	<div class="an-blog">
		<form method="post" class="an-accordeon__search an-wrapper">
			<div class="an-input an-input--icon-left an-accordeon__search-input-wrapper">
				<span class="an-icon--search"></span>
				<input type="text" class="an-input__field" value="<%=searchTerm%>" placeholder="Busca por palabras" name="<portlet:namespace/>searchTerm" id="<portlet:namespace/>searchTerm"/>
				<input type="hidden" value="<%=categoryName%>" name="<portlet:namespace/>categoryName" id="<portlet:namespace/>categoryName"/>
			</div>
			<input class="an-btn an-btn--green-border an-btn--flatter an-accordeon__search-btn" type="submit" value="Buscar">
		</form>

		<div class="an-blog__categories an-wrapper">
			<h6 class="an-h6 mb-s">O busca por tematica</h6>
			<%
			for(int i = 0; i < categories.size(); i++) {
			%>
			<div class="an-pill">
				<a href="#"><span class="entry-categories an-body-xs-bold"><%= (String)categories.get(i) %></span></a>
			</div>
			<%
			}
			%>
		</div>


		<div class="an-blog__content an-blog__content--multi" data-accordeon-list>
			<%
			if (contentArray != null){
				for (int i = 0 ; i < contentArray.length(); i++) {
					JSONObject item = contentArray.getJSONObject(i);
						%>
				<article class="blog-entry an-card an-card--blog">
					<img src='<%= item.getString("image") %>' alt="" class="an-card--blog__img" />
					<div class="an-card--blog__content">
						<div class="an-card--blog__tags">
							<%
							if (item.getJSONArray("tags") != null){
							JSONArray kk = item.getJSONArray("tags");
							for (int a = 0 ; a < kk.length(); a++) {
								String singleTag = kk.getString(a);
							%>
							<div class="an-pill">
								<span class="entry-categories an-body-xs-bold"><%= singleTag %></span>
							</div>
							<%
							}
						}
					%>	
						</div>	
					<time datetime="" class="meta an-card--blog__content__date an-h6"><%= item.getString("date") %></time>
					<h3 class="an-body-l-bold"><a href='<%= item.getString("url") %>'><span><%= item.getString("title") %></span></a></h3>
					<a href='<%= item.getString("url") %>' class="an-icon--half-arrow-right an-card__icon-link"></a>
				</div>
			</article>
			<%
				}
				}
			%>
		</div>
	</div>
</div>
​
<script>
	<%if(searchTerm != ""){%>
    document.getElementById("blogsDivId").scrollIntoView(true);
<%}%>
</script>
​
<script type="application/ld+json">
	{ "@context": "https://schema.org",
		"@type": "BlogPage",
		"mainEntity": [
		<%for (int i = 0 ; i < contentArray.length(); i++) {
	JSONObject item = contentArray.getJSONObject(i);
%>
		 {
			"@type": "Question",
			"name": "<%= item.get("question") %>",
			"acceptedAnswer": {
				"@type": "Answer",
				"text": "<%= item.get("answer") %>"
			}
		 }, 
	  <%}%>
		  ]
	}
</script>