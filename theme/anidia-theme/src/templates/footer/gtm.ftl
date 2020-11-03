
<script>
window.dataLayer.push({
	"event": "anidiapageview",
	"content": {
		"category" : "${gtm_category_page}",
		"contenthierarchy": [
			{
			<#assign hierarchy_counter = 0 />
			<#list gtm_ancestors>
				<#items as ancestor>
					"${hierarchy_counter}": "${ancestor.getHTMLTitle(locale)}",
					<#assign hierarchy_counter += 1 />
				</#items>
			</#list>
				"${hierarchy_counter}": "${layout.getHTMLTitle(locale)}"
			}
		]
	}
})
</script>
