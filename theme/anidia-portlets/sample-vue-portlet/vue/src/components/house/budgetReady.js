const budgetReady = {
  inject: ["global", "house"],
  methods: {
    submitRequest() {
      // TODO
    }
  },
  template: /*html*/`
  <div class="an-form an-wrapper">
    <form @submit.prevent="submitRequest">
      <div class="an-form__flex an-form__flex--2-cols">
        <div class="an-input an-form__item">
          <input type="text" class="an-input__field" placeholder="Nombre" required="">
        </div>
        <div class="an-input an-form__item">
          <input type="text" class="an-input__field" placeholder="Apellidos" required="">
        </div>
        <div class="an-input an-form__item">
          <input type="text" class="an-input__field" placeholder="Teléfono" required="">
        </div>
        <div class="an-input an-form__item">
          <input type="text" class="an-input__field" placeholder="Email" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> Acepto la política de privacidad </span>
            </label>
          </div>
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input class="an-checkbox__input" type="checkbox" name="privacy-policy" id="offers-and-services">
            <label class="an-checkbox__label" for="offers-and-services">
              <span> Quiero recibir publicidad con nuevas ofertas y servicios </span>
            </label>
          </div>
        </div>
      </div>

      <button type="submit" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--check-simple mt-xl">
        <span>Continuar</span>
      </button>
      
    </form>
  </div>
  `,
}

export default budgetReady;