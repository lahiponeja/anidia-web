<#include init />
<#if is_signed_in >
	<#include "${full_templates_path}/portlet_signed_in.ftl" />
<#else >
	<#include "${full_templates_path}/portlet_annonymous.ftl" />
</#if>
