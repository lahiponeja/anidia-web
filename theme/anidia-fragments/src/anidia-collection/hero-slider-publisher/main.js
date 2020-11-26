function heroSlider() {
  var slides = document.querySelectorAll(".an-hero-slider .journal-content-article");

  setSliderLines(slides);

  function getSiblings(elem) {
    var siblings = [];
    var sibling = elem.parentNode.firstChild;
    var skipMe = elem;
    for ( ; sibling; sibling = sibling.nextSibling )
      if ( sibling.nodeType == 1 && sibling != elem )
          siblings.push( sibling );
    return siblings;
  }

  function checkLeftCollision() {
    const slideLine = document.querySelectorAll("[data-slide-line]");

    slides.forEach((slide, i) => {
      const info = slide.querySelector('.an-hero-slider__info')
      const opacityNum = window.getComputedStyle(info).getPropertyValue("opacity")
      const slideRect = slide.getBoundingClientRect();
      if(slideLine.length) {
        // if(Math.abs(slideRect.top).toFixed(0) < 300) {
        if(opacityNum > 0) {
        const slideLineSiblings = getSiblings(slideLine[i]);

          slideLine[i].classList.add('an-hero-slider__lines-item--active');

          slideLineSiblings.forEach((siblingLine) => {
            siblingLine.classList.remove('an-hero-slider__lines-item--active');
          });
        }
      }
    });
  }

  gsap.registerPlugin(ScrollTrigger);

  var tl = new TimelineMax();
  tl.to(".first.journal-content-article .an-hero-slider__image", { duration: 30,  opacity: 0})
  tl.to(".first.journal-content-article .an-hero-slider__info", { duration: 27,  opacity: 0, y:-100}, "-=30")

  if(document.querySelectorAll(".inner.journal-content-article").length > 0) {
    Array.from(document.querySelectorAll(".inner.journal-content-article")).forEach((elem, index) => {
      const img = elem.querySelector(".an-hero-slider__image")
      const info = elem.querySelector(".an-hero-slider__info")

      tl.to(img, {duration: 30,  opacity: 1})
      tl.to(info, {duration: 27, opacity: 1, y: 0}, "-=30")

      tl.to(img, {duration: 30,  opacity: 0})
      tl.to(info, {duration: 27, opacity: 0, y:-100}, "-=30")
    });
  }

  tl.to(".last.journal-content-article .an-hero-slider__image", {duration: 30,  opacity: 1})
  tl.to(".last.journal-content-article .an-hero-slider__info", {duration: 27, opacity: 1, y:0}, "-=30")

  const controller = new ScrollMagic.Controller();

  new ScrollMagic.Scene({
    duration: '100%',
    triggerHook: 0
  })
  .setPin('.an-hero-slider .portlet-body')
  .addIndicators({name: "pin scene", colorStart: "tomato"})
  .setTween(tl)
  .addTo(controller)
  .on('enter', startBgAnimate)
  .on('update', function(){
    checkLeftCollision()
  })
  .on('leave', endBgAnimate);

  function endBgAnimate () {
    document.getElementById('banner').style.opacity = '0';
    document.querySelector('.an-hero-slider__lines').style.opacity = '0';
  }

  function startBgAnimate () {
    if(document.getElementById('banner') && document.querySelector('.an-hero-slider__lines')) {
      document.getElementById('banner').style.opacity = '1';
      document.querySelector('.an-hero-slider__lines').style.opacity = '1';
    }
  }

}

function setSliderLines(slides) {
  const dataSliderLinesList = document.querySelector("[data-slider-lines-list]");

  slides.forEach((el, i) => {
    if (i === 0) el.classList.add('first');
    else {
      if (i === slides.length - 1) el.classList.add('last');
      else el.classList.add('inner');
    }
  });

  const linesHTML = []
  for(let i = 0; i < slides.length; i++) {
    linesHTML.push(`<div class="an-hero-slider__lines-item an-hero-slider__lines-item-${(i + 1)} ${ i === 0 && "an-hero-slider__lines-item--active"}" data-slide-line></div>`);
  }
  dataSliderLinesList.innerHTML = linesHTML.join("");
}

if (document.querySelectorAll(".an-hero-slider__item").length > 1) {
  heroSlider();
  if(document.getElementById('banner')) {
    document.getElementById('banner').classList.add('more-elem');
  }
}

const discoverBtn = document.querySelectorAll("[data-discover-btn]");

if (discoverBtn) {
  discoverBtn.forEach((btn) => {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      window.scrollTo({
        top: document.querySelector(".an-hero-slider").offsetHeight + 400,
        behavior: 'smooth',
      });
    });
  });
}
