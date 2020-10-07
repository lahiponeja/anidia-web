<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>

	<#include "${full_templates_path}/head/meta_tags.ftl" />

	<link rel="stylesheet" href="${css_folder}/anidia.css" charset="utf-8">
  <link rel="text/javascript" href="${javascript_folder}/main.js" charset="utf-8">

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
					<img alt="${logo_description}" src="${images_folder}/logo-anidia-white.svg" class="mobile-hide"/>
          <img alt="${logo_description}" src="${images_folder}/logo-menu-mobile-white.svg" class="desktop-hide anidia-logo--mobile"/>
				</a>
        <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-btn an-btn--flatter an-btn--white an-icon--head-phones">
          <div class="an-cmb__content">
            <span class="an-cmb__text">Llámanos gratis</span>
            <span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
          </div>
        </a>
        <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-btn an-btn--flatter an-btn--gradient an-icon--power mobile-hide">
          <span>Área cliente</span>
        </a>
			</div>

    <label class="anidia-header">
      <input type="checkbox" class="anidia-header__input"/>
      <span class="anidia-header__menu">
        <span class="anidia-header__hamburger"></span>
      </span>
        <@liferay.navigation_menu
                instance_id="main_navigation_menu"
                default_preferences="${freeMarkerPortletPreferences}"
          />
      <div class="anidia-header__footer">
        <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-btn an-btn--flatter an-btn--gradient an-icon--power">
          <span>Área cliente</span>
        </a>
        <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-btn an-btn--flatter an-btn--white an-icon--call-us-free">
          <div class="an-cmb__content">
            <span class="an-cmb__text">Llámanos gratis</span>
            <span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
          </div>
        </a>
      </div>
    </label>
		</div>

    <#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<h3>Call me back</h3>
		<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--call-us-free-svg an-cmb">
			<div class="an-cmb__content">
				<span class="an-cmb__text">Llámanos gratis</span>
				<span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
				<span class="an-cmb__text-small mobile-hide">Gestionamos por ti lo que necesites</span>
			</div>
		</a>

		<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--head-phones an-cmb an-cmb--header">
			<div class="an-cmb__content">
				<span class="an-cmb__text">Llámanos gratis</span>
				<span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
			</div>
		</a>

		<a href="tel:+34900181818" class="an-link an-btn an-btn--flatter an-btn--gradient an-icon--head-phones an-cmb an-cmb--header-small"></a>

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
      <img src="${images_folder}/logo-anidia-green.svg" class="mb-l footer__img"/>
      <!-- REFACTOR WITH LINKS ITEMS .an-link-->
      <div class="footer__links">
				<@liferay.navigation_menu
					default_preferences=
					freeMarkerPortletPreferences.getPreferences(footerNavigationPreferencesMap)
					instance_id="anidia-footer-menu-2"
				/>
      </div>
      <div class="footer__rrss">
        <a href="#" class="footer__rrss__single" target="_blank"><span class="an-icon--pinterest"></span></a>
        <a href="#" class="footer__rrss__single" target="_blank"><span class="an-icon--instagram"></span></a>
        <a href="#" class="footer__rrss__single" target="_blank"><span class="an-icon--linkedin"></span></a>
        <a href="#" class="footer__rrss__single" target="_blank"><span class="an-icon--facebook"></span></a>
      </div>



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
