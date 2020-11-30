<!DOCTYPE html>

<#include init />
<#include "${full_templates_path}/init_gtm.ftl" />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<#include "${full_templates_path}/head/one_trust.ftl" />
	<#include "${full_templates_path}/head/gtm.ftl" />


	<#include "${full_templates_path}/head/meta_tags.ftl" />

	<#--  <link rel="stylesheet" href="${css_folder}/anidia.css" charset="utf-8">  -->
  <script src="${javascript_folder}/main.js" type="text/javascript"></script>

	<link rel="stylesheet" href="${css_folder}/anidia.css?t=${theme_timestamp}" charset="utf-8">


<script type="application/ld+json">
  {
    "@context" : "http://schema.org",
    "@type" : "Organization",
    "url" : "https://anidia.es/",
    "contactPoint" : [
      { "@type" : "ContactPoint",
      "telephone" : "${call_center_phone_prefix} ${call_center_phone}",
      "contactType" : "customer service",
      "url" : "https://www.anidia.es/#p_p_id_ContactFormPortlet_WAR_ContactFormPortlet_",
      "areaServed" : "ES"
      }]
  }
</script>

<script type="application/ld+json">
  { "@context": "http://schema.org",
    "@type": "WebPage",
      "name": "${the_title} | Anidia",
      "description": "${page_description}",
      "publisher": {
          "@type": "Organization",
          "name": "Anidia"
  }    }
</script>


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
		    	<a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-link an-btn an-btn--flatter an-btn--gradient an-icon--head-phones an-cmb an-cmb--header-small"></a>
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
          <#--  <a href="tel:${call_center_phone_prefix}${call_center_phone}" class="an-btn an-btn--flatter an-btn--gradient an-icon--power">
            <span>Área cliente</span>
          </a>  -->
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
				<button id="ot-sdk-btn" class="ot-sdk-show-settings">Configurar cookies</button>
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

<#include "${full_templates_path}/footer/gtm.ftl" />

<script>
function header() {
  if (Math.max(document.documentElement.clientWidth, window.innerWidth || 0) < 1024){
    let elementWithSubmenu = document.querySelectorAll("ul .dropdown");

    for (let i = 0; i < elementWithSubmenu.length; i++) {
      returnLink(elementWithSubmenu[i]);
    }

  var e = document.querySelector('.nav-item')
    var observer = new MutationObserver(function (event) {
    if (document.querySelector('.nav-item.open')) {
      document.querySelectorAll('.nav-item:not(.open)').forEach(e => {
          e.classList.add('hide');
        });
      }
    })

    observer.observe(e, {
      attributeFilter: ['class']
    })

    function returnLink(menuItem) {
        if (!menuItem.querySelector('.anidia-header__back')) {
          let link = menuItem.getElementsByTagName("a")[0],
          submenu = menuItem.getElementsByTagName("ul")[0],
          backItem = document.createElement("li"),
          backLink = document.createElement("a");

        backLink.href = link.href;
        backLink.text = link.text;
        backItem.classList.add("anidia-header__back");
        backItem.appendChild(backLink);
        submenu.insertAdjacentElement("afterbegin", backItem);
        closeSubmenu(backLink);
      }
    }

    function closeSubmenu(link) {
        link.addEventListener("click", (event) => {
          event.preventDefault();
          let submenu = link.parentElement;
          submenu.parentElement.parentElement.classList.remove("open");
          document.querySelectorAll('.nav-item').forEach(e => {
            e.classList.remove('hide');
          });
        });
    }

    document.querySelector('.anidia-header__input').addEventListener('change', () => {
      document.body.classList.toggle('overflow-hidden');
      document.querySelector('.anidia-header').classList.toggle('active');
      document.querySelector('.site-title .anidia-logo-header--mobile--white').classList.toggle('hide');
      document.querySelector('.site-title .anidia-logo-header--mobile--green').classList.toggle('hide');
    });
  }
}

if (document.querySelector('.anidia-header__input')) {
  header();
}

if (document.querySelectorAll(".an-checkbox__label a").length && document.querySelectorAll(".an-modal").length) {
  var buttons = document.querySelectorAll(".an-checkbox__label a");

  buttons.forEach(button => {
    button.addEventListener('click', (e) => {
      e.preventDefault();
      document.getElementById(button.dataset.modal).style.display = 'block';
    });
  });

  var close = document.querySelectorAll(".an-modal__close");

  close.forEach(c => {
    c.addEventListener('click', (e) => {
      e.preventDefault();
      c.parentElement.parentElement.style.display = "none";
    });
  });
}

svg4everybody(
  {
    attributeName: 'data-href',
    polyfill: true,
    validate: function (src, svg, use) {
      return !src || !src.startsWith('#');
    }
  }
);


</script>
</body>

</html>
