const windowWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
const dots = document.querySelectorAll('.an-stepper__dot');
const cardContainer = document.querySelector('.an-stepper__tab__container');
const nCards = document.querySelectorAll('.an-stepper__tab__content').length;

dots.forEach((dot, i) => {
  dot.addEventListener('click', e => {
    e.preventDefault();
    e.stopPropagation();
    setAriaPressed(i);
    const scrollLeft = Math.floor(cardContainer.scrollWidth * (i / nCards));
    smoothScroll(cardContainer, scrollLeft);
  });
});

cardContainer.addEventListener('scroll', () => {
  let index = Math.round((cardContainer.scrollLeft / cardContainer.scrollWidth) * nCards);
  setAriaPressed(index);
}, 200);

setAriaLabels();

function smoothScroll (node, topOrLeft) {
  return node.scrollTo({
    ['left']: topOrLeft,
    behavior: 'smooth'
  });
}

function setAriaLabels() {
  dots.forEach((dot, i) => {
    dot.setAttribute('aria-label', `Scroll to item #${i + 1}`);
  });
}

function setAriaPressed(index) {
  dots.forEach((dot, i) => {
    dot.setAttribute('aria-pressed', !!(i === index));
  });
}
