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
        roofType: ""
      },

      sendingForm: false,
      submitFormError: false, // TODO
      knowMonthlyExpenses: true,
    }
  },
  methods: {
    submitRequest() {
      this.sendingForm = true
      this.lead.submitHouseData(this.gasBudgetRequest).then(() => {
        // window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details OK", "gas"));
        this.sendingForm = false
      }).catch((err) => {
        // window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "details KO", "gas"));
        this.sendingForm = false
        console.log(err)
      })
    },

    // showVentilationGrillFn(){
    //   if (this.gasBudgetRequest.boilerLocation === "Lavadero/Terraza") {
    //     this.gasBudgetRequest.hasVentilationGrill = true
    //     this.showVentilationGrillRadios = false
    //     return false
    //   } else if(this.gasBudgetRequest.boilerLocation === "Cocina") {
    //     this.showVentilationGrillRadios = true
    //     return true
    //   } else if(this.gasBudgetRequest.boilerLocation === "Baño") {
    //     this.gasBudgetRequest.hasVentilationGrill = true
    //     this.showVentilationGrillRadios = false
    //     return false
    //   }
    // },
    // showConnectConvertDeviceToKitchenFn(){
    //   if (this.gasBudgetRequest.boilerLocation === "Lavadero/Terraza") {
    //     this.gasBudgetRequest.connectDeviceToKitchen = false
    //     this.gasBudgetRequest.convertDeviceKitchen = false
    //     this.showConnectConvertDeviceToKitchen = false
    //     return false
    //   } else if(this.gasBudgetRequest.boilerLocation === "Cocina") {
    //     this.showConnectConvertDeviceToKitchen = true
    //     return true
    //   } else if(this.gasBudgetRequest.boilerLocation === "Baño") {
    //     this.gasBudgetRequest.connectDeviceToKitchen = false
    //     this.gasBudgetRequest.convertDeviceKitchen = false
    //     this.showConnectConvertDeviceToKitchen = false
    //     return false
    //   }
    // },

    // toggleInfoItem(e) {
    //   e.target.parentElement.classList.toggle("an-info--hidden")
    // },

    // closeInfoItem(el) {
    //   el.classList.add("an-info--hidden")
    // }
  },
  computed: {
    // kitchenSelected() {
    //   return (
    //     this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina"
    //     ||
    //     this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina+Calefacción"
    //   )
    // },

    // heatingSelected() {
    //   return (
    //     this.gasBudgetRequest.gasNaturalUse === "ACS+Calefacción"
    //     ||
    //     this.gasBudgetRequest.gasNaturalUse === "ACS+Cocina+Calefacción"
    //   )
    // }
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
          <!-- 🚧 ¿Cómo es el tejado de tu vivienda? 🚧 -->
          <p class="an-body-l-bold mb-xl">¿Cómo es el tejado de tu vivienda?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-l">
            <div class="an-radio an-form__item">
              <input v-model="solarBudgetRequest.roofType" value="Plano o inclinación menor a 20º" class="an-radio__input" checked="" type="radio" name="roof-type" id="piso-plano">
              <label class="an-radio__label" for="piso-plano">
                <span>
                  Plano
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input v-model="solarBudgetRequest.roofType" value="Inclinación superior a 20º" class="an-radio__input" type="radio" name="roof-type" id="piso-inclinado">
              <label class="an-radio__label" for="piso-inclinado">
                <span>
                  Inclinado
                </span>
              </label>
            </div>
          </div>
          
          <!-- 🚧 ¿Sabes el gasto mensual que tienes de electricidad? 🚧 -->
          <p class="an-body-l-bold mb-xl">¿Sabes el gasto mensual que tienes de electricidad?</p>
          <div class="an-form__flex an-form__flex--2-cols an-form__flex--justify-normal mb-l">
            <div class="an-radio an-form__item display-flex">
              <input v-model="knowMonthlyExpenses" :value="true" class="an-radio__input" checked="" type="radio" name="monthly-expenses" id="gasto-mensual-si">
              <label class="an-radio__label w-half" for="gasto-mensual-si">
                <span>
                  Si
                </span>
              </label>
              
              <input v-model="knowMonthlyExpenses" :value="false" class="an-radio__input" type="radio" name="monthly-expenses" id="gasto-mensual-no">
              <label class="an-radio__label w-half" for="gasto-mensual-no">
                <span>
                  No
                </span>
              </label>
            </div>

            <div class="an-form__item">
              <div class="an-input mb-0">
                <input v-model="solarBudgetRequest.monthlyConsumption" type="number" min="0" required="required" class="an-input__field">
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


        </form>
      </div>
    `
}

export default houseForm;