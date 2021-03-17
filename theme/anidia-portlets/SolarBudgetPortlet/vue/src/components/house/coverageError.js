const coverageError = {
  props: ['msg'],
  mounted () {
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template: /*html*/ `
    <div class="an-wrapper an-wrapper--med an-coverage-form__error">
      <p class="an-h3">{{ msg }}</p>
    </div>
  `
}

export default coverageError;
