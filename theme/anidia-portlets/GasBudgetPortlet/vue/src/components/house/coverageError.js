const coverageError = {
  props: ['msg'],
  mounted () {
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    })
  },
  template: /*html*/ `
    <div class="an-wrapper an-wrapper--med an-coverage-form__error">
      <p class="an-h3">{{ msg }}</p>
      <!-- TODO: Incluir el módulo Destacado Calculadora de Ahorro -->
    </div>
  `
}

export default coverageError;
