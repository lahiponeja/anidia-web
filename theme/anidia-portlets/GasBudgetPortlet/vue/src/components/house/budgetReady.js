import phonePrefixMixin from "../../mixins/phonePrefixMixin"

const budgetReady = {
  mixins: [phonePrefixMixin],
  inject: ["global", "house"],
  data() {
    return {
      budgetReadyForm: {
        name: "",
        lastname: "",
        phone: "",
        email: "",
        privacyPolicy: false,
        offersAndServices: false,
      },
      sendingForm: false,
    }
  },
  methods: {
    submitRequest() {
      this.sendingForm = true
      this.house.submitUserContactInfo(this.budgetReadyForm).then((res) => {
        this.$emit("form-success")
        this.sendingForm = false
      }).catch((err)=>{
        console.error(err)
        this.sendingForm = false
      })
    },

    goBack() {
      const confirmation = confirm("¿Estás seguro de que quieres volver a calcular?")
      if(confirmation) {
        this.house.changeHouseStep('vivienda')
      }
    },
  },
  mounted () {
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template: /*html*/`
  <div class="an-form an-wrapper">
    <div v-if="sendingForm" class="an-funnel__white-overlay">
      <p class="an-h3">Cargando...</p>
    </div>

    <form @submit.prevent="submitRequest">
      <div class="an-form__flex an-form__flex--2-cols">
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.name" type="text" class="an-input__field" placeholder="Nombre*" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.lastname" type="text" class="an-input__field" placeholder="Apellidos*" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.phone" type="text" class="an-input__field" placeholder="Teléfono*" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="budgetReadyForm.email" type="email" class="an-input__field" placeholder="Email*" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="budgetReadyForm.privacyPolicy" class="an-checkbox__input privacy" type="checkbox" name="privacy-policy" id="privacy-policy" required="">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> He leído y acepto la política de privacidad *</span>
            </label>
          </div>
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="budgetReadyForm.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
            <label class="an-checkbox__label" for="offers-and-services">
              <span> Acepto recibir comunicaciones comerciales </span>
            </label>
          </div>
        </div>
      </div>

      <div class="an-form__flex an-form__flex--6-cols mb-xxl">
        <button @click="goBack" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
          <span>Volver a calcular</span>
        </button>

        <button type="submit" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--check-simple mt-xl">
          <span v-if="!sendingForm">Continuar</span>
          <span v-else>Enviando...</span>
        </button>
      </div>

    </form>
  </div>
  `,
}

export default budgetReady;