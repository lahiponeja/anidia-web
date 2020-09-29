let cardsExpand = document.querySelectorAll('[data-expand-card]');
let cardsClose = document.querySelectorAll('[data-close-card]');
const windowWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
const dots = document.querySelectorAll('.extensible-cards__dot');
const cardContainer = document.querySelector('.extensible-cards__container');
const nCards = document.querySelectorAll('.an-card--simple').length;

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

expandCards();

function expandCards() {

  if (windowWidth > 798) {
    cardsExpand.forEach(singleCard => {
      singleCard.addEventListener('click', function(event){
        event.preventDefault();
        let parent = this.parentElement.querySelectorAll('.hide');
        let shortText = this.parentElement.querySelector('.an-card--simple__text-short');
        shortText.classList.add('hide');
        for (var j = 0 ; j < parent.length; j++) {
          parent[j].classList.remove('hide');
        }
        this.classList.add('hide');
        this.parentElement.classList.add('active');
      });
    });

    cardsClose.forEach(singleCardClose => {
      singleCardClose.addEventListener('click', function(event){
        event.preventDefault();
        let parent = this.parentElement.querySelectorAll('.hide');
        for (var j = 0 ; j < parent.length; j++) {
          parent[j].classList.remove('hide');
        }
        this.parentElement.classList.remove('active');
        this.parentElement.querySelector('.an-icon--close-cross').classList.add('hide');
        this.parentElement.querySelector('.an-card--simple__text-expanded').classList.add('hide');
      });
    });
  } else {
    cardsExpand.forEach(singleCard => {
      singleCard.addEventListener('click', function(event){
        event.preventDefault();
        let cards = this.parentElement.parentElement.querySelectorAll('.an-card--simple');
        cards.forEach(card => {
          card.classList.add('active');
          card.querySelector('.an-card--simple__text-expanded').classList.remove('hide');
          card.querySelector('.an-card--simple__text-short').classList.add('hide');
          card.querySelector('[data-expand-card]').classList.add('hide');
        });
      });
    });
  }
}
