<#--
This file allows you to override and define new FreeMarker variables.
-->
<#if getterUtil.getString(themeDisplay.getThemeSetting("Esquema de colores de cabecera")) == "verde">
	<#assign css_class = css_class + " anidia-green" />
<#else/>
	<#assign css_class = css_class + " anidia-white" />
</#if>