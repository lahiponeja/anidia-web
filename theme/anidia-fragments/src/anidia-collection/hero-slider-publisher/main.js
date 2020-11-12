function heroSlider() {
  const dataSliderLinesList = document.querySelector("[data-slider-lines-list]");
  var slides = document.querySelectorAll(".an-hero-slider .journal-content-article");

  const linesHTML = []
  for(let i = 0; i < slides.length; i++) {
    linesHTML.push(`<div class="an-hero-slider__lines-item an-hero-slider__lines-item-${(i + 1)} ${ i === 0 && "an-hero-slider__lines-item--active"}" data-slide-line></div>`);
  }
  dataSliderLinesList.innerHTML = linesHTML.join("");

  var controller = new ScrollMagic.Controller({});

  for (var i=0; i < slides.length; i++) {
    new ScrollMagic.Scene({
      triggerElement: slides[i],
      triggerHook: "onLeave",
      duration: '100%'
    })
    .setPin(slides[i], {pushFollowers: false})
    // .addIndicators({colorEnd: "tomato"})
    .addTo(controller);
  }

    new ScrollMagic.Scene({
      triggerElement: (".section--one"),
      triggerHook: "onLeave",
      duration: '100%'
    })
    .setPin(".section--one")
    // .addIndicators({
    //   name: ".section--one Pin",
    //   colorEnd: "dodgerblue"
    // })
    .addTo(controller);
}

if (document.querySelectorAll(".an-hero-slider__item").length > 1) {
  heroSlider();
  document.getElementById('banner').classList.add('more-elem');
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

