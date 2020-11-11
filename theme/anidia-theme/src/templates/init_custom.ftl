<#--
This file allows you to override and define new FreeMarker variables.
-->



<#assign page_description = layout.getDescription(themeDisplay.getLocale(), true)/>

<#assign contact_mail = themeDisplay.getThemeSetting("Mail de contacto") />

<#-- We use slicing to split the phone and the prefix if it's needed -->
<#assign call_center_phone = themeDisplay.getThemeSetting("Teléfono de Call Center") />
<#assign call_center_phone_prefix = "+34" />

<#if call_center_phone?starts_with("+") >
  <#assign call_center_phone_prefix = call_center_phone[0..*3] />
  <#assign call_center_phone = call_center_phone[3..] />
</#if>

<#if getterUtil.getString(themeDisplay.getThemeSetting("Esquema de colores de cabecera")) == "verde">
	<#assign css_class = css_class + " anidia-green" />
<#else/>
	<#assign css_class = css_class + " anidia-white" />
</#if>

<#assign gtm_id = themeDisplay.getThemeSetting("GTM-ID") />
<#assign oneTrustEnvironment = themeDisplay.getThemeSetting("Entorno OneTrust") />


<#-- ---------- Footer -------------  -->
<#-- Settings for the footer menu  -->

<#assign footerNavigationPreferencesMap =
  {
    "displayStyle": "ddmTemplate_LIST-MENU-FTL",
    "portletSetupPortletDecoratorId": "bottom-menu",
    "rootLayoutType": "relative",
    "siteNavigationMenuId": "0",
    "siteNavigationMenuType": "2",
    "expandedLevels": "all"
  }
/>
<#-- We get the style of the movile call center link -->

<#assign call_center_phone_mobile_version = themeDisplay.getThemeSetting("Versión de Call Center en móvil") />
