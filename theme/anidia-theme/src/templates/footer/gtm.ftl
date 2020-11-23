<script>
window.dataLayer.push({
  "event": "anidiapageview",
  "content": {
    "category" : "${gtm_category_page}",
    "contenthierarchy": {
      <#assign hierarchy_counter = 0 />
      <#list gtm_ancestors>
        <#items as ancestor>
          "${hierarchy_counter}": "${ancestor.getHTMLTitle(locale)}",
          <#assign hierarchy_counter += 1 />
        </#items>
      </#list>
      "${hierarchy_counter}": "${layout.getHTMLTitle(locale)}"
    },
    "country": "spain",
    "date": "${dateUtil.getCurrentDate("yyyyMMdd", locale, timeZoneUtil.getDefault())}",
    "asset": "anidiamainweb",
    "host": "${theme_display.getPortalDomain()}",
    "language": "${locale.getLanguage()}",
    "mode": "${browserSniffer.isMobile(request)?then('mobile','desktop')}",
    "pagetype": "${gtm_pagetype}",
    "pagename": "${gtm_pagename}",
    "referrer": "${request.getHeader("referer")!""}",
    "subcategory1": "${gtm_subcategory}",
    "subcategory2": "",
    "subcategory3": "",
    "subcategory4": "",
    "time": "${dateUtil.getCurrentDate("HHmmss", locale, timeZoneUtil.getDefault())}",
    "url": "${request.getRequestURL()}",
    "weekday": "${dateUtil.getCurrentDate("EEEEEEEEEEE", locale, timeZoneUtil.getDefault())}"
  }
});

if (typeof ga === 'function') {
  ga(function(tracker) {
    var clientId = tracker.get('clientId');

    var adblockActive = false;

    try {
      var xmlhttp = new XMLHttpRequest();
      xmlhttp.open("HEAD", "/js/prebid-ads.js", false);
      xmlhttp.send();
    } catch(e) {
      adblockActive = true;
    }

    window.dataLayer.push({
    "event": "anidiapageview",
    "user": {
      "gaclient" : clientId,
      "adblock": (adblockActive ? "enabled" : "disabled"),
      "useragent": "${request.getHeader("User-Agent")!""}",
      "subcribednewsletter": undefined,
      "logged": "no logado",
      "idcrm": undefined,
      "lastlogin": undefined,
      "product1": undefined,
      "registrationdate": undefined,
      "gender": undefined,
      "age": undefined,
      "province": undefined,
      "city": undefined,
      "isclient": undefined,
      "phonehas": undefined,
      "emailhas": undefined,
      }
    });
  });
}

window.dataLayer.push({
  "event": "anidiapageview",
  "campaign": {
    "campaign": "${request.getParameter("utm_campaign")!""}",
    "trafficsource": "${request.getParameter("utm_trafficsource")!""}",
    "medium": "${request.getParameter("utm_medium")!""}",
    "term": "${request.getParameter("utm_term")!""}",
    "content": "${request.getParameter("utm_content")!""}",
    "glcid": "${request.getParameter("gclid")!""}",
    "gclsrc": "${request.getParameter("gclsrc")!""}"
  }
});
</script>
