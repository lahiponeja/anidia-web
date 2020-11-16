<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject"%><%@ 
page import="com.liferay.portal.kernel.json.JSONArray" %>


<portlet:defineObjects />

<%
String content = (String)renderRequest.getAttribute("contentJson");
JSONObject contentJson = JSONFactoryUtil.createJSONObject(content);
JSONArray contentArray = contentJson.getJSONArray("data");
JSONArray setOfCategoriesArray = contentJson.getJSONArray("foundCategories");
%>

<div class="bg-white pl-s pr-s pt-s pb-s">
	<div class="an-accordeon" data-accordeon>

		<form method="post" class="an-accordeon__search">
			<div class="an-input an-input--icon-left an-accordeon__search-input-wrapper">
				<span class="an-icon--search"></span>
				<input type="text" class="an-input__field" placeholder="Busca por palabras" name="<portlet:namespace/>searchTerm" id="<portlet:namespace/>searchTerm" required/>
			</div>
			<input class="an-btn an-btn--green-border an-btn--flatter an-accordeon__search-btn" type="submit" value="Buscar">
		</form>

	 	<ul class="an-accordeon__list" data-accordeon-list>
	 		<%
			if (setOfCategoriesArray!=null){
				
				for (Object category : setOfCategoriesArray){
					String categoryName =category.toString();
					%>
					<li class="an-accordeon__item an-accordeon__item" data-accordeon-list-item>
						<div class="an-accordeon__item-head" data-accordeon-head>
				          <div class="an-accordeon__item-head-title"><%=categoryName%></div>
				          <span class="an-accordeon__item-head-icon"></span>
				        </div>
						<div class="an-accordeon__item-body">
							<div class="an-accordeon__item-body-inner">
								<div class="an-accordeon an-accordeon--child" data-accordeon>
									<ul class="an-accordeon__list" data-accordeon-list>
											<%
											if (contentArray!=null){
											
												for (int i = 0 ; i < contentArray.length(); i++) {
													JSONObject item = contentArray.getJSONObject(i);
													JSONArray categories = item.getJSONArray("Categories");
													
													if(categories.toString().contains(categoryName)){
											%>
														<li class="an-accordeon__item an-accordeon__item" data-accordeon-list-item>
											                    <div class="an-accordeon__item-head" data-accordeon-head>
											                      <div class="an-accordeon__item-head-title"><%= item.get("question") %></div>
											                      <span class="an-accordeon__item-head-icon"></span>
											                    </div>
											                    <div class="an-accordeon__item-body">
											                      <div class="an-accordeon__item-body-inner">
											                      <p><%= item.get("answer") %></p>
											                        </div>
											                    </div>    
		
														</li>									
											<%
													}
												}
											}
											%>
									</ul>
								</div>	
							</div>
						</div>
					</li>
					<%				
				}
			} 		
	 		%>
		</ul>		
	</div>
</div>

<script>
(function() {
	  function accordeon() {
	    const accList = this.querySelector("[data-accordeon-list]");
	    const accItems = Array.from(accList.children);

	    function closeOpenItems() {
	      const accFilteredItems = accItems.filter((item) => item != this);
	      accFilteredItems.forEach(function(filteredItem) {
	        const headItem = filteredItem.querySelector("[data-accordeon-head]");
	        const parent = headItem.parentElement;
	        if(parent.classList.contains("an-accordeon__item--open")) {
	          parent.classList.remove("an-accordeon__item--open");
	        }
	      });
	    }

	    function toggleActive() {
	      closeOpenItems.call(this);
	      this.classList.toggle("an-accordeon__item--open");
	    }

	    function addEventListeners() {
	      this.forEach(function(accItem) {
	        const headItem = accItem.querySelector("[data-accordeon-head]");
	        headItem.addEventListener("click", toggleActive.bind(accItem));
	      });
	    }

	    function initAccordeon() {
	      addEventListeners.call(accItems);
	    }

	    initAccordeon();
	  }

	  if(this) {
	    this.forEach(function(acc){accordeon.call(acc);});
	  }
	}).call(document.querySelectorAll("[data-accordeon]"));

</script>

<style>
.faq {
  color: inherit;
  font-size: 18px;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans',
    'Helvetica Neue', sans-serif;
}

</style>

<script type="application/ld+json">
	{   "@context": "https://schema.org",
		"@type": "FAQPage",
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