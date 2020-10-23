const houseForm = {
  inject: ["global", "house"],
  data() {
    return {
      houseFormData: {
        // HouseType: En este punto, este dato debe estar almacenado en el state global (global.js). Es el tipo de casa elegida en el primer paso del proceso.
        
        // Corresponde con el campo “metros cuadrados” del paso de Vivienda.
        propertyMeters: "", //🌮

        // Corresponde con el campo “plantas” del paso de Vivienda.
        floorNumber: "", //🌮

        // Corresponde con el campo “baños” del paso de Vivienda.
        bathroomNumber: "", //🌮

        // Corresponde con el campo “Número de estancias” del paso de Vivienda.
        staysNumber: "", //🌮

        // Corresponde con los gráficos de la pregunta  “qué necesitas” del paso de Vivienda.
        gasNaturalUse: "", //🌮

        // Corresponde con el campo “Agua caliente” del paso de Vivienda. Aparece siempre.
        acsUse: "", // no procede //🌮

        // Corresponde con el campo “Cocina” del paso de Vivienda. Aparece sólo si se selecciona cocina en la pregunta de “qué necesitas”.
        kitchenUse: "", // no procede //🌮

        // Corresponde con el campo “Calefacción” del paso de Vivienda. Aparece sólo si se selecciona calefacción en la pregunta de “qué necesitas”.
        heatingUse: "", // no procede //🌮

        // Corresponde con el campo “¿Dónde está la caldera?” del paso de Vivienda. Aparece sólo si se selecciona calefacción en la pregunta de “qué necesitas”.
        boilerLocation: "", //🌮

        // radio. Corresponde con el campo “¿Tienes rejilla de ventilación superior?” del paso de Vivienda.
        hasVentilationGrill: "", //🌮

        // Corresponde con la pregunta “¿Qué uso haces del agua caliente?”.
        personsWater: "", //🌮

        
        // Es un campo obligatorio que no aparece reflejado en los diseños. Se trata de un campo numérico con 3 dígitos como mucho.
        MetersBoilerToWindow: "",

        // Es otro campo obligatorio con un selector de opciones con los valores solicitados por la API.
        MetersWaterIntake: "", 

        // radio. Es un Radiobutton al estilo del campo HasVentilationGrill. SOLO APARECE SI SE HA SELECCIONADO "COCINA" en la pregunta de “qué necesitas”.
        ConnectDeviceToKitchen: "", 

        // radio. Es un Radiobutton al estilo del campo HasVentilationGrill. SOLO APARECE SI SE HA SELECCIONADO "COCINA" en la pregunta de “qué necesitas”.
        ConvertDeviceKitchen: "", 

        // radio. Es un Radiobutton al estilo del campo HasVentilationGrill. SOLO APARECE SI SE HA SELECCIONADO "CALEFACCIÓN" en la pregunta de “qué necesitas”.
        ControllHeatingFloor: "", 

        // radio. Es un Radiobutton al estilo del campo HasVentilationGrill. SOLO APARECE SI SE HA SELECCIONADO "CALEFACCIÓN" en la pregunta de “qué necesitas”.
        RadiatorsBathroom: "", 

      }
    }
  },
  methods: {
    submitRequest() {
      console.log("Vivienda, enviar data...")
      // TODO
      //this.house.submitHouseData(houseFormData)
    }
  },
  template: /*html*/
    `<div class="an-form an-wrapper">


        <ul>
          <li>propertyMeters: {{ houseFormData.propertyMeters }}</li>
          <li>floorNumber: {{ houseFormData.floorNumber }}</li>
          <li>bathroomNumber: {{ houseFormData.bathroomNumber }}</li>
          <li>staysNumber: {{ houseFormData.staysNumber }}</li>
          <li>gasNaturalUse: {{ houseFormData.gasNaturalUse }}</li>

          <h4>Selecciona todo lo que tienes ahora mismo</h4>
          <li>acsUse: {{houseFormData.acsUse}}</li>
          <li>kitchenUse: {{houseFormData.kitchenUse}}</li>
          <li>heatingUse: {{houseFormData.heatingUse}}</li>
          <li>boilerLocation: {{houseFormData.boilerLocation}}</li>

          <p class="an-body-l-bold mb-xl">¿Tienes rejilla de ventilación superior?</p>
          <li>hasVentilationGrill: {{houseFormData.hasVentilationGrill}}</li>

          <p class="an-body-l-bold mb-xl">¿Qué uso haces del agua caliente?</p>
          <li>personsWater: {{houseFormData.personsWater}}</li>
        </ul>

        <form @submit.prevent="submitRequest">
          <p class="an-body-l-bold mb-xl">Rellena los datos de tu vivienda para poderte hacer un presupuesto lo más ajustado posible</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <!-- <input v-model="houseFormData.propertyMeters" type="text" class="an-input__field" placeholder="Metros cuadrados" required=""> -->
            
              <div class="an-select an-select--full-width mb-none">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="houseFormData.propertyMeters" class="an-select__native">
                  <option disabled value="">Metros cuadrados</option>
                  <option value="Hasta 100m2">Hasta 100m2</option>
                  <option value="De 100m2 a 180m2">De 100m2 a 180m2</option>
                  <option value="Mas de 180m2">Mas de 180m2</option>
                </select>
              </div>
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.floorNumber" type="text" class="an-input__field" placeholder="Plantas" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.bathroomNumber" type="text" class="an-input__field" placeholder="Baños" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.staysNumber" type="text" class="an-input__field" placeholder="Número de estancias" required="">
            <p class="an-input__caption an-body-s-regular">*(Incluye cocina y salón y excluye baños)</p>
            </div>
          </div>
          
          <p class="an-body-l-bold mb-xl">¿Que necesitas?</p>
          <div class="an-form__flex an-form__flex--4-cols mb-xxl">
            <label for="agua-caliente" class="an-form__item">
              <input v-model="houseFormData.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente" name="que-necesitas" value="solo ACS" checked>

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-cocina" class="an-form__item">
              <input v-model="houseFormData.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-cocina" name="que-necesitas" value="ACS+Cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion" class="an-form__item">
              <input v-model="houseFormData.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion" name="que-necesitas" value="ACS+Calefacción">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion-cocina" class="an-form__item">
              <input v-model="houseFormData.gasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion-cocina" name="que-necesitas" value="ACS+Cocina+Calefacción">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>
          </div>


          <p class="an-body-l-bold mb-xl">Selecciona todo lo que tienes ahora mismo</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">
          
            <div class="an-form__item">
              <p class="an-body-m-bold color-an-theme mb-m">Agua Caliente</p>
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="houseFormData.acsUse" class="an-select__native">
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
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="houseFormData.kitchenUse" class="an-select__native">
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
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="houseFormData.heatingUse" class="an-select__native">
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
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="houseFormData.boilerLocation" class="an-select__native">
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
              <input v-model="houseFormData.hasVentilationGrill" value="true" class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="vent-si">
              <label class="an-radio__label" for="vent-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input v-model="houseFormData.hasVentilationGrill" value="false" class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="vent-no">
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
              <input v-model="houseFormData.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-un-sitio" name="uso-agua-caliente" value="Hasta 2">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en un sitio para 1 o 2 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-dos-sitios" class="an-form__item">
              <input v-model="houseFormData.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-dos-sitios" name="uso-agua-caliente" value="Entre 3-4">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en dos sitios a la vez para 2 o 3 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-tres-sitios" class="an-form__item">
              <input v-model="houseFormData.personsWater" type="radio" class="an-selection__radio" id="agua-caliente-tres-sitios" name="uso-agua-caliente" value="Más de 4">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en tres o más sitios a la vez para 4 o más personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>
          </div>



          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Continuar</span>
          </button>
          
        </form>
      </div>
    `
}

export default houseForm;