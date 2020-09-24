<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div>
	<h3>Call me back</h3>
	<a href="tel:+34900181818" class="an-link an-btn an-btn--flatter an-btn--white an-icon--call-us-free-svg cmb">
		<div class="cmb__content">
			<span class="cmb__text">Llámanos gratis</span>
			<span class="an-link cmb__number" class="cmb__number">900 18 18 18</span>
			<span class="cmb__text-small mobile-hide">Gestionamos por ti lo que necesites</span>
		</div>
	</a>

	<h3>Call me back solo mobile</h3>
	<a href="tel:+34900181818" class="an-link an-btn an-btn--flatter an-btn--white an-icon--head-phones cmb cmb--header">
		<div class="cmb__content">
			<span class="cmb__text">Llámanos gratis</span>
			<span class="an-link cmb__number" class="cmb__number">900 18 18 18</span>
		</div>
	</a>

	<a href="tel:+34900181818" class="an-link an-btn an-btn--flatter an-btn--gradient an-icon--head-phones cmb cmb--header-small"></a>

<div class="container-fluid" id="wrapper">
	<h1>Heading 1</h1>
	<h2>Heading 2</h2>
	<h3>Heading 3</h3>
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

	<footer id="footer" role="contentinfo">
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>
