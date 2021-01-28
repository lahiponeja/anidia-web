/**
 * Accordion with keyboard support
 * Use:
 *  <button data-accordion aria-expanded="<true|false>" aria-controls="idDelElementoADesplegar">Despliégame</button>
 *  <div id="elementoADesplegar">...</div>
 */

const Accordion = {
  KEYCODE: 32, // Space key
  SELECTOR: 'accordion',

  init: function(selector = this.SELECTOR) {
    this.SELECTOR = selector;
    document.querySelectorAll(`[data-${this.SELECTOR}]`).forEach(item => {
      item.addEventListener('click', e => this.handleClick.call(this, e, item));
      item.addEventListener('keydown', e => this.handleKeydown.call(this, e, item));

      if (item.getAttribute('aria-expanded') === 'false') {
        this.toggleExpand(item);
      }
    });
  },

  handleClick: function(e, item) {
    e.preventDefault();
    document.documentElement.classList.add('with-mouse');
    this.toggleExpand(item);
  },

  handleKeydown: function(e, item) {
    document.documentElement.classList.remove('with-mouse');
    if (e.keyCode === this.KEYCODE) {
      this.toggleExpand(item);
      e.stopPropagation();
      e.preventDefault();
    }
  },

  toggleExpand: function(item) {
    item.classList.toggle('closed');
    document.querySelector(`#${item.getAttribute('aria-controls')}`).classList.toggle('closed');

    if (item.classList.contains('closed')) {
      item.setAttribute('aria-expanded', 'false');
    } else {
      item.setAttribute('aria-expanded', 'true');
    }
  }
};

Accordion.init();


/**
 * Custom input number
 * Use:
 *  <div class="an-input-number" data-inumber>
 *    <button type="button" data-inumber-decrement aria-label="Decrement" class="an-input-number__button disabled">-</button>
 *    <input type="number" min="0" value="0" class="an-input-number__input"/>
 *    <button type="button" data-inumber-increment aria-label="Increment" class="an-input-number__button">+</button>
 *  </div>
 */

const InputNumber = {
  SELECTOR: 'inumber',

  init: function(selector = this.SELECTOR) {
    this.SELECTOR = selector;
    document.querySelectorAll(`[data-${this.SELECTOR}]`).forEach(item => {
      let input = item.querySelector('input');
      item.querySelector(`[data-inumber-decrement]`).addEventListener('click', e => this.handleClickDecrement.call(this, e, input));
      item.querySelector(`[data-inumber-increment]`).addEventListener('click', e => this.handleClickIncrement.call(this, e, input));
    });
  },

  handleClickDecrement: function(e, input) {
    e.preventDefault();
    this.decrement(input);
  },

  handleClickIncrement: function(e, input) {
    e.preventDefault();
    this.increment(input);
  },

  decrement: function(input) {
    input.value = parseInt(input.value) - 1;
    this.disbaledDecrement(input);
  },

  increment: function(input) {
    input.value = parseInt(input.value) + 1;
    this.disbaledDecrement(input);
  },

  disbaledDecrement: function(input) {
    const decrementButton = input.parentNode.querySelector(`[data-inumber-decrement]`);
    if (input.value === '0') {
      decrementButton.classList.add('disabled');
    } else {
      decrementButton.classList.remove('disabled');
    }
  }
};

InputNumber.init();

/**
 * Slider cars
 * Use:
 *  <section data-slider-cards>
 *    <div data-container>
 *      <div data-card>Card 1... </div>
 *      <div data-card>Card 2... </div>
 *    </div>
 *    <ul class="an-pack-cards__dots">
 *      <li class="an-pack-cards__dot" aria-pressed="true" data-dot></li>
 *      <li class="an-pack-cards__dot" data-dot></li>
 *    </ul>
 *  </section>
 */

const SliderCards = {
  SELECTOR: 'slider-cards',

  init: function() {
    document.querySelectorAll(`[data-${this.SELECTOR}]`).forEach(slider => {
      const dots = slider.querySelectorAll(`[data-dot]`);
      const cardsContainer = slider.querySelector(`[data-container]`);
      const nCards = slider.querySelectorAll(`[data-card]`).length;

      dots.forEach((dot, i) => {
        dot.addEventListener('click', e => this.handleClick.call(this, e, i, dot, cardsContainer, nCards));
      });
      cardsContainer.addEventListener('scroll', e => this.handleScroll.call(this,cardsContainer, nCards));
    });
  },

  handleClick: function(e, i, dot, cardContainer, nCards) {
    e.preventDefault();
    e.stopPropagation();
    const scrollLeft = Math.floor(cardContainer.scrollWidth * (i / nCards));
    this.smoothScroll(cardContainer, scrollLeft);
    this.setAriaLabels(dot, i);
  },

  handleScroll: function(cardContainer, nCards) {
    let index = Math.round((cardContainer.scrollLeft / cardContainer.scrollWidth) * nCards);
    this.setAriaPressed(index, cardContainer);
  },

  smoothScroll: function (node, topOrLeft) {
    return node.scrollTo({
      ['left']: topOrLeft,
      behavior: 'smooth'
    });
  },

  setAriaLabels: function(dot, i) {
    dot.setAttribute('aria-label', `Scroll to item #${i + 1}`);
  },

  setAriaPressed: function(index, cardContainer) {
    const dots = cardContainer.parentNode.querySelectorAll(`[data-dot]`);
    dots.forEach((dot, i) => {
      dot.setAttribute('aria-pressed', !!(i === index));
    });
  }
};

SliderCards.init();
