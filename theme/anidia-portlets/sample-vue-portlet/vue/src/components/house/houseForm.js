const houseForm = {
  inject: ["global", "house"],
  data() {
    return {
      houseFormData: {
        // HouseType: En este punto, este dato debe estar almacenado en el state global (global.js). Es el tipo de casa elegida en el primer paso del proceso.
        
        // Corresponde con el campo “metros cuadrados” del paso de Vivienda.
        PropertyMeters: "", 

        // Corresponde con el campo “plantas” del paso de Vivienda.
        FloorNumber: "", 

        // Corresponde con el campo “baños” del paso de Vivienda.
        BathroomNumber: "", 

        // Corresponde con el campo “Número de estancias” del paso de Vivienda.
        StaysNumber: "", 

        // Corresponde con los gráficos de la pregunta  “qué necesitas” del paso de Vivienda.
        GasNaturalUse: "", 

        // Corresponde con el campo “Agua caliente” del paso de Vivienda. Aparece siempre.
        ACSUse: "", // no procede

        // Corresponde con el campo “Cocina” del paso de Vivienda. Aparece sólo si se selecciona cocina en la pregunta de “qué necesitas”.
        KitchenUse: "", // no procede

        // Corresponde con el campo “Calefacción” del paso de Vivienda. Aparece sólo si se selecciona calefacción en la pregunta de “qué necesitas”.
        HeatingUse: "", // no procede

        // Corresponde con el campo “¿Dónde está la caldera?” del paso de Vivienda. Aparece sólo si se selecciona calefacción en la pregunta de “qué necesitas”.
        BoilerLocation: "",

        // Corresponde con la pregunta “¿Qué uso haces del agua caliente?”.
        PersonsWater: "",

        
        // 🔥 FIELDS BELOW TO INCLUDE IN THE FORM

        // Es un campo obligatorio que no aparece reflejado en los diseños. Se trata de un campo numérico con 3 dígitos como mucho.
        MetersBoilerToWindow: "",

        // Es otro campo obligatorio con un selector de opciones con los valores solicitados por la API.
        MetersWaterIntake: "", 

        // radio. Corresponde con el campo “¿Tienes rejilla de ventilación superior?” del paso de Vivienda.
        HasVentilationGrill: "", 

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
        <form @submit.prevent="submitRequest">
          <p class="an-body-l-bold mb-xl">Rellena los datos de tu vivienda para poderte hacer un presupuesto lo más ajustado posible</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <input v-model="houseFormData.PropertyMeters" type="text" class="an-input__field" placeholder="Metros cuadrados" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.FloorNumber" type="text" class="an-input__field" placeholder="Plantas" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.BathroomNumber" type="text" class="an-input__field" placeholder="Baños" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="houseFormData.StaysNumber" type="text" class="an-input__field" placeholder="Número de estancias" required="">
            <p class="an-input__caption an-body-s-regular">*(Incluye cocina y salón y excluye baños)</p>
            </div>
          </div>
          
          <p class="an-body-l-bold mb-xl">¿Que necesitas?</p>
          <div class="an-form__flex an-form__flex--4-cols mb-xxl">
            <label for="agua-caliente" class="an-form__item">
              <input v-model="houseFormData.GasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente" name="que-necesitas" value="agua caliente" checked>

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-cocina" class="an-form__item">
              <input v-model="houseFormData.GasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-cocina" name="que-necesitas" value="agua caliente y cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion" class="an-form__item">
              <input v-model="houseFormData.GasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion" name="que-necesitas" value="agua caliente, calefaccion">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion-cocina" class="an-form__item">
              <input v-model="houseFormData.GasNaturalUse" type="radio" class="an-selection__radio" id="agua-caliente-calefaccion-cocina" name="que-necesitas" value="agua caliente, calefaccion y cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>
          </div>


          <p class="an-body-l-bold mb-xl">Selecciona todo lo que tienes ahora mismo</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">
          
            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="houseFormData.ACSUse" class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected="">Agua Caliente</option>
                <option value="Agua Caliente 1">Agua Caliente 1</option>
                <option value="Agua Caliente 2">Agua Caliente 2</option>
                <option value="Agua Caliente 3">Agua Caliente 3</option>
                <option value="Agua Caliente 4">Agua Caliente 4</option>
                <option value="Agua Caliente 5">Agua Caliente 5</option>
              </select>

              <!-- <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div> -->
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="houseFormData.HeatingUse" class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected="">Calefacción</option>
                <option value="Calefacción 1">Calefacción 1</option>
                <option value="Calefacción 2">Calefacción 2</option>
                <option value="Calefacción 3">Calefacción 3</option>
                <option value="Calefacción 4">Calefacción 4</option>
                <option value="Calefacción 5">Calefacción 5</option>
              </select>

              <!-- <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div> -->
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="houseFormData.KitchenUse" class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected="">Cocina</option>
                <option value="Cocina 1">Cocina 1</option>
                <option value="Cocina 2">Cocina 2</option>
                <option value="Cocina 3">Cocina 3</option>
                <option value="Cocina 4">Cocina 4</option>
                <option value="Cocina 5">Cocina 5</option>
              </select>

              <!-- <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div> -->
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="houseFormData.BoilerLocation" class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected="">¿Donde está la caldera?</option>
                <option value="Donde está la caldera 1">Donde está la caldera 1</option>
                <option value="Donde está la caldera 2">Donde está la caldera 2</option>
                <option value="Donde está la caldera 3">Donde está la caldera 3</option>
                <option value="Donde está la caldera 4">Donde está la caldera 4</option>
                <option value="Donde está la caldera 5">Donde está la caldera 5</option>
              </select>

              <!-- <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div> -->
            </div>
          </div>

          <p class="an-body-l-bold mb-xl">¿Tienes rejilla de ventilación superior?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
            <div class="an-radio an-form__item">
              <input class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="vent-si">
              <label class="an-radio__label" for="vent-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="vent-no">
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
              <input v-model="houseFormData.PersonsWater" type="radio" class="an-selection__radio" id="agua-caliente-un-sitio" name="uso-agua-caliente" value="Agua caliente en un sitio para 1 o 2 personas">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en un sitio para 1 o 2 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-dos-sitios" class="an-form__item">
              <input v-model="houseFormData.PersonsWater" type="radio" class="an-selection__radio" id="agua-caliente-dos-sitios" name="uso-agua-caliente" value="Agua caliente en dos sitios a la vez para 2 o 3 personas">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en dos sitios a la vez para 2 o 3 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-tres-sitios" class="an-form__item">
              <input v-model="houseFormData.PersonsWater" type="radio" class="an-selection__radio" id="agua-caliente-tres-sitios" name="uso-agua-caliente" value="Agua caliente en tres o más sitios a la vez para 4 o más personas">

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