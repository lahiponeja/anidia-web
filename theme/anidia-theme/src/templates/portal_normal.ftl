<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
  <#if !is_signed_in && !layoutTypePortlet.hasStateMax()>
    <script src="${javascript_folder}/loader-min.js" type="text/javascript"></script>
  </#if>
	<#include "${full_templates_path}/head/one_trust.ftl" />

<!-- Google Tag Manager -->
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src='https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);})(window,document,'script','dataLayer','${gtm_id}');</script>
<!-- End Google Tag Manager -->

	<#include "${full_templates_path}/head/meta_tags.ftl" />

	<#--  <link rel="stylesheet" href="${css_folder}/anidia.css" charset="utf-8">  -->
  <script src="${javascript_folder}/main.js" type="text/javascript"></script>

  <#if !is_signed_in && !layoutTypePortlet.hasStateMax()>
    <script src="${javascript_folder}/loader-min.js" type="text/javascript"></script>
  </#if>

	<link rel="stylesheet" href="${css_folder}/anidia.css?t=${theme_timestamp}" charset="utf-8">

</head>

<body class="${css_class}">
<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=${gtm_id}" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid p-0" id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<div aria-level="1" class="site-title" role="heading">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
          <div class="site-title--mobile-hide">
          	<img alt="${logo_description}" src="${images_folder}/logo-anidia-white.svg" class="anidia-logo-header anidia-logo-header--white"/>
            <img alt="${logo_description}" src="${images_folder}/logo-anidia-green.svg" class="anidia-logo-header anidia-logo-header--green"/>
          </div>
          <div class="site-title--desktop-hide">
            <img alt="${logo_description}" src="${images_folder}/logo-menu-mobile-white.svg" class="anidia-logo-header--mobile anidia-logo-header--mobile--white"/>
            <img alt="${logo_description}" src="${images_folder}/logo-menu-mobile-green.svg" class="anidia-logo-header--mobile anidia-logo-header--mobile--green"/>
          </div>
				</a>
				<#if (call_center_phone_mobile_version == "icono + texto")>
        <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--head-phones an-cmb an-cmb--header">
          <div class="an-cmb__content">
            <span class="an-cmb__text">Llámanos gratis</span>
            <span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
          </div>
        </a>
				<#else/>
		    	<a href="tel:+34900181818" class="an-link an-btn an-btn--flatter an-btn--gradient an-icon--head-phones an-cmb an-cmb--header-small"></a>
				</#if>

			</div>

      <label class="anidia-header">
        <input type="checkbox" class="anidia-header__input"/>
        <span class="anidia-header__menu">
          <img alt="${logo_description}" src="${images_folder}/logo-menu-mobile-white.svg" class="hide anidia-logo--menu"/>
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
          <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--call-us-free-svg an-cmb">
            <div class="an-cmb__content">
              <span class="an-cmb__text">Llámanos gratis</span>
              <span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
              <span class="an-cmb__text-small mobile-hide">Gestionamos por ti lo que necesites</span>
            </div>
          </a>
        </div>
      </label>
		</div>

    <#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--call-us-free-svg an-cmb mobile-hide">
			<div class="an-cmb__content">
				<span class="an-cmb__text">Llámanos gratis</span>
				<span class="an-link an-cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
				<span class="an-cmb__text-small mobile-hide">Gestionamos por ti lo que necesites</span>
			</div>
		</a>



	</header>

	<section id="content">
		<#--  <h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>  -->

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
				<button id="ot-sdk-btn" class="ot-sdk-show-settings">Gestionar cookies</button>
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
<script>
window.dataLayer.push({
	"event": "anidiapageview",
	"content": {
		"category" :"",
		"contenthierarchy": [
			{
				"0": "home"
			}
		]
	}
})
</script>
</body>

</html>
