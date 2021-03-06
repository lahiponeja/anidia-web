<div class="an-blog">

<#assign totalEntries = entries?size>
<#assign blogsEntryClassName = "com.liferay.blogs.kernel.model.BlogsEntry" />

  <#if totalEntries gte 3>
  <div class="an-blog__content an-blog__content--multi">
  <#else>
  <div class="an-blog__content">
  </#if>

  <#list entries as entry>
    <#assign blogEntry = entry.getAssetRenderer().getAssetObject() />
    <#assign entryURL = "/web/guest/b/" + entry.getAssetRenderer().getUrlTitle() />


      <article class="blog-entry an-card an-card--blog">
          <div class="an-card--blog__img-container">
            <img src="${blogEntry.getCoverImageURL(themeDisplay)}" alt="${htmlUtil.escape(entry.getTitle())}" class="an-card--blog__img" />
          </div>
          <div class="an-card--blog__content">
            <#list entry.getTagNames() as tag>
              <div class="an-pill">${tag}</div>
            </#list>
          <time datetime="${dateUtil.getDate(entry.getCreateDate(), "yyyy-MM-dd'T'HH:mm:ssZ", locale)}" class="meta an-card--blog__content__date an-body-xs-bold">
            ${dateUtil.getDate(entry.getCreateDate(), "dd MMM yyyy", locale)}
          </time>

          <h3 class="an-body-l-bold"><a href="${entryURL}"><span>${htmlUtil.escape(entry.getTitle())}</span></a></h3>
          <a href="${entryURL}" class="an-icon--half-arrow-right an-card__icon-link"></a>
          </div>
      </article>
  </#list>
  </div>
</div>