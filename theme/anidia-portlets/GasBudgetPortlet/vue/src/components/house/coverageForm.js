import coverageError from './coverageError';
import Autocomplete from '@trevoreyre/autocomplete-vue';
import clickOutside from '../directives/clickoutDirective'

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
  inject: ["house"],
  methods: {
    submitRequest() {
      if(this.isValidStatusCode) {
        // console.log("submited")
        this.house.setPostalCode(this.formData.postalCode);
        this.house.changeHouseStep('vivienda');
      } else {
        this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
        if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.remove('hide');
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

    showHelperDropdown(elemId) {
      const elem = document.querySelector(elemId)
      if(elem) {
        // console.log("showHelperDropdown", elem)
        elem.classList.add('d-block');
      }
    },

    hideHelperDropdown(elemId) {
      const elem = document.querySelector(elemId)
      if(elem) {
        // console.log("hideHelperDropdown", elem)
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

    // setValue(municipality.municipalityName, 'municipalityName')
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
      const { postalCode } = result
      this.formData.postalCode = postalCode
      // Save object in the store
      this.house.setCoverageData("postalCode", { postalCode })
      // Get municipalities
      this.loadingMunicipalities = true,
      this.house.getMunicipalities(postalCode)
        .then((res) => { this.loadingMunicipalities = false })
        .catch((err) => {
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingMunicipalities = false
        })
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
        .catch((err) => {
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingAddressess = false
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
      this.formData.addressName = name

      this.loadingEstates = true
      this.house.getEstates(this.formData.provMunId, this.formData.postalCode, kind, name)
        .then((res) => { this.loadingEstates = false })
        .catch((err) => {
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingEstates = false
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
      const { gateId, number } = result

      // Save object in the store
      this.house.setCoverageData("estate", { gateId, number })

      this.formData.number = number

      this.loadingProperties = true
      this.house.getProperties(gateId)
        .then((res) => { this.loadingProperties = false })
        .catch((err) => {
          this.house.setCoverageError('Vaya, de momento no prestamos servicio en tu zona. Lo sentimos mucho.');
          this.loadingProperties = false
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
    if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.add('hide');
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    })
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
      <li>He leído y acepto la política de privacidad: {{ formData.privacyPolicy }}</li>
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
                      :class="{ 'form-group--error': $v.formData.postalCode.$invalid && formData.postalCode.length }"
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
                    <h6 class="form-error" v-if="$v.formData.postalCode.$invalid && formData.postalCode.length">Hay un error en el campo introducido</h6>
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
                      v-model="formData.municipalityName"
                      v-bind="inputProps"
                      v-on="inputListeners"
                      class="an-input__field"
                      @focus="[
                        setActiveField('municipalitiesArr', 'municipalityName'),
                        showHelperDropdown('#municustomul')
                      ]"
                      @keyup="checkResultsLength('#municustomul', results)"
                      required=""
                      >
                      <!-- @blur="hideHelperDropdown('#municustomul')" -->
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

                    <ul id="municustomul" v-show="house.state.autocompData.municipalities.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(municipality.municipalityName, 'municipalityName', '#municustomul'), onSubmitMunicipalities(municipality)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(municipality, index) in house.state.autocompData.municipalities" :key="municipality.municipalityId">
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
                        <!-- @blur="hideHelperDropdown('#addresscustomul')" -->
                      <ul class="an-select__custom-options" style="display: block;" v-bind="resultListProps" v-on="resultListListeners">
                        <li
                          class="an-select__custom-option"
                          v-for="(result, index) in results"
                          :key="'street-'+index"
                          v-bind="resultProps[index]"
                        >
                          {{ result.name }}
                        </li>
                      </ul>

                      <ul id="addresscustomul" v-show="house.state.autocompData.addresses.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                        <li @click="[setValue(address.name, 'name', '#addresscustomul'), onSubmitAddresses(address)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(address, index) in house.state.autocompData.addresses" :key="address.name">
                          {{ address.name }}
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
                    <!-- <div v-bind="rootProps"> -->
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
                        <!-- @blur="hideHelperDropdown('#estatescustomul')" -->
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

                      <ul id="estatescustomul" v-show="house.state.autocompData.estates.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                        <li @click="[setValue(estate.number, 'number', '#estatescustomul'), onSubmitEstates(estate)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(estate, index) in house.state.autocompData.estates" :key="estate.gateId">
                          {{ estate.number }}
                        </li>
                      </ul>
                    </div>
                  </template>
                </autocomplete>
              </div>

              <!--INPUT FIELD: formData.houseType -->
              <div class="an-input an-form__item" :class="{ 'an-input--disabled': !!!propertiesArr.length }">
              <small v-show="loadingProperties" style="position: absolute;z-index: 1;right: 30px;">Cargando viviendas...</small>

              <!-- <input :disabled="!!!propertiesArr.length" v-model="formData.houseType" class="an-input__field" placeholder="Vivienda (bloque, escalera, piso, puerta)" style="width: 100%;"> -->

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
                      v-model="formData.houseType"
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
                        :key="'propertyResult'+index"
                        v-bind="resultProps[index]"
                      >
                        {{ result.address }}
                      </li>
                    </ul>

                    <ul id="propertiescustomul" v-show="house.state.autocompData.properties.length" class="an-select__custom-options" style="position: absolute; width: 100%; top: 100%; z-index: 3;">
                      <li @click="[setValue(property.address, 'address', '#propertiescustomul'), onSubmitProperties(property)]" v-bind="resultProps[index]" class="an-select__custom-option" v-for="(property, index) in house.state.autocompData.properties" :key="'property'+index">
                        {{ property.address }}
                      </li>
                    </ul>
                  </div>
                </template>
              </autocomplete>
            </div>
          </div>

          <button type="submit" :disabled="!formData.status && btnDisabled===true" :class="{ 'an-btn--disabled': !formData.status && btnDisabled  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Comprobar</span>
          </button>

        </form>
      </div>
      <h5 class="mt-xl">¿No encuentras tu dirección? Llama al <a href="tel:+34900922203" class="an-link">900 92 22 03 </a>y te atendemos</h5>
    </template>
    <template v-else>
      <coverage-error :msg="house.state.coverageError" />
    </template>
  </div>`
  }

export default coverageForm;