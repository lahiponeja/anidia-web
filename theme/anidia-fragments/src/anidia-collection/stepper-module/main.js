function stepperModule() {
  let tabs = document.querySelectorAll('.an-stepper__tab');

  tabs.forEach(tab => {
    const dots = tab.querySelectorAll('.an-stepper__dot');
    const cardContainer = tab.querySelector('.an-stepper__tab__container');
    const nCards = tab.querySelectorAll('.an-stepper__tab__content').length;

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
  })
}

if (document.querySelector(".an-stepper__dropdown")) {
  stepperModule();
  document.querySelector(`#tab-${document.querySelector('.an-stepper__dropdown').value}`).parentElement.classList.add('active');
  document.querySelector('.an-stepper__dropdown').addEventListener('change', () => {
    let id = document.querySelector('.an-stepper__dropdown').value;
    document.querySelector(`#tab-${id}`).checked = true;
    document.querySelectorAll('.an-stepper__tab').forEach((el) => {
      el.classList.remove('active');
    })
    document.querySelector(`#tab-${id}`).parentElement.classList.add('active');
  });
}
