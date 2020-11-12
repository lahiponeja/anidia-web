function heroSlider() {
  const dataSliderLinesList = document.querySelector("[data-slider-lines-list]");
  var slides = document.querySelectorAll(".an-hero-slider .journal-content-article");

  const linesHTML = []
  for(let i = 0; i < slides.length; i++) {
    linesHTML.push(`<div class="an-hero-slider__lines-item an-hero-slider__lines-item-${(i + 1)} ${ i === 0 && "an-hero-slider__lines-item--active"}" data-slide-line></div>`);
  }
  dataSliderLinesList.innerHTML = linesHTML.join("");

  var controller = new ScrollMagic.Controller({});


  if (document.querySelectorAll('#an-hero-slider__flag')) document.querySelectorAll('#an-hero-slider__flag')[0].parentElement.style.padding = 0;

  let sceneBgAnimate;
  for (var i = 0; i < slides.length; i++) {
    sceneBgAnimate = new ScrollMagic.Scene({
      triggerElement: slides[i],
      triggerHook: "onLeave",
      duration: '100%'
    })
    .setPin(slides[i], {pushFollowers: false})
    .addTo(controller)
    .on('end', function() {
      document.getElementById('banner').style.left = '0';
      document.querySelector('.an-hero-slider__lines').style.display = 'block';
    });
  }

  new ScrollMagic.Scene({
      triggerElement: ("#an-hero-slider__flag"),
      triggerHook: "onEnter",
      duration: '0'
    })
    .setPin("#an-hero-slider__flag")
    .addTo(controller)
    .on('start', function() {
      if(sceneBgAnimate) sceneBgAnimate.destroy();
      document.getElementById('banner').style.left = '-100%';
      document.querySelector('.an-hero-slider__lines').style.display = 'none';
    });
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
