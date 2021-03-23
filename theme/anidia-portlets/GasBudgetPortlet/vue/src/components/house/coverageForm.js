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
      value: '',
      results: [],
      isCustomAddress: false,
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
        fieldKey: ""
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
  inject: ["global", "house"],
  methods: {

    goBack() {
      this.global.changeView('funnel')
      this.house.resetAutocompleteData()
      this.house.setCoverageError("")
    },

    submitRequest() {
      if(this.isValidStatusCode) {
        this.house.setPostalCode(this.formData.postalCode);
        window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture OK", "gas"));
        this.house.changeHouseStep('vivienda');
      } else {
        this.house.setPostalCode(this.formData.postalCode);
        window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
        this.house.setCoverageError('Hemos detectado que ya tienes gas natural instalado');
        if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.remove('hide');
      }

      this.house.resetAutocompleteData()
    },

    /*************************************
     * GENERAL
    *************************************/
    search(input) {
      this.value = input
      if (input.length < 1) this.results = []
      else {
        this.results =  this[this.selected.fieldArr].filter(pc => {
          return pc[this.selected.fieldKey].toLowerCase()
            .startsWith(input.toLowerCase())
        })
      }
      return this.results
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
     * POSTAL CODES
    *************************************/
    searchPostalCodes(index) {
      if (index.length < 1) { return [] }
      return this.postalCodesArr.filter(pc => {
        return pc.postalCode.startsWith(index)
      })
    },

    onSubmitPostalCode(result) {
      if (result) {
        const { postalCode } = result
        this.formData.postalCode = postalCode
        // Save object in the store
        this.house.setCoverageData("postalCode", { postalCode })
        // Get municipalities
        this.loadingMunicipalities = true,
        this.house.getMunicipalities(postalCode)
          .then(() => { this.loadingMunicipalities = false })
          .catch((err) => {
            window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
            this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
            this.loadingMunicipalities = false
            console.error(err)
          })
      }
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
        .then(() => { this.loadingAddressess = false })
        .catch((err) => {
          window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingAddressess = false
          console.error(err)
        })

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
      this.formData.addressName = `${kind} ${name}`

      this.loadingEstates = true
      this.house.getEstates(this.formData.provMunId, this.formData.postalCode, kind, name)
        .then(() => { this.loadingEstates = false })
        .catch((err) => {
          window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingEstates = false
          console.error(err)
        })
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
      const { gateId, number, annex } = result

      // Save object in the store
      this.house.setCoverageData("estate", { gateId, number, annex })

      if(annex) {
        this.formData.number = `${number} ${annex}`
      } else {
        this.formData.number = number
      }

      this.loadingProperties = true
      this.house.getProperties(gateId)
        .then(() => { this.loadingProperties = false })
        .catch((err) => {
          window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "coberture KO", "gas"));
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingProperties = false
          console.error(err)
        })
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

    createCustomAddress() {
      this.isCustomAddress = true
    },

    resetCustomAddress() {
      this.isCustomAddress = false
      this.value = ''
      this.formData.postalCode = ""
      this.formData.municipalityName = ""
      this.formData.municipalityId = ""
      this.formData.provinceId = ""
      this.formData.provMunId = ""
      this.formData.addressKind = ""
      this.formData.addressName = ""
      this.formData.name = ""
      this.formData.number = ""
      this.formData.houseType = ""
      this.formData.status = "" 
      this.formData.privacyPolicy = false
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
    noresults() {
      return this.value && this.results.length === 0
    }
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
        <p class="an-body-l-bold mb-xl">Rellena tu dirección para saber si tenemos cobertura en tu zona</p>
        <form @submit.prevent="submitRequest">
        <div v-if="isCustomAddress" class="an-form__flex an-form__flex--2-cols">
          <!--INPUT FIELD: formData.postalCode -->
          <div class="an-input an-form__item">
            <input v-model="formData.postalCode" type="text" class="an-input__field" required="" placeholder="Código Postal">
          </div>
          <div class="an-input an-form__item">
            <input v-model="formData.municipalityName" type="text" class="an-input__field" required="" placeholder="Municipios">
          </div>
          <div class="an-input an-form__item">
            <input v-model="formData.addressName" type="text" class="an-input__field" required="" placeholder="Calle">
          </div>
          <div class="an-input an-form__item">
            <input v-model="formData.number" type="text" class="an-input__field" required="" placeholder="Número">
          </div>
          <div class="an-input an-form__item">
            <input v-model="formData.houseType" type="text" class="an-input__field" required="" placeholder="Vivienda (bloque, escalera, piso, puerta)">
          </div>
        </div>
          <div v-else class="an-form__flex an-form__flex--2-cols">
          <!--INPUT FIELD: formData.postalCode -->
           <div class="an-input an-form__item">
              <autocomplete
                :debounce-time="700"
                :search="search"
                placeholder="Código Postal"
                style="width: 100%;"
                auto-select
                @submit="onSubmitPostalCode"
                :get-result-value="getResultValue"
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
                      v-bind:value="formData.postalCode"
                      v-on:input="formData.postalCode= $event.target.value"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      :class="[
                        'autocomplete-input'
                      ]"
                      @focus="setActiveField('postalCodesArr', 'postalCode')"
                      required=""
                    >
                    <ul
                      v-if="noresults && selected.fieldKey ==='postalCode'"
                      class="an-select__custom-options an-select__custom-options--custom"
                      style="display: block;"
                    >
                      <li class="an-select__custom-option" @click="createCustomAddress()">
                        No encuentro mi dirección
                      </li>
                    </ul>
                    
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
                  <div v-bind="rootProps" v-click-outside:municustomul="hideHelperDropdown">
                  <input
                      :disabled="!!!municipalitiesArr.length"
                      v-bind:value="formData.municipalityName"
                      v-on:input="formData.municipalityName= $event.target.value"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      :class="[
                        'autocomplete-input'
                      ]"
                      @focus="[
                        setActiveField('municipalitiesArr', 'municipalityName'),
                        showHelperDropdown('#municustomul')
                      ]"
                      @keyup="checkResultsLength('#municustomul', results)"
                      required=""
                      >
                      <ul v-if="noresults && selected.fieldKey ==='municipalityName'"
                      class="an-select__custom-options an-select__custom-options--custom"
                      style="display: block;"
                    >
                      <li class="an-select__custom-option" @click="createCustomAddress()">
                        No encuentro mi dirección
                      </li>
                    </ul>
                    <ul v-if="!noresults && selected.fieldKey === 'municipalityName'" class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'municipality-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.municipalityName }}
                      </li>
                    </ul>

                    <ul v-if="!noresults && selected.fieldKey === 'municipalityName'" id="municustomul" v-show="house.state.autocompData.municipalities.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(municipality.municipalityName, 'municipalityName', '#municustomul'), onSubmitMunicipalities(municipality)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(municipality, index) in house.state.autocompData.municipalities" :key="'second-municipality-'+index">
                        {{ municipality.municipalityName }}
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
                      v-bind:value="formData.addressName"
                      v-on:input="formData.addressName= $event.target.value"
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
                    <ul v-if="noresults && selected.fieldKey ==='name'"
                      class="an-select__custom-options an-select__custom-options--custom"
                      style="display: block;"
                    >
                      <li class="an-select__custom-option" @click="createCustomAddress()">
                        No encuentro mi dirección
                      </li>
                    </ul>
                    <ul v-if="!noresults && selected.fieldKey ==='name'" class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'address-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.kind }} {{ result.name }}
                      </li>
                    </ul>

                    <ul v-if="!noresults && selected.fieldKey ==='name'" id="addresscustomul" v-show="house.state.autocompData.addresses.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li v-for="(address, index) in house.state.autocompData.addresses" :key="'second-address-'+index" @click="[setValue(address.kind + ' ' + address.name, 'name', '#addresscustomul'), onSubmitAddresses(address)]" v-bind="resultProps[index]" class="an-select__custom-option">
                        {{ address.kind }} {{ address.name }}
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
                      v-bind:value="formData.number"
                      v-on:input="formData.number= $event.target.value"
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
                    <ul v-if="noresults && selected.fieldKey ==='number'"
                      class="an-select__custom-options an-select__custom-options--custom"
                      style="display: block;"
                    >
                      <li class="an-select__custom-option" @click="createCustomAddress()">
                        No encuentro mi dirección
                      </li>
                    </ul>
                    <ul v-if="!noresults && selected.fieldKey ==='number'" class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'estate-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.number }} {{ result.annex }}
                      </li>
                    </ul>

                    <ul v-if="!noresults && selected.fieldKey ==='number'" id="estatescustomul" v-show="house.state.autocompData.estates.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(estate.number + ' ' + estate-annex, 'number', '#estatescustomul'), onSubmitEstates(estate)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(estate, index) in house.state.autocompData.estates" :key="'second-estate-'+index">
                        {{ estate.number }} {{ estate.annex }}
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
                  <div v-bind="rootProps" v-click-outside:propertiescustomul="hideHelperDropdown">
                    <input
                      :disabled="!!!propertiesArr.length"
                      v-bind:value="formData.houseType"
                      v-on:input="formData.houseType= $event.target.value"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('propertiesArr', 'address'),
                        showHelperDropdown('#propertiescustomul')
                      ]"
                      @keyup="checkResultsLength('#propertiescustomul', results)"
                    >
                    <ul v-if="noresults && selected.fieldKey ==='address'"
                      class="an-select__custom-options an-select__custom-options--custom"
                      style="display: block;"
                    >
                      <li class="an-select__custom-option" @click="createCustomAddress()">
                        No encuentro mi dirección
                      </li>
                    </ul>
                    <ul v-if="!noresults && selected.fieldKey ==='address'" class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                      <li
                        class="an-select__custom-option"
                        v-for="(result, index) in results"
                        :key="'property-'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.address }}
                      </li>
                    </ul>

                    <ul v-if="!noresults && selected.fieldKey ==='address'" id="propertiescustomul" v-show="house.state.autocompData.properties.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(property.address, 'address', '#propertiescustomul'), onSubmitProperties(property)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(property, index) in house.state.autocompData.properties" :key="'second-property-'+index">
                        {{ property.address }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div>
          </div>

          <button v-if="isCustomAddress" @click="resetCustomAddress" type="button" class="an-btn an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
            <span>Volver a calcular</span>
          </button>

          <button type="submit" :disabled="!formData.status && !isCustomAddress" :class="{ 'an-btn--disabled': !formData.status && !isCustomAddress  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple mt-xl">
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