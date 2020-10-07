<section class="an-hero-slider__item bg-gradient-green" data-slider-item-section>
	<div class="an-hero-slider__item-inner-wrapper">
	<div class="an-hero-slider__info-container">
		<p class="an-h1 an-hero-slider__heading">
		${Título.getData()}
		</p>
		<p class="an-body-l-regular an-hero-slider__text">
		${Subtítulo.getData()}
		</p>

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

		<div class="an-hero-slider__btn">
		<a href="${LinkToPage1z94.getFriendlyUrl()}" class="an-btn an-btn--flatter an-btn--white">
			<span>${TextoBotón1.getData()}</span>
		</a>
		</div>
	</div>
	</div>
</section>