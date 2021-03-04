import clickOutside from '../../directives/clickoutDirective'

const houseForm = {
  directives: {
    'click-outside': clickOutside
  },
  inject: ["house"],
  data() {
    return {
      gasBudgetRequest: {
        propertyMeters: "",
        floorNumber: "",
        bathroomNumber: "",
        staysNumber: "",
        gasNaturalUse: "",
        acsUse: "",
        kitchenUse: "",
        heatingUse: "",
        boilerLocation: "",
        hasVentilationGrill: true,
        personsWater: "",
        metersBoilerToWindow: "",
        metersWaterIntake: "",
        connectDeviceToKitchen: false,
        convertDeviceKitchen: false,
        controllHeatingFloor: false,
        radiatorsBathroom: "",
      },

      sendingForm: false,
      submitFormError: false, // TODO
      showVentilationGrillRadios: false,
      showConnectConvertDeviceToKitchen: false
    }
  },
  methods: {
    submitRequest() {
      this.sendingForm = true
      this.house.submitHouseData(this.gasBudgetRequest).then(() => {
        window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details OK", "gas"));
        this.sendingForm = false
      }).catch((err) => {
        window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details KO", "gas"));
        this.sendingForm = false
        console.log(err)
      })
    },

    showVentilationGrillFn(){
      if (this.gasBudgetRequest.boilerLocation === "Lavadero/Terraza") {
        this.gasBudgetRequest.hasVentilationGrill = true
        this.showVentilationGrillRadios = false
        return false
      } else if(this.gasBudgetRequest.boilerLocation === "Cocina") {
        this.showVentilationGrillRadios = true
        return true
      } else if(this.gasBudgetRequest.boilerLocation === "Baño") {
        this.gasBudgetRequest.hasVentilationGrill = true
        this.showVentilationGrillRadios = false
        return false
      }
    },
    showConnectConvertDeviceToKitchenFn(){
      if (this.gasBudgetRequest.boilerLocation === "Lavadero/Terraza") {
        this.gasBudgetRequest.connectDeviceToKitchen = false
        this.gasBudgetRequest.convertDeviceKitchen = false
        this.showConnectConvertDeviceToKitchen = false
        return false
      } else if(this.gasBudgetRequest.boilerLocation === "Cocina") {
        this.showConnectConvertDeviceToKitchen = true
        return true
      } else if(this.gasBudgetRequest.boilerLocation === "Baño") {
        this.gasBudgetRequest.connectDeviceToKitchen = false
        this.gasBudgetRequest.convertDeviceKitchen = false
        this.showConnectConvertDeviceToKitchen = false
        return false
      }
    },

    toggleInfoItem(e) {
      e.target.parentElement.classList.toggle("an-info--hidden")
    },

    closeInfoItem(el) {
      el.classList.add("an-info--hidden")
    }
  },
  computed: {
    kitchenSelected() {
      return (
        this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina"
        ||
        this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina+Calefacción"
      )
    },

    heatingSelected() {
      return (
        this.gasBudgetRequest.gasNaturalUse === "ACS+Calefacción"
        ||
        this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina+Calefacción"
      )
    }
  },
  mounted () {
    window.dataLayer.push(this.house.getDatalayerAddressStepInfo("FUNNEL - CONTRATACIÓN", "details", "gas"));
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template: /*html*/
    `<div class="an-form an-wrapper">
      <div v-if="sendingForm" class="an-funnel__white-overlay">
        <p class="an-h3">Cargando...</p>
      </div>

        <form @submit.prevent="submitRequest">
          <p class="an-body-l-bold mb-xl">Rellena los datos de tu vivienda para poderte hacer un presupuesto lo más ajustado posible</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">

            <div class="an-input an-form__item">
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.propertyMeters" class="an-select__native" required>
                  <option disabled value="">Metros cuadrados</option>
                  <option value="Hasta 100m2">Hasta 100m2</option>
                  <option value="De 100m2 a 180m2">De 100m2 a 180m2</option>
                  <option value="Mas de 180m2">Mas de 180m2</option>
                </select>
              </div>
            </div>

            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.floorNumber" type="number" min="0" class="an-input__field" placeholder="Plantas" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.bathroomNumber" type="number" min="0" class="an-input__field" placeholder="Baños" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.staysNumber" type="number" min="0" class="an-input__field" placeholder="Número de estancias" required="">
            <p class="an-input__caption an-body-s-regular">*(Incluye cocina y salón y excluye baños)</p>
            </div>
          </div>

          <p class="an-body-l-bold mb-xl">¿Qué necesitas?</p>
          <div class="an-form__flex an-form__flex--4-cols mb-xxl">
            <label for="agua-caliente" class="an-form__item">
              <input v-model="gasBudgetRequest.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente" name="que-necesitas" value="solo ACS" checked>

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente</p>
                <div class="an-selection__icon an-icon--hot-water"></div>
              </div>
            </label>

            <label for="agua-caliente-cocina" class="an-form__item">
              <input v-model="gasBudgetRequest.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-cocina" name="que-necesitas" value="ACS+Cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-and-pot"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion" class="an-form__item">
              <input v-model="gasBudgetRequest.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion" name="que-necesitas" value="ACS+Calefacción">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción</p>
                <div class="an-selection__icon an-icon--hot-water-thermo"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion-cocina" class="an-form__item">
              <input v-model="gasBudgetRequest.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion-cocina" name="que-necesitas" value="ACS+Cocina+Calefacción">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-thermo-and-pot"></div>
              </div>
            </label>
          </div>


          <p class="an-body-l-bold mb-xl">Selecciona todo lo que tienes ahora mismo</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">

            <div class="an-form__item">
              <p class="an-body-m-bold color-an-theme mb-m">Agua Caliente</p>
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.acsUse" class="an-select__native" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="No Procede">No procede</option>
                  <option value="Termo eléctrico">Termo eléctrico</option>
                  <option value="Butano">Butano</option>
                  <option value="Propano">Propano</option>
                  <option value="Gasóleo">Gasóleo</option>
                  <option value="Carbón">Carbón</option>
                  <option value="Otro">Otro</option>
                </select>
              </div>
            </div>


            <div class="an-form__item">
              <p class="an-body-m-bold color-an-theme mb-m">Cocina</p>
              <div class="an-select an-select--full-width" :class="{ 'an-select--disabled': !kitchenSelected }">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.kitchenUse" :disabled="!kitchenSelected" class="an-select__native" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="No Procede">No procede</option>
                  <option value="Eléctrico">Eléctrico</option>
                  <option value="Butano">Butano</option>
                  <option value="Propano">Propano</option>
                  <option value="Gasóleo">Gasóleo</option>
                  <option value="Carbón">Carbón</option>
                  <option value="Otro">Otro</option>
                </select>
              </div>
            </div>

            <div class="an-form__item">
              <p class="an-body-m-bold color-an-theme mb-m">Calefacción</p>
              <div class="an-select an-select--full-width" :class="{ 'an-select--disabled': !heatingSelected }">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.heatingUse" :disabled="!heatingSelected" class="an-select__native" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="No Procede">No procede</option>
                  <option value="Radiadores eléctricos">Radiadores eléctricos</option>
                  <option value="Butano">Butano</option>
                  <option value="Propano">Propano</option>
                  <option value="Gasóleo">Gasóleo</option>
                  <option value="Carbón">Carbón</option>
                  <option value="Otro ">Otro </option>
                </select>
              </div>
            </div>

            <div class="an-form__item">
              <div class="d-flex mb-m">
                <p class="an-body-m-bold color-an-theme">¿Dónde está o va a estar el calentador/caldera instalado?</p>
                <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                  <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                  <div class="an-info__box"> 
                    En que ubicación de la casa se va a poner el equipo, la misma determinara los trabajos a realizar.
                  </div>
                </div>
              </div>
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.boilerLocation" class="an-select__native" @change="[showVentilationGrillFn(), showConnectConvertDeviceToKitchenFn()]" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="Lavadero/Terraza">Lavadero/Terraza</option>
                  <option value="Cocina">Cocina</option>
                  <option value="Baño">Baño</option>
                </select>
              </div>
            </div>

          </div>

          <template v-if="showVentilationGrillRadios">
            <div class="d-flex mb-xl">
              <p class="an-body-l-bold">¿Necesita instalar rejilla de ventilación?</p> 
              <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                <div class="an-info__box"> 
                  Se tienen que tener rejillas superiores e inferiores.
                </div>
              </div>
            </div>

            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.hasVentilationGrill" :value="true" class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="vent-si">
                <label class="an-radio__label" for="vent-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.hasVentilationGrill" :value="false" class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="vent-no">
                <label class="an-radio__label" for="vent-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>
          </template>

          <p class="an-body-l-bold mb-xl">¿Qué uso haces del agua caliente?</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">
            <label for="agua-caliente-un-sitio" class="an-form__item">
              <input v-model="gasBudgetRequest.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-un-sitio" name="uso-agua-caliente" value="Hasta 2">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en un sitio para 1 o 2 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-dos-sitios" class="an-form__item">
              <input v-model="gasBudgetRequest.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-dos-sitios" name="uso-agua-caliente" value="Entre 3-4">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en dos sitios a la vez para 3 o 4 personas</p>
                <div class="an-selection__icon an-icon--hot-water-three"></div>
              </div>
            </label>

            <label for="agua-caliente-tres-sitios" class="an-form__item">
              <input v-model="gasBudgetRequest.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-tres-sitios" name="uso-agua-caliente" value="Más de 4">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en tres o más sitios a la vez para 5 o más personas</p>
                <div class="an-selection__icon an-icon--hot-water-four"></div>
              </div>
            </label>
          </div>

          <div class="d-flex mb-xl">
            <p class="an-body-l-bold">¿Distancia entre calentador/caldera y la ventana/pared exterior?</p> 
            <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
              <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
              <div class="an-info__box"> 
                Nos puede indicar los metros entre donde quiere instalar el calentador/caldera y la ventana más próxima de la estancia o la pared exterior más próxima en caso de no existir ventana.
              </div>
            </div>
          </div>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.metersBoilerToWindow" type="number" class="an-input__field" required="">
            </div>
          </div>

          <div class="d-flex mb-xl">
            <p class="an-body-l-bold">¿Distancia entre las tomas de agua y la ubicación calentador/caldera?</p> 
            <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
              <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
              <div class="an-info__box"> 
                Si la ubicación del nuevo calentador/caldera es distinta de la ubicación de su aparato de generación de agua caliente actual indique la distancia en metros.
              </div>
            </div>
          </div>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.metersWaterIntake" class="an-select__native" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="m.0">0</option>
                  <option value="m.1">1</option>
                  <option value="m.2">2</option>
                  <option value="m.3">3</option>
                  <option value="m.4">4</option>
                </select>
              </div>
            </div>
          </div>

          <template v-if="showConnectConvertDeviceToKitchen">
            <div class="d-flex mb-xl">
              <p class="an-body-l-bold">¿Necesita conectar su cocina a la instalación de gas?</p> 
              <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                <div class="an-info__box"> 
                  Si no quiere utilizar cocinas eléctricas se podría conectar una cocina de gas a la nueva instalación.
                </div>
              </div>
            </div>
            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.connectDeviceToKitchen" :value="true" class="an-radio__input" checked="" type="radio" name="connect-kitchen-device" id="connect-kitchen-device-si">
                <label class="an-radio__label" for="connect-kitchen-device-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.connectDeviceToKitchen" :value="false" class="an-radio__input" type="radio" name="connect-kitchen-device" id="connect-kitchen-device-no">
                <label class="an-radio__label" for="connect-kitchen-device-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>

            <div class="d-flex mb-xl">
              <p class="an-body-l-bold">¿Quiere intentar reutilizar su cocina?</p> 
              <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                <div class="an-info__box"> 
                  Reutilizar la cocina existente (no eléctrica) para que funcione a gas natural transformándola.
                </div>
              </div>
            </div>
            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.convertDeviceKitchen" :value="true" class="an-radio__input" checked="" type="radio" name="convert-kitchen-device" id="convert-kitchen-device-si">
                <label class="an-radio__label" for="convert-kitchen-device-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.convertDeviceKitchen" :value="false" class="an-radio__input" type="radio" name="convert-kitchen-device" id="convert-kitchen-device-no">
                <label class="an-radio__label" for="convert-kitchen-device-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>
          </template>

          <template v-if="heatingSelected">
            
            <div class="d-flex mb-xl">
              <p class="an-body-l-bold">¿Quiere controlar la calefacción de manera independiente en cada planta de su vivienda?</p> 
              <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                <div class="an-info__box"> 
                  Solo se debería activar si tiene varias plantas, si no, no da lugar.
                </div>
              </div>
            </div>

            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.controllHeatingFloor" value="true" class="an-radio__input" checked="" type="radio" name="constrol-heating-floor" id="constrol-heating-floor-si">
                <label class="an-radio__label" for="constrol-heating-floor-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.controllHeatingFloor" value="false" class="an-radio__input" type="radio" name="constrol-heating-floor" id="constrol-heating-floor-no">
                <label class="an-radio__label" for="constrol-heating-floor-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>

            <div class="d-flex mb-xl">
              <p class="an-body-l-bold">¿Necesita algún radiador toallero en su/s baño/s?</p> 
              <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
                <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
                <div class="an-info__box"> 
                  El radiador que se instalará en su baño será tipo toallero.
                </div>
              </div>
            </div>
            <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.radiatorsBathroom" class="an-select__native" required="required">
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="0">0</option>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                </select>
              </div>
            </div>
          </div>

          </template>

          <div class="an-form__flex an-form__flex--6-cols mb-xxl">
            <button @click="house.changeHouseStep('cobertura')" type="button" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
              <span>Anterior</span>
            </button>

            <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
              <span v-if="!sendingForm">Continuar</span>
              <span v-else>Enviando...</span>
            </button>
          </div>

          <!-- TODO -->
          <p v-if="submitFormError" class="color-danger">Ups, parece que hubo un problema. Por favor intente nuevamente.</p>

        </form>
      </div>
    `
}

export default houseForm;