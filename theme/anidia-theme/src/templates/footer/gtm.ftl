
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
				"${hierarchy_counter}": "${layout.getHTMLTitle(locale)}",
        "country": "spain",
        "aset": "anidiamainweb",
        "host": "${theme_display.getHost()}",
        "language": "${locale.getLanguage()}",
        "mode": "${theme_display.IsMobile()?then('mobile','desktop')}",
        "pagetype": "${gtm_pagetype}",
        "pagename": "${gtm_pagename}",
        "referrer": "${request.getHeader("Referer")",
        "subcategory1:" "instalationtype"
        "subcategory2:" ""
        "subcategory3:" ""
        "subcategory4:" ""

			}
		]
	}
})
</script>
