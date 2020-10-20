import coverageError from './coverageError';

const coverageForm = {
  components: {
    'coverage-error': coverageError,
  },
  data() {
    return {
      formData: {
        postalCode: "",
        municipality: "",
        street: "",
        number: "",
        houseType: "",
        privacyPolicy: false
      }
    }
  },
  inject: ["global", "house"],
  methods: {
    submitRequest() {
      // TODO: add validation
      this.house.submitCoverageData(this.formData)
    },
  },
  updated() {
    console.log(this.formData)
  },
  template: /*html*/
    `
    <template v-if="!house.state.coverageError">
      <div class="an-form an-wrapper">
        <p class="an-body-l-bold mb-xl">Rellena tu dirección para saber si tenemos cobertura en tu zona</p>
        <form @submit.prevent="submitRequest">
          <div class="an-form__flex an-form__flex--2-cols">
            <div class="an-input an-form__item">
              <input v-model="formData.postalCode" type="text" class="an-input__field" placeholder="Código Postal" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="formData.municipality" type="text" class="an-input__field" placeholder="Municipio" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="formData.street" type="text" class="an-input__field" placeholder="Calle" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="formData.number" type="text" class="an-input__field" placeholder="Número" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="formData.houseType" type="text" class="an-input__field" placeholder="Vivienda (bloque, escalera, piso, puerta)" required="">
            </div>
          </div>

          <div class="an-checkbox mt-xl">
            <input v-model="formData.privacyPolicy" class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
            <label class="an-checkbox__label" for="privacy-policy">
              <span> Acepto la política de privacidad </span>
            </label>
          </div>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Comprobar</span>
          </button>
          
        </form>
      </div>
    </template>
    <template v-else>
      <coverage-error :msg="house.state.coverageError" />
    </template>
    <div @click="setError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.')">Trigger First error</div>
    <div @click="setError('Hemos detectado que ya tienes gas natural instalado y mantenimiento contratado.')">Trigger Second error</div>
    `
  }

export default coverageForm;