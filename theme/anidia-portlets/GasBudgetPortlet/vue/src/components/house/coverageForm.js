import coverageError from './coverageError';
import Autocomplete from '@trevoreyre/autocomplete-vue';
import { required } from 'vuelidate/lib/validators';

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
        name: "",
        number:"",
        houseType: "",
        status: "",
        privacyPolicy: false
      },

      selected: {
        fieldArr: "",
        fieldKey: "",
      },

      statusCodes: {
        validArr: ["01", "02", "03", "04", "05", "10", "10a", "10b"],
        invalidArr: ["06", "07", "08ª", "08b", "09"],
      },

      loadingMunicipalities: false,
      loadingAddressess: false,
      loadingEstates: false,
      loadingProperties: false,
    }
  },

  validations: {
    formData: {
      postalCode: {
        required
      },
      municipalityName: {
        required
      },
      municipalityId: {
        required
      },
      provinceId: {
        required
      },
      provMunId: {
        required
      },
      addressKind: {
        required
      },
      addressName: {
        required
      },
      number: {
        required
      },
      houseType: {
        required
      },
      status: {
        required
      },
      privacyPolicy: {
        required
      }
    }
  },

  inject: ["house"],
  methods: {
    submitRequest() {
      if(this.isValidStatusCode) {
        console.log("submited")
        this.house.setPostalCode(this.formData.postalCode);
        this.house.changeHouseStep('vivienda');
      } else {
        this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.')
      }
    },

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
    // onSubmitPostalCode(e) {
    //   const postalCode = e.target.value
    //   this.formData.postalCode = postalCode
    //   // Save object in the store
    //   this.house.setCoverageData("postalCode", { postalCode })
    //   // Get municipalities
    //   this.loadingMunicipalities = true,
    //   this.house.getMunicipalities(postalCode)
    //     .then((res) => { this.loadingMunicipalities = false })
    //     .catch((err) => { this.loadingMunicipalities = false })
    // },
    onSubmitPostalCode(result) {
      const { postalCode } = result
      this.formData.postalCode = postalCode
      // Save object in the store
      this.house.setCoverageData("postalCode", { postalCode })
      // Get municipalities
      this.loadingMunicipalities = true,
      this.house.getMunicipalities(postalCode)
        .then((res) => { this.loadingMunicipalities = false })
        .catch((err) => { this.loadingMunicipalities = false })
    },


    /*************************************
     * MUNICIPALITIES
    *************************************/
    onSubmitMunicipalities(result) {
      const { municipalityId, municipalityName, provinceId } = result
      this.formData.provMunId =  provinceId + municipalityId

      // Save object in the store
      this.house.setCoverageData("postalCode", { municipalityId, municipalityName, provinceId })

      this.formData.municipalityName = municipalityName
      this.formData.municipalityId = municipalityId
      this.formData.provinceId = provinceId

      this.loadingAddressess = true
      this.house.getAddresses(this.formData.provMunId, this.formData.postalCode)
        .then((res) => { this.loadingAddressess = false })
        .catch((err) => { this.loadingAddressess = false })

    },

    /*************************************
     * ADDRESSES
    *************************************/
    onSubmitAddresses(result) {
      const { kind, name } = result

      // Save object in the store
      this.house.setCoverageData("estate", {
        addressKind: kind,
        addressName: name
      })

      this.formData.addressKind = kind
      this.formData.addressName = name

      this.loadingEstates = true
      this.house.getEstates(this.formData.provMunId, this.formData.postalCode, kind, name)
        .then((res) => { this.loadingEstates = false })
        .catch((err) => { this.loadingEstates = false })
    },

    /*************************************
     * ESTATES
    *************************************/
    searchEstates(index) {
      if (index.length < 1) { return [] }
      return this.estatesArr.filter(pc => {
        return pc.number.startsWith(index)
      })
    },

    onSubmitEstates(result) {
      const { gateId, number } = result

      // Save object in the store
      this.house.setCoverageData("estate", { gateId, number })

      this.formData.number = number

      this.loadingProperties = true
      this.house.getProperties(gateId)
        .then((res) => { this.loadingProperties = false })
        .catch((err) => { this.loadingProperties = false })
    },

    /*************************************
     * PROPERTIES
    *************************************/
    onSubmitProperties(result) {
      const {
        address,
        propertyId,
        status,
        contractStatus,
        floor,
        block,
        ladder,
        door} = result

      // Save object in the store
      this.house.setCoverageData("property", {
        address: address || "",
        propertyId: propertyId || "",
        status: status || "",
        contractStatus: contractStatus || "",
        floor: floor || "",
        block: block || "",
        ladder: ladder || "",
        door: door || ""
      })

      this.formData.houseType = address
      this.formData.status = status
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

    isValidStatusCode() {
      return this.statusCodes.validArr.indexOf(this.formData.status) != -1
    },

    btnDisabled () {
      return this.$v.$invalid
    }

  },

  mounted() {
    console.log(this.house.state.autocompData.postalCodes);
  },
  template: /*html*/
  `<div>

<!--
    <div>{{ house.state.coverageData.postalCode }}</div>
    <div>{{ house.state.coverageData.estate }}</div>
    <div>{{ house.state.coverageData.property }}</div>


    <br /><br /><br />

    <h3>isValidStatusCode: {{ isValidStatusCode }}</h3>
    <h3>Selected field {{ selected.fieldKey }}</h3>

    <h5>Municipalities {{ house.state.autocompData.municipalities }}</h5>
    <h5>Addresses {{ house.state.autocompData.addresses }}</h5>
    <h5>Estates {{ house.state.autocompData.estates }}</h5>
    <h5>Properties {{ house.state.autocompData.properties }}</h5>

    <ul>
      <li>Postal Code: {{ formData.postalCode }}</li>
      <li>Municipio: {{ formData.municipalityName }}</li>
      <li>Calle: {{ formData.addressName }}</li>
      <li>Número: {{ formData.number }}</li>
      <li>Vivienda: {{ formData.houseType }}</li>
      <li>Acepto la política de privacidad: {{ formData.privacyPolicy }}</li>
      <li>--------------</li>
      <li>Status: {{ formData.status }}</li>
    </ul>
-->
    <template v-if="!house.state.coverageError">
      <div class="an-form an-wrapper">
        <p class="an-body-l-bold mb-xl">Rellena tu dirección para saber si tenemos cobertura en tu zona</p>
        <form @submit.prevent="submitRequest">
          <div class="an-form__flex an-form__flex--2-cols">

         <!-- <div class="an-input an-form__item">
            <input @blur="onSubmitPostalCode($event)" class="an-input__field">
          </div> -->

            <!--INPUT FIELD: formData.postalCode -->
           <div class="an-input an-form__item">
              <autocomplete
                :debounce-time="700"
                @submit="onSubmitPostalCode"
                :search="searchPostalCodes"
                :get-result-value="getResultValue"
                placeholder="Código Postal"
                style="width: 100%;"
                auto-select
                >
                <template
                  #default="{
                    rootProps,
                    inputProps,
                    inputListeners,
                    resultListProps,
                    resultListListeners,
                    results,
                    resultProps
                  }"
                >
                  <div v-bind="rootProps">
                    <input
                      v-model="formData.postalCode"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="setActiveField('postalCodesArr', 'postalCode')"
                      required=""
                    >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="result.postalCode"
                        v-bind="resultProps[index]"
                      >
                        {{ result.postalCode }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div>

            <!--INPUT FIELD: formData.municipalityName -->
            <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!municipalitiesArr.length }">
              <small v-show="loadingMunicipalities" style="position: absolute;z-index: 1;right: 30px;">Cargando municipios...</small>
              <autocomplete
                @submit="onSubmitMunicipalities"
                :search="search"
                :get-result-value="getResultValue"
                placeholder="Municipios"
                style="width: 100%;"
                auto-select
                >
                <template
                  #default="{
                    rootProps,
                    inputProps,
                    inputListeners,
                    resultListProps,
                    resultListListeners,
                    results,
                    resultProps
                  }"
                >
                  <div v-bind="rootProps">
                    <input
                      :disabled="!!!municipalitiesArr.length"
                      v-model="formData.municipalityName"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="setActiveField('municipalitiesArr', 'municipalityName')"
                      required=""
                    >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="result.municipalityName"
                        v-bind="resultProps[index]"
                      >
                        {{ result.municipalityName }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
              </div>

              <!--INPUT FIELD: formData.addressName -->
              <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!addressesArr.length }">
              <small v-show="loadingAddressess" style="position: absolute;z-index: 1;right: 30px;">Cargando calles...</small>
                <autocomplete
                  @submit="onSubmitAddresses"
                  :search="search"
                  :get-result-value="getResultValue"
                  placeholder="Calle"
                  style="width: 100%;"
                  auto-select
                  >
                  <template
                    #default="{
                      rootProps,
                      inputProps,
                      inputListeners,
                      resultListProps,
                      resultListListeners,
                      results,
                      resultProps
                    }"
                  >
                    <div v-bind="rootProps">
                      <input
                        :disabled="!!!addressesArr.length"
                        v-model="formData.addressName"
                        v-bind="inputProps"
                        v-on="inputListeners"
                        class="an-input__field"
                        @focus="setActiveField('addressesArr', 'name')"
                        required=""
                      >
                      <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                        <li
                          class="an-select__custom-option"
                          v-for="(result, index) in results"
                          :key="result.name"
                          v-bind="resultProps[index]"
                        >
                          {{ result.name }}
                        </li>
                      </ul>
                    </div>
                  </template>
                </autocomplete>
              </div>

              <!-- INPUT FIELD: formData.number -->
              <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!estatesArr.length }">
                <small v-show="loadingEstates" style="position: absolute;z-index: 1;right: 30px;">Cargando numeros...</small>
                <autocomplete
                  @submit="onSubmitEstates"
                  :search="searchEstates"
                  :get-result-value="getResultValue"
                  placeholder="Número"
                  style="width: 100%;"
                  auto-select
                  >
                  <template
                    #default="{
                      rootProps,
                      inputProps,
                      inputListeners,
                      resultListProps,
                      resultListListeners,
                      results,
                      resultProps
                    }"
                  >
                    <div v-bind="rootProps">
                      <input
                        :disabled="!!!estatesArr.length"
                        v-model="formData.number"
                        v-bind="inputProps"
                        v-on="inputListeners"
                        class="an-input__field"
                        @focus="setActiveField('estatesArr', 'number')"
                        required=""
                      >
                      <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                        <li
                          class="an-select__custom-option"
                          v-for="(result, index) in results"
                          :key="result.number"
                          v-bind="resultProps[index]"
                        >
                          {{ result.number }}
                        </li>
                      </ul>
                    </div>
                  </template>
                </autocomplete>
              </div>

              <!--INPUT FIELD: formData.houseType -->
              <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!propertiesArr.length }">
              <small v-show="loadingProperties" style="position: absolute;z-index: 1;right: 30px;">Cargando viviendas...</small>
              <autocomplete
                @submit="onSubmitProperties"
                :search="search"
                :get-result-value="getResultValue"
                placeholder="Vivienda (bloque, escalera, piso, puerta)"
                style="width: 100%;"
                auto-select
                >
                <template
                  #default="{
                    rootProps,
                    inputProps,
                    inputListeners,
                    resultListProps,
                    resultListListeners,
                    results,
                    resultProps
                  }"
                >
                  <div v-bind="rootProps">
                    <input
                      :disabled="!!!propertiesArr.length"
                      v-model="formData.houseType"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="setActiveField('propertiesArr', 'address')"
                      required=""
                    >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="result.address"
                        v-bind="resultProps[index]"
                      >
                        {{ result.address }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div>
          </div>

          <button type="submit" :disabled="!formData.status || btnDisabled===true" :class="{ 'an-btn--disabled': !formData.status || btnDisabled  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Comprobar</span>
          </button>

        </form>
      </div>
    </template>
    <template v-else>
      <coverage-error :msg="house.state.coverageError" />
    </template>
  </div>`
  }

export default coverageForm;
