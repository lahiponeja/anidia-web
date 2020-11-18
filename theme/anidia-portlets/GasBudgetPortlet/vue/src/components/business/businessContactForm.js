import phonePrefixMixin from "../../mixins/phonePrefixMixin"

const businessContactForm = {
  mixins: [phonePrefixMixin],
  inject: ["global", "house"], // TODO: refactor once I tackle this business form.
  data() {
    return {
      businessFormData: {
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
  computed: {
    fullPhoneNumber() {
      return `${this.phonePrefix} ${this.phoneNumber}`
    }
  },
  methods: {
    submitRequest() {
      const payloadObj = Object.assign(this.businessFormData, {
        phone: this.fullPhoneNumber
      })

      this.sendingForm = true
      this.house.submitBusinessContactInfo(payloadObj).then((res) => {
        this.$emit("form-success")
        this.sendingForm = false
      }).catch((err)=>{
        console.error(err)
        this.sendingForm = false
      })
    },
  },
  mounted() {
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
          <input v-model="businessFormData.name" type="text" class="an-input__field" placeholder="Nombre" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="businessFormData.lastname" type="text" class="an-input__field" placeholder="Apellidos" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-select an-select--small-width mr-xs">
            <span class="an-select__icon an-icon--chevron-down"></span>
            <select v-model="phonePrefix" class="an-select__native" required>
              <option v-for="(option, index) in phonePrefixesOptions" :value="option.value">
                {{ option.text }}
              </option>
            </select>
          </div>
          
          <input v-model="phoneNumber" type="number" class="an-input__field" placeholder="Teléfono" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="businessFormData.email" type="email" class="an-input__field" placeholder="Email*" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="businessFormData.privacyPolicy" class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> Acepto la política de privacidad </span>
            </label>
          </div>
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="businessFormData.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
            <label class="an-checkbox__label" for="offers-and-services">
              <span> Quiero recibir publicidad con nuevas ofertas y servicios </span>
            </label>
          </div>
        </div>
      </div>
      
      <div class="an-form__flex an-form__flex--6-cols mb-xxl">
        <button @click="global.changeView('funnel')" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
          <span>Volver a calcular</span>
        </button>

        <button type="submit" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--check-simple mt-xl">
          <span v-if="!sendingForm">Continuar</span>
          <span v-else>Enviando...</span>
        </button>
      </div>

    </form>
  </div>`,
}

export default businessContactForm;