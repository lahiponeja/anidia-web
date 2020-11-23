import provincias from '../../enums/provincias'

const funnelForm = {
  inject: ["global", "comparator"],
  data() {
    return {
      energyConsumption: {
        acsUse: false,
        heatingUse: false,
        kitchenUse: false,
        energyType: "",
        electricityConsumption: 0,
      },
      known: true,
      provincesArr: provincias,
      province: "",
      sendingForm: false,
      energyTypeKind: {
        glp: "(Kg/Año)",
        goc: "(L/Año)",
        electricity: "(kWh/Año)",
        butane: "(Bombonas/Año)",

      }
    }
  },
  computed: {
    energyUnit() {
      return this.energyTypeKind[this.energyConsumption.energyType]
    }
  },
  methods: {
    submitRequest() {
      if(this.known) {
        this.sendingForm = true
        this.comparator.setSavingsByConsumption(this.energyConsumption)
          .then((res) => { this.sendingForm = false })
          .catch((err) => { this.sendingForm = false })
      } else {
        this.comparator.setSavingByUse({
          province: this.province
        })
        this.global.changeView('comparator')
        this.comparator.changeStepComponent('comp-hot-water')
      }
      window.dataLayer.push(this.comparator.getDatalayerFirstStepInfo("engagement", "calculator", "energyspent"));
    }
  },
  mounted() {
    window.dataLayer.push(this.comparator.getDatalayerInitialInfo("engagement", "calculator", "energyspent"));
    if(document.querySelector('.an-featured')) document.querySelector('.an-featured').classList.add('hide');
  },
  template: /*html*/`
    <div class="an-form an-wrapper">

      <!-- <h3>known: {{ known }}</h3>
      <h3>energyConsumption.energyType: {{ energyConsumption.energyType }}</h3>
      <h3>energyConsumption.electricityConsumption: {{ energyConsumption.electricityConsumption }}</h3>
      <h3>energyConsumption.acsUse: {{ energyConsumption.acsUse }}</h3>
      <h3>energyConsumption.heatingUse: {{ energyConsumption.heatingUse }}</h3>
      <h3>energyConsumption.kitchenUse: {{ energyConsumption.kitchenUse }}</h3>

      <div>
      gasConsumptionComparison: {
        <div>consumptionRequired: {{ comparator.state.gasConsumptionComparison.consumptionRequired }},</div>
        <div>currentCost: {{ comparator.state.gasConsumptionComparison.currentCost }},</div>
        <div>futureCost: {{ comparator.state.gasConsumptionComparison.futureCost }},</div>
        <div>savings: {{ comparator.state.gasConsumptionComparison.savings }},</div>
      }
      </div> -->

      <form @submit.prevent="submitRequest">
        <!-- 🚧 ¿Sabes cuánto consumes en energía al año? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Sabes cuánto consumes en energía al año?</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-l">
          <div class="an-radio an-form__item">
            <input v-model="known" :value="true" class="an-radio__input" checked="" type="radio" name="constrol-heating-floor" id="constrol-heating-floor-si">
            <label class="an-radio__label" for="constrol-heating-floor-si">
              <span>
                Si
              </span>
            </label>
          </div>

          <div class="an-radio an-form__item">
            <input v-model="known" :value="false" class="an-radio__input" type="radio" name="constrol-heating-floor" id="constrol-heating-floor-no">
            <label class="an-radio__label" for="constrol-heating-floor-no">
              <span>
                No
              </span>
            </label>
          </div>
        </div>

        <template v-if="known">
          <!-- 🚧 ¡Genial! indícanos tu consumo anual 🚧 -->
          <p class="an-body-l-bold mb-xl">¡Genial! indícanos tu consumo anual {{ energyUnit }} </p>
          <div class="an-form__flex an-form__flex--2-cols">

            <div class="an-form__item">
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="energyConsumption.energyType" class="an-select__native" required>
                  <option disabled value="">Seleccione una opción...</option>
                  <option value="glp">GLP</option>
                  <option value="goc">Gas-oil Canalizado</option>
                  <option value="electricity">Electricidad</option>
                  <option value="butane">Butano</option>
                </select>
              </div>
            </div>

            <div class="an-form__item">
              <div class="an-input">
                <input v-model="energyConsumption.electricityConsumption" type="text" class="an-input__field" required="">
              </div>
            </div>
          </div>

          <!-- 🚧 ¿En qué lo empleas? 🚧 -->
          <p class="an-body-l-bold mb-xl">¿En qué lo empleas?</p>
          <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal">
            <div class="an-checkbox an-form__item">
              <input v-model="energyConsumption.acsUse" value="Si" class="an-checkbox__input" checked="" type="checkbox" name="check1" id="check1">
              <label class="an-checkbox__label" for="check1">
                <span>
                  Agua caliente
                </span>
              </label>
            </div>

            <div class="an-checkbox an-form__item">
              <input v-model="energyConsumption.heatingUse" value="Si" class="an-checkbox__input" checked="" type="checkbox" name="check2" id="check2">
              <label class="an-checkbox__label" for="check2">
                <span>
                  Calefacción
                </span>
              </label>
            </div>

            <div class="an-checkbox an-form__item">
              <input v-model="energyConsumption.kitchenUse" value="Si" class="an-checkbox__input" checked="" type="checkbox" name="check3" id="check3">
              <label class="an-checkbox__label" for="check3">
                <span>
                  Cocina
                </span>
              </label>
            </div>
          </div>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span v-if="!sendingForm">Ver ahorro</span>
            <span v-else>Enviando...</span>
          </button>
        </template>
        <template v-else>

          <div class="an-form__flex an-form__flex--2-cols">
            <div class="an-form__item">
              <p class="an-body-m-bold color-an-theme mb-m">Agua Caliente</p>
              <div class="an-select an-select--full-width">
                <span class="an-select__icon an-icon--chevron-down"></span>
                <select v-model="province" class="an-select__native" required>
                  <option disabled value="">¿En qué provincia vives?</option>
                  <option v-for="(province, index) in provincesArr" :key="index">
                    {{ province }}
                  </option>
                </select>
              </div>
            </div>
          </div>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span v-if="!sendingForm">Continuar</span>
            <span v-else>Enviando...</span>
          </button>
        </template>

        <p class="an-body-s-bold color-an-theme-dark-grey mt-xl">
          También puedes llamarnos grátis al <span class="color-an-theme">900 18 18 18</span>
        </p>

      </form>
    </div>`
}

export default funnelForm