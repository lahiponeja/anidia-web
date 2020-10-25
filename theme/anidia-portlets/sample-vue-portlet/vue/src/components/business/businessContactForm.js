const businessContactForm = {
  inject: ["global", "house"], // TODO: refactor once I tackle this business form.
  methods: {
    submitRequest() {
      // TODO
    }
  },
  template: /*html*/`
  <div class="an-form an-wrapper">
        <form @submit.prevent="submitRequest">
          
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
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
          </div>


          <p class="an-body-l-bold mb-xl">¿Cómo prefieres que te contactemos?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal">
            <div class="an-radio an-form__item">
              <input class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="telefono">
              <label class="an-radio__label" for="telefono">
                <span>
                  Teléfono
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="email">
              <label class="an-radio__label" for="email">
                <span>
                  Email
                </span>
              </label>
            </div>
          </div>

          <div class="an-checkbox">
            <input class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> Acepto la política de privacidad </span>
            </label>
          </div>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Continuar</span>
          </button>
          
        </form>
      </div>`,
}

export default businessContactForm;