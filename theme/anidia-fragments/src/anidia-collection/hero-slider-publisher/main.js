function heroSlider() {
  const dataSliderLinesList = document.querySelector("[data-slider-lines-list]");
  var slides = document.querySelectorAll(".an-hero-slider .journal-content-article");

  slides.forEach((el, i) => {
    if (i === 0) el.classList.add('one');
    else {
      if (i === slides.length - 1) el.classList.add('three');
      else el.classList.add('two');
    }
  });

  const linesHTML = []
  for(let i = 0; i < slides.length; i++) {
    linesHTML.push(`<div class="an-hero-slider__lines-item an-hero-slider__lines-item-${(i + 1)} ${ i === 0 && "an-hero-slider__lines-item--active"}" data-slide-line></div>`);
  }
  dataSliderLinesList.innerHTML = linesHTML.join("");


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
    const slideRect = slide.getBoundingClientRect();
    if(slideLine.length) {
      if(Math.abs(slideRect.top).toFixed(0) < 300) {
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
  tl
  .to(".one .an-hero-slider__image", {y: 0, duration: 20, display: 'none'})
  .to(".one .an-hero-slider__info", {y: 0, duration: 0, display: 'none'})

  .to(".two .an-hero-slider__image", {y: 0, duration: 10, display: 'block'})
  .to(".two .an-hero-slider__info", {y: 0, duration: 20, display: 'block'})

  .to(".two .an-hero-slider__image", {y: 0, duration: 10, display: 'none'})
  .to(".two .an-hero-slider__info", {y: 0, duration: 0, display: 'none'})

  .to(".three .an-hero-slider__image", {y: 0, duration: 20, display: 'block'})
  .to(".three .an-hero-slider__info", {y: 0, duration: 20, display: 'block'});

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
    document.getElementById('banner').style.opacity = '1';
    document.querySelector('.an-hero-slider__lines').style.opacity = '1';
  }

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
        top: document.querySelector(".an-hero-slider").offsetHeight + 400,
        behavior: 'smooth',
      });
    });
  });
}

