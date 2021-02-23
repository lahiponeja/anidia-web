import phonePrefixMixin from "../../mixins/phonePrefixMixin"
import customSelect from "../../helpers/customSelect"

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

    openModal(name) {
      this.global.changeModalStatus({
        open: true,
        options: {
          componentName: name
        }
      })
    },
    setPhoneCustomValidationMessage(event) {
      var input = event.target;
      input.setCustomValidity('');
      if(!input.validity.valid) {
        input.setCustomValidity(input.dataset.validationMessage);
      }
    },
    reportPhoneValidation(event) {
      var input = event.target;
      input.reportValidity();
    }
  },
  mounted() {
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
    customSelect()
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
          <div class="an-select an-select--flag an-select--small-width mr-xs data-select-container">
            <template v-for="(option, index) in phonePrefixesOptions">
              <img class="an-select__flag" v-if="option.value === phonePrefix" :src="option.flagUrl" />
            </template>
            <span class="an-select__icon an-icon--chevron-down"></span>
            <select v-model="phonePrefix" @change="setFlag" class="an-select__native data-select-native" required>
              <option v-for="(option, index) in phonePrefixesOptions" :value="option.value">
                <span>{{ option.text }}</span>
              </option>
            </select>
            <div class="an-select__custom data-select-custom">
              <div class="an-select__custom-trigger data-select-custom-trigger">
                <img class="an-select__flag" :src="activeFlag" />
                <span>+34</span>
              </div>
              <div class="an-select__custom-options data-select-custom-options">
                <template v-for="(option, index) in phonePrefixesOptions">
                  <div @click="setPrefix(option.value, option.flagUrl)" class="an-select__custom-option an-select__custom-option-flag" :class="'an-select__custom-option-flag--' + (option.name)" :data-value="option.value">
                    {{ option.text }}
                  </div>
                </template>
              </div>
            </div>
          </div>

          <input v-model="phoneNumber" v-on:input="reportPhoneValidation" v-on:invalid="setPhoneCustomValidationMessage" data-validation-message="Introduce un número de teléfono, sin espacios ni guiones." type="text" pattern="\\d{9}" class="an-input__field" placeholder="Teléfono" required="">
        </div>
        <div class="an-input an-form__item">
          <input v-model="businessFormData.email" type="email" class="an-input__field" placeholder="Email*" required="">
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="businessFormData.privacyPolicy" class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy" required="">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> He leído y acepto <a href="#" @click.prevent="openModal('privacyPolicyModal')">la política de privacidad</a> </span>
            </label>
          </div>
        </div>
        <div class="an-input an-form__item">
          <div class="an-checkbox mt-xl">
            <input v-model="businessFormData.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
            <label class="an-checkbox__label" for="offers-and-services">
              <span> Acepto recibir <a href="#" @click.prevent="openModal('commercialModal')">comunicaciones comerciales</a> </span>
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