let cardsExpand = document.querySelectorAll('[data-expand-card]');
let cardsClose = document.querySelectorAll('[data-close-card]');
const windowWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
const easingOutQuint = (x, t, b, c, d) =>
  c * ((t = t / d - 1) * t * t * t * t + 1) + b;

const hasNativeSmoothScroll = testSupportsSmoothScroll();
const indicators = document.querySelectorAll('.extensible-cards__dot');
const scroller = document.querySelector('.extensible-cards__container');
indicators.forEach((indicator, i) => {
  indicator.addEventListener('click', e => {
    e.preventDefault()
    e.stopPropagation()
    setAriaPressed(i)
    const scrollLeft = Math.floor(scroller.scrollWidth * (i / 4))
    smoothScroll(scroller, scrollLeft, true)
  });
});

scroller.addEventListener('scroll', debounce(() => {
  let index = Math.round((scroller.scrollLeft / scroller.scrollWidth) * 4)
  setAriaPressed(index);
}, 200));

setAriaLabels();
expandCards();

function smoothScrollPolyfill (node, key, target) {
  const startTime = Date.now()
  const offset = node[key]
  const gap = target - offset
  const duration = 1000
  let interrupt = false

  const step = () => {
    const elapsed = Date.now() - startTime
    const percentage = elapsed / duration

    if (interrupt) {
      return
    }

    if (percentage > 1) {
      cleanup()
      return
    }

    node[key] = easingOutQuint(0, elapsed, offset, gap, duration)
    requestAnimationFrame(step)
  }

  const cancel = () => {
    interrupt = true
    cleanup()
  }

  const cleanup = () => {
    node.removeEventListener('wheel', cancel)
    node.removeEventListener('touchstart', cancel)
  }

  node.addEventListener('wheel', cancel, { passive: true })
  node.addEventListener('touchstart', cancel, { passive: true })

  step()

  return cancel
}

function testSupportsSmoothScroll () {
  let supports = false
  try {
    let div = document.createElement('div')
    div.scrollTo({
      top: 0,
      get behavior () {
        supports = true
        return 'smooth'
      }
    })
  } catch (err) {} // Edge throws an error
  return supports
}

function smoothScroll (node, topOrLeft, horizontal) {
  if (hasNativeSmoothScroll) {
    return node.scrollTo({
      [horizontal ? 'left' : 'top']: topOrLeft,
      behavior: 'smooth'
    })
  } else {
    return smoothScrollPolyfill(node, horizontal ? 'scrollLeft' : 'scrollTop', topOrLeft)
  }
}

function debounce(func, ms) {
	let timeout
	return () => {
		clearTimeout(timeout)
		timeout = setTimeout(() => {
			timeout = null
      func()
		}, ms)
	}
}

function setAriaLabels() {
  indicators.forEach((indicator, i) => {
    indicator.setAttribute('aria-label', `Scroll to item #${i + 1}`)
  })
}

function setAriaPressed(index) {
  indicators.forEach((indicator, i) => {
    indicator.setAttribute('aria-pressed', !!(i === index))
  })
}

function expandCards() {

  if (windowWidth > 798) {
    for (var i = 0 ; i < cardsExpand.length; i++) {
      cardsExpand[i].addEventListener("click", function(event){
        event.preventDefault()
        let parent = this.parentElement.querySelectorAll('.hide')
        let shortText = this.parentElement.querySelector('.an-card--simple__text-short');
        shortText.classList.add('hide');
        for (var j = 0 ; j < parent.length; j++) {
          parent[j].classList.remove('hide');
        }
        this.classList.add('hide');
        this.parentElement.classList.add('active');
      });
    }

    for (var i = 0 ; i < cardsClose.length; i++) {
      cardsClose[i].addEventListener("click", function(event){
        event.preventDefault()
        let parent = this.parentElement.querySelectorAll('.hide')
        for (var j = 0 ; j < parent.length; j++) {
          parent[j].classList.remove('hide');
        }
        this.parentElement.classList.remove('active')
        this.parentElement.querySelector('.an-icon--close-cross').classList.add('hide')
        this.parentElement.querySelector('.an-card--simple__text-expanded').classList.add('hide')
      });
    }
  } else {
    for (var i = 0 ; i < cardsExpand.length; i++) {
      cardsExpand[i].addEventListener("click", function(event){
        event.preventDefault()
        this.classList.add('hide');
        this.parentElement.querySelector('.an-card--simple__text-expanded').classList.remove('hide')
        this.parentElement.querySelector('.an-card--simple__text-short').classList.add('hide')
        this.parentElement.classList.add('active');
      });
    }
  }

}
