const compHotWater = {
  inject: ["global", "comparator"],
  data() {
    return {
      savingsData: {
        acsIndividual: true,
        heatingIndividual: true,
        acsUse: "",
        numberOfPeople: 1,
      }
    }
  },
  methods: {
    submitRequest() {
      this.comparator.setSavingByUse({
        acsIndividual: this.savingsData.acsIndividual,
        heatingIndividual: this.savingsData.heatingIndividual,
        acsUse: this.savingsData.acsUse,
        numberOfPeople: this.savingsData.numberOfPeople,
      })
      window.dataLayer.push(this.comparator.getDatalayerWaterStepInfo("engagement", "calculator", "hotwater"));
      
      this.comparator.changeStepComponent('comp-heating')
    },

    startOver() {
      window.dataLayer.push(this.comparator.getDatalayerInitialInfo("engagement", "calculator", "back"));
      this.global.changeView('funnel')
    },

    setNumberOfPeople(operation) {
      if(operation === "add") {
        ++this.savingsData.numberOfPeople
      } else if("substract") {
        if(this.savingsData.numberOfPeople > 1) {
          --this.savingsData.numberOfPeople
        }
      }
    }
  },
  mounted() {
    window.dataLayer.push(this.comparator.getDatalayerFirstStepInfo("engagement", "calculator", "hotwater"));//heating system??
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template: /*html*/`
    <div class="an-form an-wrapper">
      <form @submit.prevent="submitRequest">

        <!-- 🚧 ¿Haces uso individual del agua caliente? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Haces uso individual del agua caliente?</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-radio an-form__item">
            <input v-model="savingsData.acsIndividual" :value="true" class="an-radio__input" checked="" type="radio" name="hot-water" id="hot-water-si">
            <label class="an-radio__label" for="hot-water-si">
              <span>
                Si
              </span>
            </label>
          </div>

          <div class="an-radio an-form__item">
            <input v-model="savingsData.acsIndividual" :value="false" class="an-radio__input" type="radio" name="hot-water" id="hot-water-no">
            <label class="an-radio__label" for="hot-water-no">
              <span>
                No
              </span>
            </label>
          </div>
        </div>

        <!-- 🚧 Mi caldera/calentador es: 🚧 -->
        <p class="an-body-l-bold mb-xl">Mi caldera/calentador es:</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-radio an-form__item">
            <input v-model="savingsData.heatingIndividual" :value="true" class="an-radio__input" checked="" type="radio" name="individual-heating" id="individual-heating-si">
            <label class="an-radio__label" for="individual-heating-si">
              <span>
                Individual
              </span>
            </label>
          </div>

          <div class="an-radio an-form__item">
            <input v-model="savingsData.heatingIndividual" :value="false" class="an-radio__input" type="radio" name="individual-heating" id="individual-heating-no">
            <label class="an-radio__label" for="individual-heating-no">
              <span>
                Colectivo
              </span>
            </label>
          </div>
        </div>

        <!-- 🚧 Para el agua caliente uso: 🚧 -->
        <p class="an-body-l-bold mb-xl">Para el agua caliente uso:</p>
        <div class="an-form__flex an-form__flex--2-cols mb-xxl">

          <div class="an-form__item">
            <div class="an-select an-select--full-width">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="savingsData.acsUse" class="an-select__native" required>
                <option disabled value="">Seleccione energía...</option>
                <option value="GLP">GLP</option>
                <option value="GOC">Gas-oil Canalizado</option>
                <option value="Electricidad">Electricidad</option>
                <option value="Butano">Butano</option>
              </select>
            </div>
          </div>
        </div>

        <!-- 🚧 ¿Cuántas personas vivís en casa? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Cuántas personas vivís en casa?</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-counter">
            <button class="an-counter__btn" @click="setNumberOfPeople('substract')" type="button"> - </button>
            <span class="an-body-m-regular">{{ savingsData.numberOfPeople }}</span>
            <button class="an-counter__btn an-counter__btn--green" @click="setNumberOfPeople('add')" type="button"> + </button>
          </div>
        </div>

        <div class="an-form__flex an-form__flex--6-cols mb-xxl">
          <button @click="startOver" type="button" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
            <span>Volver a calcular</span>
          </button>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Continuar</span>
          </button>
        </div>

      </form>
    </div>
  `
}

export default compHotWater