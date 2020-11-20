function heroSlider() {
  const dataSliderLinesList = document.querySelector("[data-slider-lines-list]");
  var slides = document.querySelectorAll(".an-hero-slider .journal-content-article");

  const linesHTML = []
  for(let i = 0; i < slides.length; i++) {
    linesHTML.push(`<div class="an-hero-slider__lines-item an-hero-slider__lines-item-${(i + 1)} ${ i === 0 && "an-hero-slider__lines-item--active"}" data-slide-line></div>`);
  }
  dataSliderLinesList.innerHTML = linesHTML.join("");

  const controller = new ScrollMagic.Controller({ addIndicators: true });

  // var timeline = new TimelineMax();

  // var tween1 = TweenMax.staggerFromTo(".an-hero-slider__info-container", 1, { bottom: 0 },{bottom: document.querySelectorAll(".an-hero-slider").offsetHeight}, 0.1);

  // var tween2a = TweenMax.staggerTo(".an-hero-slider__info-container", 0.5, { y: 0 }, 0.1);

  // var tween2b = TweenMax.staggerTo(".an-hero-slider__info-container", 0.35, { y: -100 }, 0.1);

  // timeline.add(tween1, 0)
  //     .add(tween2a, 0)
  //     .add(tween2b, 0.5);


  const sceneBgAnimate = new ScrollMagic.Scene({
    triggerElement: '.an-hero-slider',
    duration: document.querySelector('.an-hero-slider').offsetHeight,
    triggerHook: 0,
  })
  .setPin('.an-hero-slider')
  // .setTween(timeline)
  .addTo(controller);


}

if (document.querySelectorAll(".an-hero-slider__item").length > 1) {
  heroSlider();
}


const discoverBtn = document.querySelectorAll("[data-discover-btn]");

if (discoverBtn) {
  discoverBtn.forEach((btn) => {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      window.scrollTo({
        top: document.querySelector(".an-hero-slider__item").offsetWidth - 300,
        behavior: 'smooth',
      });
    });
  });
}
