function packCards() {
  const dots = document.querySelectorAll('.an-pack-cards__dot');
  const cardContainer = document.querySelector('.an-pack-cards .portlet-body');
  const nCards = document.querySelectorAll('.an-card--pack').length;

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

  if (nCards === 3 || cardContainer.classList.contains('small')) {
    document.querySelectorAll('.an-card--pack').forEach(card => {
      card.classList.remove('an-card--pack--big');
      card.classList.add('an-card--pack--small');
    })
    document.querySelector('.an-pack-cards__cards .portlet-body').classList.add('small');
  }
}

if (document.querySelector(".an-pack-cards")) {
  packCards();
  document.querySelectorAll('.an-card--pack').forEach(card => {
    if (card.classList.contains('featured')) card.parentElement.classList.add('featured');
  })
}
