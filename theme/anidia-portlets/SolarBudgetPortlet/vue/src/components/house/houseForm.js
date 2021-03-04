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
        monthlyConsumption: "",
      },

      sendingForm: false,
      submitFormError: false, // TODO
      knowMonthlyExpenses: true,
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
          <!-- 🚧 ¿Sabes el gasto mensual que tienes de electricidad? 🚧 -->
          <p class="an-body-l-bold mb-xl">¿Sabes el gasto mensual que tienes de electricidad?</p>
          <!-- <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l"> -->
          <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-l">
            <div class="an-radio an-form__item display-flex width-150">
              <input v-model="knowMonthlyExpenses" :value="true" class="an-radio__input" checked="" type="radio" name="monthly-expenses" id="gasto-mensual-si">
              <label class="an-radio__label w-half" for="gasto-mensual-si">
                <span>
                  Si
                </span>
              </label>
            </div>
            <div class="an-radio an-form__item width-150">
              <input v-model="knowMonthlyExpenses" :value="false" class="an-radio__input" type="radio" name="monthly-expenses" id="gasto-mensual-no">
              <label class="an-radio__label w-half" for="gasto-mensual-no">
                <span>
                  No
                </span>
              </label>
            </div>

            <div v-if="knowMonthlyExpenses" class="an-form__item mb-0">
              <div class="an-input mb-0">
                <input v-model="solarBudgetRequest.monthlyConsumption" type="number" min="0" class="an-input__field" required>
                <span class="an-input__field-right-text">€/mes</span>
              </div>
            </div>
          </div>



          <template v-if="!knowMonthlyExpenses">
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