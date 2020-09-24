<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>

	<#include "${full_templates_path}/head/meta_tags.ftl" />

	<link rel="stylesheet" href="${css_folder}/anidia.css" charset="utf-8">

</head>

<body class="${css_class}">
<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<div aria-level="1" class="site-title" role="heading">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</div>
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>

	<section id="content">
		<h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>

	<footer id="footer" role="contentinfo" class="footer">
			<img class="mb-l" src="../images/logo-anidia-green.svg" class="footer__img"/>
      <!-- REFACTOR WITH LINKS ITEMS .an-link-->
      <div class="footer__links">
        <a class="an-h6 footer__link mb-m" href="#">Aviso legal</a>
        <a class="an-h6 footer__link mb-m" href="#">Política de cookies</a>
        <a class="an-h6 footer__link mb-m" href="#">Política de privacidad</a>
      </div>
      <div class="footer__rrss">
        <a class="footer__rrss__single"><span class="an-icon--pinterest"></span></a>
        <a class="footer__rrss__single"><span class="an-icon--instagram"></span></a>
        <a class="footer__rrss__single"><span class="an-icon--linkedin"></span></a>
        <a class="footer__rrss__single"><span class="an-icon--facebook"></span></a>
      </div>

		<@liferay.navigation_menu
		default_preferences=
		freeMarkerPortletPreferences.getPreferences(footerNavigationPreferencesMap)
		instance_id="anidia-footer-menu-2"
	/>

	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<#if is_signed_in || layoutTypePortlet.hasStateMax() >
	<@liferay_util["include"] page=bottom_include />
</#if>
<!-- inject:js -->
<!-- endinject -->

</body>

</html>
