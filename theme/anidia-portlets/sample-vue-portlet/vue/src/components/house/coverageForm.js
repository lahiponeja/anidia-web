import coverageError from './coverageError';
import Autocomplete from '@trevoreyre/autocomplete-vue';

const coverageForm = {
  components: {
    'coverage-error': coverageError,
    Autocomplete
  },
  data() {
    return {
      // municipalityNameID = provinceCode + municipalityNameID
      formData: {
        postalCode: "",
        municipalityName: "",
        municipalityId: "",
        provinceId: "",
        provMunId: "",
        addressKind: "",
        addressName: "",
        street: "",
        gateId: "",
        houseType: "",
        privacyPolicy: false
      },

      selected: {
        fieldArr: "",
        fieldKey: "",
      }
    }
  },
  inject: ["global", "house"],
  methods: {
    submitRequest() {
      // TODO: add validation
      this.house.submitCoverageData(this.formData)
    },
    // setError(error) {
      
    // }

    /*************************************
     * GENERAL
    *************************************/
    search(index) {
      if (index.length < 1) { return [] }
      return this[this.selected.fieldArr].filter(pc => {
        return pc[this.selected.fieldKey].toLowerCase().startsWith(index.toLowerCase())
      })
    },

    getResultValue(result) {
      return result[this.selected.fieldKey]
    },

    setActiveField(arr, key) {
      this.selected.fieldArr = arr
      this.selected.fieldKey = key
    },

    /*************************************
     * POSTAL CODES
    *************************************/
    searchPostalCodes(index) {
      if (index.length < 1) { return [] }
      return this.postalCodesArr.filter(pc => {
        return pc.postalCode.startsWith(index)
      })
    },
    onSubmitPostalCode(result) {
      const { postalCode } = result
      this.formData.postalCode = postalCode
      // Get municipalities
      this.house.getMunicipalities(postalCode)
    },


    /*************************************
     * MUNICIPALITIES
    *************************************/
    onSubmitMunicipalities(result) {
      const { municipalityId, municipalityName, provinceId } = result
      this.formData.provMunId =  provinceId + municipalityId

      this.formData.municipalityName = municipalityName
      this.formData.municipalityId = municipalityId
      this.formData.provinceId = provinceId

      this.house.getAddresses(this.formData.provMunId, this.formData.postalCode)
    },

    /*************************************
     * ADDRESSES
    *************************************/
    onSubmitAddresses(result) {
      const { kind, name } = result

      this.formData.addressKind = kind
      this.formData.addressName = name

      this.house.getEstates(this.formData.provMunId, this.formData.postalCode, kind, name)
    },

    /*************************************
     * ESTATES
    *************************************/
    onSubmitEstates(result) {
      const { gateId } = result
      this.formData.gateId = gateId
      this.house.getProperties(gateId)
    },

    /*************************************
     * PROPERTIES
    *************************************/
    onSubmitProperties(result) {
      const { address } = result
      this.formData.houseType = address
    },
  },
  computed: {
    postalCodesArr() {
      return Array.from(this.house.state.autocompData.postalCodes);
    },
    municipalitiesArr() {
      return Array.from(this.house.state.autocompData.municipalities);
    },
    addressesArr() {
      return Array.from(this.house.state.autocompData.addresses);
    },
    estatesArr() {
      return Array.from(this.house.state.autocompData.estates);
    },
    propertiesArr() {
      return Array.from(this.house.state.autocompData.properties);
    },

  },
  mounted() {
    console.log(this.house.state.autocompData.postalCodes);
  },
  template: /*html*/
    `<div>
    
    <h4>Selected field {{ selected.fieldKey }}</h4>

    <h5>Municipalities {{ house.state.autocompData.municipalities }}</h5>
    <h5>Addresses {{ house.state.autocompData.addresses }}</h5>
    <h5>Estates {{ house.state.autocompData.estates }}</h5>
    <h5>Properties {{ house.state.autocompData.properties }}</h5>

    <ul>
      <li>Postal Code: {{ formData.postalCode }}</li>
      <li>Municipio: {{ formData.municipalityName }}</li>
      <li>Calle: {{ formData.addressName }}</li>
      <li>Número: {{ formData.gateId }}</li>
      <li>Vivienda: {{ formData.houseType }}</li>
      <li>Acepto la política de privacidad: {{ formData.privacyPolicy }}</li>
    </ul>

    <div v-if="!house.state.coverageError">
      <div class="an-form an-wrapper">
        <p class="an-body-l-bold mb-xl">Rellena tu dirección para saber si tenemos cobertura en tu zona</p>
        <form @submit.prevent="submitRequest">
          <div class="an-form__flex an-form__flex--2-cols">
            <div class="an-input an-form__item">
              <!-- <input v-model="formData.postalCode" type="text" class="an-input__field" placeholder="Código Postal" required=""> -->
              <autocomplete @click="setActiveField('postalCodesArr', 'postalCode')" :debounce-time="700" @submit="onSubmitPostalCode" :search="searchPostalCodes" :get-result-value="getResultValue" class="an-input__field" placeholder="Código Postal"></autocomplete>
            </div>
            <div class="an-input an-form__item">
              <!-- <input v-model="formData.municipality" type="text" class="an-input__field" placeholder="Municipio" required=""> -->
              <autocomplete @click="setActiveField('municipalitiesArr', 'municipalityName')" @submit="onSubmitMunicipalities" :search="search" :get-result-value="getResultValue" class="an-input__field" placeholder="Municipios"></autocomplete>
              </div>
              <div class="an-input an-form__item">
              <!-- <input v-model="formData.street" type="text" class="an-input__field" placeholder="Calle" required=""> -->
              <autocomplete @click="setActiveField('addressesArr', 'name')" @submit="onSubmitAddresses" :search="search" :get-result-value="getResultValue" class="an-input__field" placeholder="Calle"></autocomplete>
              </div>
              <div class="an-input an-form__item">
              <!-- <input v-model="formData.number" type="text" class="an-input__field" placeholder="Número" required=""> -->
              <autocomplete @click="setActiveField('estatesArr', 'gateId')" @submit="onSubmitEstates" :search="search" :get-result-value="getResultValue" class="an-input__field" placeholder="Número"></autocomplete>
              </div>
              <div class="an-input an-form__item">
              <!-- <input v-model="formData.houseType" type="text" class="an-input__field" placeholder="Vivienda (bloque, escalera, piso, puerta)" required=""> -->
              <autocomplete @click="setActiveField('propertiesArr', 'address')" @submit="onSubmitProperties" :search="search" :get-result-value="getResultValue" class="an-input__field" placeholder="Vivienda (bloque, escalera, piso, puerta)"></autocomplete>
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
    </div>
    <div v-else>
      <coverage-error :msg="house.state.coverageError" />
    </div>
    <div @click="setError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.')">Trigger First error</div>
    <div @click="setError('Hemos detectado que ya tienes gas natural instalado y mantenimiento contratado.')">Trigger Second error</div>
    </div>
    `
  }

export default coverageForm;