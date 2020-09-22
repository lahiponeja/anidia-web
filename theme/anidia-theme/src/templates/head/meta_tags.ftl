	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

  <#if is_signed_in >
    <@liferay_util["include"] page=top_head_include />
  <#else >

  	<@liferay_theme["meta-tags"] />

		<#assign completeURL = portalUtil.getCurrentCompleteURL(request) >
		<#assign canonicalURL = portalUtil.getCanonicalURL(completeURL, themeDisplay, layout) >

		<link href="${htmlUtil.escapeAttribute(canonicalURL)}" rel="canonical" />

		<#assign availableLocales = languageUtil.getAvailableLocales(themeDisplay.getSiteGroupId()) >

		<#if availableLocales?size gt 1 >
			<#list availableLocales as availableLocale>
				<#if availableLocale == localeUtil.getDefault() >
					<link href="${htmlUtil.escapeAttribute(canonicalURL) }" hreflang="x-default" rel="alternate" />
				</#if>
				<link href="${htmlUtil.escapeAttribute(portalUtil.getAlternateURL(canonicalURL, themeDisplay, availableLocale, layout)) }" hreflang="${localeUtil.toW3cLanguageId(availableLocale)}" rel="alternate" />
		</#list>
		</#if>
	</#if>