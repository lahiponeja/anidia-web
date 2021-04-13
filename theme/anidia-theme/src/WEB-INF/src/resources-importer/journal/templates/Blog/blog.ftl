<div class="an-blog">
<#assign totalEntries = entries.getList().size()>
  <#if totalEntries..getList()?size >= 3>
  <div class="an-blog__content an-blog__content--multi">
  <#else>
  <div class="an-blog__content">
  </#if>
 
  <#list entries as entry>
    <#assign viewURL = renderResponse.createRenderURL() />
    ${viewURL.setWindowState("MAXIMIZED")}
    ${viewURL.setParameter("mvcRenderCommandName", "/blogs/view_entry")}
    ${viewURL.setParameter("redirect", currentURL)}
    ${viewURL.setParameter("urlTitle", entry.getUrlTitle())}

    <#assign blogsEntryClassName = "com.liferay.blogs.kernel.model.BlogsEntry" />
      <article class="blog-entry an-card an-card--blog">
      		<div class="an-card--blog__img-container">
					  <img src="${entry.getSmallImageURL(themeDisplay)}" alt="${htmlUtil.escape(entry.getTitle())}" class="an-card--blog__img" />
					</div>
          <div class="an-card--blog__content">
            <div class="an-pill">
              <span class="entry-categories an-body-xs-bold">
                <@liferay_ui["asset-categories-summary"]
                className=blogsEntryClassName
                classPK=entry.getEntryId()
                portletURL=renderResponse.createRenderURL()
                />
              </span>
            </div>
          <time datetime="${dateUtil.getDate(entry.getCreateDate(), "yyyy-MM-dd'T'HH:mm:ssZ", locale)}" class="meta an-card--blog__content__date an-body-xs-bold">
            ${dateUtil.getDate(entry.getCreateDate(), "dd MMM yyyy", locale)}
          </time>
          <h3 class="an-body-l-bold"><a href="${viewURL}"><span>${htmlUtil.escape(entry.getTitle())}</span></a></h3>
          <a href="${viewURL}" class="an-icon--half-arrow-right an-card__icon-link"></a>
          </div>
      </article>
  </#list>
  </div>
</div>
