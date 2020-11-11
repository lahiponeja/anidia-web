<section class="an-hero-slider__item bg-gradient-green" data-slider-item-section>
	<div class="an-hero-slider__item-inner-wrapper">
	<div class="an-hero-slider__info-container">

    <div class="an-hero-slider__info">
    	<p class="an-h1 an-hero-slider__heading">
      ${Título.getData()}
      </p>
      <p class="an-body-l-regular an-hero-slider__text">
      ${Subtítulo.getData()}
      </p>

      <#if TextoBotón.getData?? && TextoBotón.getData() != "">
			<div class="an-hero-slider__btn">
        <#if LinkToPage1z94.getFriendlyUrl() != "">
        <a href="${LinkToPage1z94.getFriendlyUrl()}" class="an-btn an-btn--flatter an-btn--white">
          <span>${TextoBotón.getData()}</span>
        </a>
        <#else>
            <#if LinkToUrl.getData??>
                <a href="${LinkToUrl.getData()}" class="an-btn an-btn--flatter an-btn--white">
                  <span>${TextoBotón.getData()}</span>
                </a>
            </#if>
        </#if>
        </div>
      </#if>
    </div>

		<div class="an-hero-slider__image">
		<#if ImagenDesktop.getData()?? && ImagenDesktop.getData() != "">
			<img class="an-hero-slider__image-desktop" src="${ImagenDesktop.getData()}" alt="">
		</#if>
		<#if ImagenMobile.getData()?? && ImagenMobile.getData() != "">
			<img class="an-hero-slider__image-mobile" src="${ImagenMobile.getData()}" alt="">
		</#if>
		</div>

		<button class="an-h6 an-hero-slider__discover-btn" type="button" data-discover-btn>
		Descubre más
		<span class="an-icon--half-arrow-right"></span>
		</button>

	</div>
	</div>
</section>
