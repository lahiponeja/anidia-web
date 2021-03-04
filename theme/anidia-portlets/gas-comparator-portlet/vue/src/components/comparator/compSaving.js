import results from "./results";
import phonePrefixMixin from "../../mixins/phonePrefixMixin"
import customSelect from "../../helpers/customSelect"

const compSaving = {
  mixins: [phonePrefixMixin],
  components: {
    results,
  },
  inject: ["global", "comparator"],
  data() {
    return {
      compSavingForm: {
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
  mounted() {
    window.dataLayer.push(this.comparator.getDatalayerKitchenStepInfo("FUNNEL - CONTRATACIÓN", "calculatorlead", "calculator"));
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
    customSelect()
  },
  methods: {
    submitRequest() {
      const payloadObj = Object.assign(this.compSavingForm, {
        phone: this.fullPhoneNumber
      })
      window.dataLayer.push(this.comparator.getDatalayerKitchenStepInfo("conversion", "calculatorlead", "calculator"));
      // TODO: add validation
      this.sendingForm = true
      this.comparator.submitUserContactInfo(payloadObj).then((res) => {
        this.sendingForm = false
        this.comparator.setLead(true)
        this.resetCompSavingForm()
      }).catch((err)=>{
        console.error(err)
        this.sendingForm = false
      })
    },

    resetCompSavingForm() {
      Object.assign(this.compSavingForm, {
        name: "",
        lastname: "",
        phone: "",
        email: "",
        privacyPolicy: false,
        offersAndServices: false,
      })
    },

    calculateAgain() {
      const confirmation = confirm("¿Estás seguro de que quieres volver a calcular?")
      if(confirmation) {
        this.comparator.resetComparatorStateData()
        this.comparator.changeStepComponent('comp-hot-water')
        this.global.changeView('funnel')
      }
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
  template: /*html*/`
  <div>
    <transition name="view">
      <template v-if="!comparator.state.leadSent">
        <div class="an-form an-wrapper">
        <div v-if="sendingForm" class="an-funnel__white-overlay">
          <p class="an-h3">Cargando...</p>
        </div>

          <form @submit.prevent="submitRequest">
            <div class="an-form__flex an-form__flex--2-cols">
              <div class="an-input an-form__item">
                <input v-model="compSavingForm.name" type="text" class="an-input__field" placeholder="Nombre*" required="">
              </div>
              <div class="an-input an-form__item">
                <input v-model="compSavingForm.lastname" type="text" class="an-input__field" placeholder="Apellidos*" required="">
              </div>
              <div class="an-input an-form__item">
                <div class="an-select an-select--flag an-select--small-width mr-xs data-select-container">
                  <template v-for="(option, index) in phonePrefixesOptions">
                    <img class="an-select__flag" v-if="option.value === phonePrefix" :src="option.flagUrl" />
                  </template>
                  <span class="an-select__icon an-icon--chevron-down"></span>
                  <select v-model="phonePrefix" @change="setFlag" class="an-select__native data-select-native" required>
                    <option v-for="(option, index) in phonePrefixesOptions" :value="option.value">
                      {{ option.text }}
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

                <input v-model="phoneNumber" v-on:input="reportPhoneValidation" v-on:invalid="setPhoneCustomValidationMessage" data-validation-message="Introduce tu número completo, sin espacios ni guiones." type="text" pattern="\\d{9}"  class="an-input__field" placeholder="Teléfono*" required="">
              </div>
              <div class="an-input an-form__item">
                <input v-model="compSavingForm.email" type="email" class="an-input__field" placeholder="Email*"  required="">
              </div>
              <div class="an-input an-form__item">
                <div class="an-checkbox mt-xl">
                  <input v-model="compSavingForm.privacyPolicy" class="an-checkbox__input privacy" type="checkbox" name="privacy-policy" id="privacy-policy" required="">
                  <label class="an-checkbox__label" for="privacy-policy">
                    <span> He leído y acepto <a href="#" @click.prevent="openModal('privacyPolicyModal')">la política de privacidad*</a> </span>
                  </label>
                </div>
              </div>
              <div class="an-input an-form__item">
                <div class="an-checkbox mt-xl">
                  <input v-model="compSavingForm.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
                  <label class="an-checkbox__label" for="offers-and-services">
                    <span> Acepto recibir <a href="#" @click.prevent="openModal('commercialModal')">comunicaciones comerciales</a> </span>
                  </label>
                </div>
              </div>
            </div>

            <div class="an-form__flex an-form__flex--6-cols mb-xxl">
              <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
                <span>Volver a calcular</span>
              </button>

              <button type="submit" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--check-simple mt-xl">
                <span v-if="sendingForm">Enviando...</span>
                <span v-else>Continuar</span>
              </button>
            </div>
          </form>
        </div>
      </template>
      <template v-else>
        <results />
      </template>
    </transition>
  </div>
  `,
}

export default compSaving;
