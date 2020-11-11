const compError = {
  inject: ["global", "comparator"],
  mounted () {
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  methods: {
    startOver() {
      this.global.changeView('funnel')
      this.comparator.changeStepComponent('comp-hot-water')
    },
  },
  template: /*html*/ `
    <div class="an-wrapper an-wrapper--med an-coverage-form__error align-items-center text-center">
      <p class="an-h3">Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.</p>

      <div class="an-form__flex an-form__flex--6-cols mb-xxl">
        <button @click="startOver" type="button" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
          <span>Volver a calcular</span>
        </button>
      </div>
    </div>
  `
}

export default compError;
