import coverageError from './coverageError';
import Autocomplete from '@trevoreyre/autocomplete-vue';
import clickOutside from '../../directives/clickoutDirective';

const coverageForm = {
  directives: {
    clickOutside: clickOutside
  },
  components: {
    'coverage-error': coverageError,
    Autocomplete
  },
  data() {
    return {
      formData: {
        postalCode: "",
        populationName: "",
        municipalityName: "",
        municipalityId: "",
        provinceId: "",
        provMunId: "",
        populationId: "",
        addressKind: "",
        addressName: "",
        name: "",
        number:"",
        propertyAddress: "",
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
      checkingAvailability: false
    }
  },
  inject: ["global", "house", "lead"],
  methods: {

    goBack() {
      this.global.changeView('funnel')
      this.house.resetAutocompleteData()
      this.house.setCoverageError("")
    },

    submitRequest() {
      if(this.formData.propertyAddress) {
        this.lead.setAddress(this.formData.propertyAddress)
        this.house.changeHouseStep('vivienda');
      }
      this.house.resetAutocompleteData()
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

    showHelperDropdown(elemId) {
      const elem = document.querySelector(elemId)
      if(elem) {
        elem.classList.add('d-block');
      }
    },

    hideHelperDropdown(elemId) {
      const elem = document.querySelector(elemId)
      if(elem) {
        elem.classList.remove('d-block');
      }
    },

    checkResultsLength(elemId, results) {
      const elem = document.querySelector(elemId)
      if(results.length) {
        elem.classList.remove('d-block');
      } else {
        elem.classList.add('d-block');
      }
    },

    setValue(resultItemName, formDataKey, elemId) {
      this.formData[formDataKey] = resultItemName
      this.hideHelperDropdown(elemId)
    },

    /*************************************
     * POSTAL CODES | Códigos postales */
    searchPostalCodes(index) {
      if (index.length < 1) { return [] }
      return this.postalCodesArr.filter(pc => {
        return pc.postalCode.startsWith(index)
      })
    },

    onSubmitPostalCode(result) {
      const { postalCode } = result
      this.formData.postalCode = postalCode
      // Save object in the store
      this.house.setCoverageData("postalCode", { postalCode })
      this.lead.setPostalCodeData({ postalCode })
      
      // Get municipalities
      this.loadingMunicipalities = true,
      this.house.getMunicipalities(postalCode)  // OBTENER MUNICIPIOS
        .then(() => { this.loadingMunicipalities = false })
        .catch((err) => {
          // window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingMunicipalities = false
          console.error(err)
        })
    },


    /*************************************
     * MUNICIPALITIES | Municipios
    *************************************/

    checkAvailability(result) {
      const { municipalityId, provinceId } = result

      this.checkingAvailability = true

      this.house.getAvailability(this.formData.postalCode, provinceId, municipalityId)
      .then(() => {
        this.onSubmitMunicipalities(result)
        this.checkingAvailability = false
       })
      .catch((err) => {
        console.error(err)
        this.checkingAvailability = false
      })
    },

    onSubmitMunicipalities(result) {
      const { populationName, municipalityId, municipalityName, provinceId, populationId } = result
      this.formData.provMunId =  provinceId + municipalityId

      // Save object in the store
      this.house.setCoverageData("postalCode", { municipalityId, municipalityName, provinceId, populationId})
      this.lead.setPostalCodeData({ municipalityId, municipalityName, provinceId})

      this.formData.municipalityName = municipalityName
      this.formData.municipalityId = municipalityId
      this.formData.provinceId = provinceId
      this.formData.populationId = populationId
      this.formData.populationName = populationName

      this.loadingAddressess = true
      this.house.getAddresses(populationId, this.formData.postalCode) // OBTENER CALLES
        .then(() => { this.loadingAddressess = false })
        .catch((err) => {
          // window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingAddressess = false
          console.error(err)
        })

    },

    /*************************************
     * ADDRESSES | Calles
    *************************************/
    onSubmitAddresses(result) {
      const { kind, name, addressId } = result

      // Save object in the store
      this.house.setCoverageData("estate", {
        addressId,
        addressKind: kind,
        addressName: name,
      })
      this.lead.setEstateData({ 
        addressKind: kind,
        addressName: name,
      })

      this.formData.addressKind = kind
      this.formData.addressName =  kind ? `${kind} ${name}` : name
      this.formData.addressId = addressId

      this.loadingEstates = true
      this.house.getEstates(this.formData.populationId, this.formData.addressId)  // OBTENER NÚMEROS DE PORTAL
        .then(() => { this.loadingEstates = false })
        .catch((err) => {
          // window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingEstates = false
          console.error(err)
        })
    },

    /*************************************
     * ESTATES | NÚMEROS DE PORTAL
    *************************************/
    searchEstates(index) {
      if (index.length < 1) { return [] }
      return this.estatesArr.filter(pc => {
        return pc.number.startsWith(index)
      })
    },

    onSubmitEstates(result) {
      const { gateId, number, annex } = result

      // Save object in the store
      this.house.setCoverageData("estate", { gateId, number, annex })
      this.lead.setEstateData({ number, gateId })

      if(annex) {
        this.formData.number = `${number} ${annex}`
      } else {
        this.formData.number = number
      }

      // AL PARECER NO HARÁ FALTA CONSULTAR VIVIENDAS
      //------------------------------------------------
      // this.loadingProperties = true
      // this.house.getProperties(gateId)
      //   .then(() => { this.loadingProperties = false })
      //   .catch((err) => {
      //     window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
      //     this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
      //     this.loadingProperties = false
      //     console.error(err)
      //   })
    }
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

  },

  mounted() {
    window.dataLayer.push(this.house.getDatalayerFirstStepInfo("FUNNEL - CONTRATACIÓN", "coberture", "gas"));
    if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.add('hide');
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template: /*html*/
  `<div>
    <template v-if="!house.state.coverageError">
      <div class="an-form an-wrapper">
        <div v-if="checkingAvailability" class="an-funnel__white-overlay">
          <p class="an-h3">Verificando disponibilidad...</p>
        </div>
        <p class="an-body-l-bold mb-xl">Rellena tu dirección para saber si tenemos cobertura en tu zona</p>
        <form @submit.prevent="submitRequest">
          <div class="an-form__flex an-form__flex--2-cols">
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
                @submit="checkAvailability"
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
                  <div v-bind="rootProps" v-click-outside:municustomul="hideHelperDropdown">
                    <input
                      :disabled="!!!municipalitiesArr.length"
                      v-model="formData.populationName"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('municipalitiesArr', 'populationName'),
                        showHelperDropdown('#municustomul')
                      ]"
                      @keyup="checkResultsLength('#municustomul', results)"
                      required=""
                      >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'municipality-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{result.populationName}} ({{ result.municipalityName }})
                      </li>
                    </ul>

                    <ul id="municustomul" v-show="house.state.autocompData.municipalities.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(municipality.populationName, 'populationName', '#municustomul'), checkAvailability(municipality)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(municipality, index) in house.state.autocompData.municipalities" :key="'second-municipality-'+index">
                        {{municipality.populationName}} ({{ municipality.municipalityName }})
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
                  <div v-bind="rootProps" v-click-outside:addresscustomul="hideHelperDropdown">
                    <input
                      :disabled="!!!addressesArr.length"
                      v-model="formData.addressName"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('addressesArr', 'name'),
                        showHelperDropdown('#addresscustomul')
                      ]"
                      @keyup="checkResultsLength('#addresscustomul', results)"
                      required=""
                      >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'address-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{result.kind}} {{ result.name }}
                      </li>
                    </ul>

                    <ul id="addresscustomul" v-show="house.state.autocompData.addresses.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li v-for="(address, index) in house.state.autocompData.addresses" :key="'second-address-'+index" @click="[setValue(address.name, 'name', '#addresscustomul'), onSubmitAddresses(address)]" v-bind="resultProps[index]" class="an-select__custom-option">
                        {{address.kind}} {{ address.name }}
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
                  <div v-bind="rootProps" v-click-outside:estatescustomul="hideHelperDropdown">
                    <input
                      :disabled="!!!estatesArr.length"
                      v-model="formData.number"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('estatesArr', 'number'),
                        showHelperDropdown('#estatescustomul')
                      ]"
                      @keyup="checkResultsLength('#estatescustomul', results)"
                      required=""
                      >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'estate-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.number }} {{ result.annex }}
                      </li>
                    </ul>

                    <ul id="estatescustomul" v-show="house.state.autocompData.estates.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(estate.number, 'number', '#estatescustomul'), onSubmitEstates(estate)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(estate, index) in house.state.autocompData.estates" :key="'second-estate-'+index">
                        {{ estate.number }} {{ estate.annex }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div>

            <!--INPUT FIELD: formData.propertyAddress -->

            <div class="an-input an-form__item" :class="{ 'an-input--disabled': !formData.number }">
            <input
              :disabled="!formData.number"
              v-model="formData.propertyAddress"
              class="an-input__field"
              placeholder="Vivienda (bloque, escalera, piso, puerta)"
            >
            </div>

            <!-- <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!propertiesArr.length }">
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
                  <div v-bind="rootProps" v-click-outside:propertiescustomul="hideHelperDropdown">
                    <input
                      :disabled="!!!propertiesArr.length"
                      v-model="formData.propertyAddress"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('propertiesArr', 'address'),
                        showHelperDropdown('#propertiescustomul')
                      ]"
                      @keyup="checkResultsLength('#propertiescustomul', results)"
                    >
                    <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'property-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.address }}
                      </li>
                    </ul>

                    <ul id="propertiescustomul" v-show="house.state.autocompData.properties.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(property.address, 'address', '#propertiescustomul'), onSubmitProperties(property)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(property, index) in house.state.autocompData.properties" :key="'second-property-'+index">
                        {{ property.address }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div> -->
          </div>

          <button type="submit" :disabled="!formData.number" :class="{ 'an-btn--disabled': !formData.number  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Comprobar</span>
          </button>
      
        </form>
      </div>
      <h5 class="mt-xl">¿No encuentras tu dirección? Llama al <a href="tel:+34900922203" class="an-link">900 92 22 03 </a>y te atendemos</h5>
    </template>
    <template v-else>
      <coverage-error :msg="house.state.coverageError" />
      <div class="an-form__flex an-form__flex--6-cols justify-content-center">
        <button @click="goBack" type="button" class="an-btn an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
          <span>Volver a calcular</span>
        </button>
      </div>  
    </template>
  </div>`
  }

export default coverageForm;