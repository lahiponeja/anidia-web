<#-- ---------- GTM -------------  -->
<#assign gtm_id = themeDisplay.getThemeSetting("GTM-ID") />
<#assign oneTrustEnvironment = themeDisplay.getThemeSetting("Entorno OneTrust") />

<#assign gtm_category_page = themeDisplay.getThemeSetting("GTM - Category") />
<#assign gtm_type_page = themeDisplay.getThemeSetting("GTM - PageType") />
<#assign gtm_ancestors = layout.getAncestors() />
<#assign gtm_pagename = portalUtil.getCanonicalURL(completeURL, themeDisplay, layout) >