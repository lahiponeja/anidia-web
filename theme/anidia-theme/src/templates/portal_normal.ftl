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
	<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--call-us-free-svg cmb">
		<div class="cmb__content">
			<span class="cmb__text">Llámanos gratis</span>
			<span class="an-link cmb__number" class="cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
			<span class="cmb__text-small mobile-hide">Gestionamos por ti lo que necesites</span>
		</div>
	</a>

	<h3>Call me back solo mobile</h3>
	<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--white an-icon--head-phones cmb cmb--header">
		<div class="cmb__content">
			<span class="cmb__text">Llámanos gratis</span>
			<span class="an-link cmb__number" class="cmb__number">${call_center_phone[0..*3]} ${call_center_phone[3..*2]} ${call_center_phone[5..*2]} ${call_center_phone[7..*2]}</span>
		</div>
	</a>

	<div style="margin: 80px 0; display: grid; grid-template-columns: repeat(6, 1fr); grid-template-rows: repeat(2, 150px);">
				<div class="an-icon--bulb-green-svg"></div>
				<div class="an-icon--bulb-white-svg"></div>
				<div class="an-icon--gas-green-svg"></div>
				<div class="an-icon--gas-white-svg"></div>
				<div class="an-icon--heater-green-svg"></div>
				<div class="an-icon--heater-white-svg"></div>
				<div class="an-icon--solar-panel-green-svg"></div>
				<div class="an-icon--solar-panel-white-svg"></div>
				<div class="an-icon--stove-green-svg"></div>

				<div class="an-icon--secure-svg"></div>
				<div class="an-icon--respectful-svg"></div>
				<div class="an-icon--profitable-svg"></div>
				<div class="an-icon--flexible-svg"></div>
				<div class="an-icon--efficiency-responsibility-svg"></div>
				<div class="an-icon--comfortable-svg"></div>
				<div class="an-icon--call-us-free-svg"></div>
				<div class="an-icon--tailor-made-svg"></div>

				<div class="an-icon--euro-coin-svg"></div>
				<div class="an-icon--check-leafs-svg"></div>
				<div class="an-icon--euro-sign-svg"></div>
				<div class="an-icon--leafs-percentage-svg"></div>
			</div>
			<br>
			<br>
			<div style="font-size: 55px; display: grid; grid-template-columns: repeat(5, 100px); gap: 20px;">
				<div class="an-icon--half-arrow-down"></div>
				<div class="an-icon--half-arrow-up"></div>
				<div class="an-icon--half-arrow-left"></div>
				<div class="an-icon--half-arrow-right"></div>
				<div class="an-icon--check-circle"></div>
				<div class="an-icon--unselected"></div>
				<div class="an-icon--power"></div>
				<div class="an-icon--percentage-wallet"></div>
				<div class="an-icon--gas"></div>
				<div class="an-icon--chevron-up"></div>
				<div class="an-icon--chevron-down"></div>
				<div class="an-icon--check-simple"></div>
				<div class="an-icon--bill"></div>
				<div class="an-icon--target"></div>
				<div class="an-icon--spend"></div>
				<div class="an-icon--temperature"></div>

				<div class="an-icon--hot-water-heat-kitchen"></div>
				<div class="an-icon--hot-water-kitchen"></div>
				<div class="an-icon--hot-water"></div>
				<div class="an-icon--saving"></div>
				<div class="an-icon--environment-responsable"></div>
				<div class="an-icon--analisis"></div>
				<div class="an-icon--leverage-energy"></div>
				<div class="an-icon--heating"></div>
				<div class="an-icon--kitchen"></div>
				<div class="an-icon--enjoy"></div>
				<div class="an-icon--lightning-borders"></div>
				<div class="an-icon--lightning"></div>
				<div class="an-icon--business"></div>
				<div class="an-icon--offer"></div>
				<div class="an-icon--apartments"></div>
				<div class="an-icon--respectful"></div>
				<div class="an-icon--secure"></div>
				<div class="an-icon--request"></div>
				<div class="an-icon--living-place"></div>
				<div class="an-icon--search"></div>

				<div class="an-icon--euro-cable"></div>
				<div class="an-icon--unifamiliar-home"></div>
				<div class="an-icon--hot-water-two"></div>
				<div class="an-icon--hot-water-three"></div>
				<div class="an-icon--hot-water-four"></div>
				<div class="an-icon--install"></div>

				<div class="an-icon--check-paper"></div>
				<div class="an-icon--close-cross"></div>
				<div class="an-icon--hand-coin"></div>
				<div class="an-icon--head-phones"></div>
				<div class="an-icon--hot-water-and-pot"></div>
				<div class="an-icon--hot-water-thermo-and-pot"></div>
				<div class="an-icon--thermometer"></div>
				<div class="an-icon--thunder-house"></div>

				<div class="an-icon--facebook"></div>
				<div class="an-icon--instagram"></div>
				<div class="an-icon--linkedin"></div>
				<div class="an-icon--pinterest"></div>
			</div>
    </div>
</div>

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
