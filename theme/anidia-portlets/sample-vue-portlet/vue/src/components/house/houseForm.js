const houseForm = {
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
        hasVentilationGrill: "",
        personsWater: "",
        metersBoilerToWindow: "", 
        metersWaterIntake: "",
        connectDeviceToKitchen: "false",
        convertDeviceKitchen: "false",
        controllHeatingFloor: "false",
        radiatorsBathroom: "",
      },

      sendingForm: false,
      submitFormError: false, // TODO
    }
  },
  methods: {
    submitRequest() {
      // console.log("Vivienda, enviar data...")
      // TODO
      this.sendingForm = true

      this.house.submitHouseData(this.gasBudgetRequest).then((res) => {
        this.sendingForm = false
      }).catch((err) => {
        this.sendingForm = false
        console.log(err)
      })
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
  template: /*html*/
    `<div class="an-form an-wrapper">
        <form @submit.prevent="submitRequest">
          <p class="an-body-l-bold mb-xl">Rellena los datos de tu vivienda para poderte hacer un presupuesto lo más ajustado posible</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            
            <div class="an-input an-form__item">
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.propertyMeters" class="an-select__native">
                  <option disabled value="">Metros cuadrados</option>
                  <option value="Hasta 100m2">Hasta 100m2</option>
                  <option value="De 100m2 a 180m2">De 100m2 a 180m2</option>
                  <option value="Mas de 180m2">Mas de 180m2</option>
                </select>
              </div>
            </div>

            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.floorNumber" type="text" class="an-input__field" placeholder="Plantas" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.bathroomNumber" type="text" class="an-input__field" placeholder="Baños" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.staysNumber" type="text" class="an-input__field" placeholder="Número de estancias" required="">
            <p class="an-input__caption an-body-s-regular">*(Incluye cocina y salón y excluye baños)</p>
            </div>
          </div>
          
          <p class="an-body-l-bold mb-xl">¿Que necesitas?</p>
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
                <select v-model="gasBudgetRequest.acsUse" class="an-select__native">
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
                <select v-model="gasBudgetRequest.kitchenUse" :disabled="!kitchenSelected" class="an-select__native">
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
                <select v-model="gasBudgetRequest.heatingUse" :disabled="!heatingSelected" class="an-select__native">
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
              <p class="an-body-m-bold color-an-theme mb-m">¿Dónde está la caldera?</p>
              <div class="an-select an-select--full-width" :class="{ 'an-select--disabled': !heatingSelected }">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.boilerLocation" :disabled="!heatingSelected" class="an-select__native">
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="Lavadero/Terraza">Lavadero/Terraza</option>
                  <option value="Cocina">Cocina</option>
                  <option value="Baño">Baño</option>
                </select>
              </div>
            </div>

          </div>

          <p class="an-body-l-bold mb-xl">¿Tienes rejilla de ventilación superior?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
            <div class="an-radio an-form__item">
              <input v-model="gasBudgetRequest.hasVentilationGrill" value="true" class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="vent-si">
              <label class="an-radio__label" for="vent-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input v-model="gasBudgetRequest.hasVentilationGrill" value="false" class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="vent-no">
              <label class="an-radio__label" for="vent-no">
                <span>
                  No
                </span>
              </label>
            </div>
          </div>


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
                <p class="an-menu-bold an-card__text">Agua caliente en dos sitios a la vez para 2 o 3 personas</p>
                <div class="an-selection__icon an-icon--hot-water-three"></div>
              </div>
            </label>

            <label for="agua-caliente-tres-sitios" class="an-form__item">
              <input v-model="gasBudgetRequest.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-tres-sitios" name="uso-agua-caliente" value="Más de 4">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en tres o más sitios a la vez para 4 o más personas</p>
                <div class="an-selection__icon an-icon--hot-water-four"></div>
              </div>
            </label>
          </div>

          <p class="an-body-l-bold mb-xl">¿Cuántos metros hay de la caldera/calentador a la ventana?</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <input v-model="gasBudgetRequest.metersBoilerToWindow" type="number" class="an-input__field" required="">
            </div>
          </div>

          <p class="an-body-l-bold mb-xl">¿Cuántos metros es necesario desplazar las tomas de agua para conectarlas al aparato?</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="gasBudgetRequest.metersWaterIntake" class="an-select__native">
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

          <template v-if="kitchenSelected">
            <p class="an-body-l-bold mb-xl">¿Necesita que conectemos el aparato de cocina actual?</p>
            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.connectDeviceToKitchen" value="true" class="an-radio__input" checked="" type="radio" name="connect-kitchen-device" id="connect-kitchen-device-si">
                <label class="an-radio__label" for="connect-kitchen-device-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.connectDeviceToKitchen" value="false" class="an-radio__input" type="radio" name="connect-kitchen-device" id="connect-kitchen-device-no">
                <label class="an-radio__label" for="connect-kitchen-device-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>

            <p class="an-body-l-bold mb-xl">¿Necesita conversión del aparato de cocina actual?</p>
            <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.convertDeviceKitchen" value="true" class="an-radio__input" checked="" type="radio" name="convert-kitchen-device" id="convert-kitchen-device-si">
                <label class="an-radio__label" for="convert-kitchen-device-si">
                  <span>
                    Si
                  </span>
                </label>
              </div>

              <div class="an-radio an-form__item">
                <input v-model="gasBudgetRequest.convertDeviceKitchen" value="false" class="an-radio__input" type="radio" name="convert-kitchen-device" id="convert-kitchen-device-no">
                <label class="an-radio__label" for="convert-kitchen-device-no">
                  <span>
                    No
                  </span>
                </label>
              </div>
            </div>
          </template>
          
          <template v-if="heatingSelected">
            <p class="an-body-l-bold mb-xl">¿Quiere controlar la calefacción por planta?</p>
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
          

            <p class="an-body-l-bold mb-xl">¿Cuántos radiadores toalleros quiere en el baño?</p>
            <div class="an-form__flex an-form__flex--2-cols mb-xxl">
              <div class="an-input an-form__item">
                <input v-model="gasBudgetRequest.radiatorsBathroom" type="number" class="an-input__field" required="">
              </div>
            </div>
          </template>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span v-if="!sendingForm">Continuar</span>
            <span v-else>Enviando...</span>
          </button>

          <!-- TODO -->
          <p v-if="submitFormError" class="color-danger">Ups, parece que hubo un problema. Por favor intente nuevamente.</p>
          
        </form>
      </div>
    `
}

export default houseForm;