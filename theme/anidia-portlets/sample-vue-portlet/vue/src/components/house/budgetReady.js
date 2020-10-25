const budgetReady = {
  inject: ["house"],
  data() {
    return {
      budgetReadyForm: {
        name: "",
        lastname: "",
        phone: "",
        email: "",
        privacyPolicy: false,
        offersAndServices: false,
      }
    }
  },
  methods: {
    submitRequest() {
      // TODO: add validation
      this.house.submitUserContactInfo(this.budgetReadyForm)
    }
  },
  template: /*html*/`
  <div class="an-form an-wrapper">
    <form @submit.prevent="submitRequest">
      <div class="an-form__flex an-form__flex--2-cols">
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.name" type="text" class="an-input__field" placeholder="Nombre" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.lastname" type="text" class="an-input__field" placeholder="Apellidos" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.phone" type="text" class="an-input__field" placeholder="Teléfono" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.email" type="text" class="an-input__field" placeholder="Email" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="budgetReadyForm.privacyPolicy" class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> Acepto la política de privacidad </span>
            </label>
          </div>
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="budgetReadyForm.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
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