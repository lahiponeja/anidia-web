let cardsContainer = document.querySelectorAll('.extensible-cards');

cardsContainer.forEach(singleContainer => {
  const dots = singleContainer.querySelectorAll('.extensible-cards__dot');
  const cardContainer = singleContainer.querySelector('.portlet-body');
  const nCards = singleContainer.querySelectorAll('.journal-content-article').length;

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

  expandCards(singleContainer);

  init(singleContainer);

});


function init(singleContainer) {
  singleContainer.querySelectorAll('.an-card--simple .an-card--simple__text-expanded').forEach(e => {
    if (e.parentElement.querySelector('.an-card--simple__text-short') && e.parentElement.querySelector('.an-card--simple__text-short .an-card--simple__text-short__dots') === null) {
      let dot = document.createElement('p');
      dot.innerHTML = '...';
      dot.classList.add('an-card--simple__text-short__dots');
      e.parentElement.querySelector('.an-card--simple__text-short').append(dot);
    }
  });
}

function expandCards(singleContainer) {
  let cardsExpand = singleContainer.querySelectorAll('[data-expand-card]');
  let cardsClose = singleContainer.querySelectorAll('[data-close-card]');

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
        let cards = singleContainer.querySelectorAll('.journal-content-article');
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
