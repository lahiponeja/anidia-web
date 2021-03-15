import clickOutside from '../../directives/clickoutDirective'

const houseForm = {
  directives: {
    'click-outside': clickOutside
  },
  inject: ["house", "lead"],
  data() {
    return {
      solarBudgetRequest: {
        houseType: "",
        panelsType: "",
        monthlyConsumption: "",
        annualConsumption: ""
      },

      sendingForm: false,
      submitFormError: false, // TODO
      knowMonthlyExpenses: false,
      knowAnnualConsumption: false

    }
  },
  methods: {
    submitRequest() {
      this.sendingForm = true
      this.lead.submitHouseData(this.solarBudgetRequest).then(() => {
        // window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details OK", "gas"));
        this.sendingForm = false
      }).catch((err) => {
        // window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details KO", "gas"));
        this.sendingForm = false
        console.error(err)
      })
    },
    toggleInfoItem(e) {
      e.target.parentElement.classList.toggle("an-info--hidden")
    },
    closeInfoItem(el) {
      el.classList.add("an-info--hidden")
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



        <!-- 🚧 Indícanos el tipo de vivienda 🚧 -->
        <p class="an-body-l-bold mb-xl">Indícanos el tipo de vivienda</p>
        <div class="an-form__flex an-form__flex--2-cols">

          <div class="an-form__item">
            <div class="an-select an-select--full-width">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="solarBudgetRequest.houseType" class="an-select__native" required>
                <option disabled value="">Seleccione una opción...</option>
                <option value="Unifamiliar de 1 planta sin A/A">Unifamiliar de 1 planta sin A/A</option>
                <option value="Unifamiliar de 1 planta con A/A">Unifamiliar de 1 planta con A/A</option>
                <option value="Unifamiliar de 2-3 plantas sin A/A ni piscina">Unifamiliar de 2-3 plantas sin A/A ni piscina</option>
                <option value="Unifamiliar de 2-3 plantas con A/A o piscina">Unifamiliar de 2-3 plantas con A/A o piscina</option>
                <option value="Unifamiliar de 2-3 plantas con A/A y piscina o calefacción eléctrica">Unifamiliar de 2-3 plantas con A/A y piscina o calefacción eléctrica</option>
              </select>
            </div>
          </div>
        </div>

        <!-- 🚧 ¿Sabes el gasto mensual que tienes de electricidad? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Sabes el gasto mensual que tienes de electricidad?</p>
        <!-- <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l"> -->
        <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l">
          <div class="an-radio an-form__item display-flex width-150 mb-0">
            <input v-model="knowMonthlyExpenses" :value="false" class="an-radio__input" type="radio" name="monthly-expenses" id="gasto-mensual-no">
            <label class="an-radio__label w-half" for="gasto-mensual-no">
              <span>
                No
              </span>
            </label>
          </div>
          <div class="an-radio an-form__item display-flex width-150 mb-0">
            <input v-model="knowMonthlyExpenses" :value="true" class="an-radio__input" checked="" type="radio" name="monthly-expenses" id="gasto-mensual-si">
            <label class="an-radio__label w-half" for="gasto-mensual-si">
              <span>
                Si
              </span>
            </label>
          </div>

          <div v-if="knowMonthlyExpenses" class="an-form__item mb-0">
            <div class="an-input mb-0">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="solarBudgetRequest.monthlyConsumption" class="an-select__native" required>
                <option disabled value="">Seleccione una opción...</option>
                <option value="50">50 €/mes</option>
                <option value="180">180 €/mes</option>
                <option value="250">250 €/mes</option>
                <option value="350">350 €/mes</option>
                <option value="500">500 €/mes</option>
              </select>
            </div>
          </div>
        </div>

        <!-- 🚧 ¿Sabes el consumo anual que tienes de electricidad? 🚧 -->
          <p class="an-body-l-bold mb-xl">¿Sabes el consumo anual que tienes de electricidad?</p>
          <!-- <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l"> -->
          <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l">
            <div class="an-radio an-form__item display-flex width-150 mb-0">
              <input v-model="knowAnnualConsumption" :value="false" class="an-radio__input" type="radio" name="annual-consumption" id="consumo-anual-no">
              <label class="an-radio__label w-half" for="consumo-anual-no">
                <span>
                  No
                </span>
              </label>
            </div>
            <div class="an-radio an-form__item display-flex width-150 mb-0">
              <input v-model="knowAnnualConsumption" :value="true" class="an-radio__input" checked="" type="radio" name="annual-consumption" id="consumo-anual-si">
              <label class="an-radio__label w-half" for="consumo-anual-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div v-if="knowAnnualConsumption" class="an-form__item mb-0">
              <div class="an-input mb-0">
                <input v-model="solarBudgetRequest.annualConsumption" type="number" min="0" class="an-input__field" required>
                <span class="an-input__field-right-text">KW/año</span>
              </div>
            </div>
          </div>

          <!-- 🚧 Selecciona el tipo de paneles 🚧 -->
          <div class="d-flex mb-xl">
            <p class="an-body-l-bold">¿Qué tipo de panel deseas?</p>
            <div class="an-info an-info--hidden" v-click-outside="closeInfoItem">
              <span class="an-info__icon an-icon--info" @click="toggleInfoItem"></span>
              <div class="an-info__box">
                Disponemos de paneles solares de la mejor calidad (Tier 1) y precio, ya incluidos en la talla básica. Si aun así quieres paneles LG de diseño puedes, escoge tu opción.
              </div>
            </div>
          </div>

          <div class="an-form__flex an-form__flex--2-cols">

            <div class="an-form__item">
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="solarBudgetRequest.panelsType" class="an-select__native">
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="Standard">Paneles recomendados por Anidia</option>
                  <option value="Diseño(LG)">Paneles de Diseño LG</option>
                </select>
              </div>
            </div>
          </div>


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