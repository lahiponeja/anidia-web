const compHeating = {
  inject: ["global", "comparator"],
  data() {
    return {
      savingsData: {
        heatingUse: "",
        singleFamilyHouse: true,
        lastFloor: true,
        surfaceHouse: 0,
      }
    }
  },
  mounted() {
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  methods: {
    submitRequest() {
      this.comparator.setSavingByUse({
        heatingUse: this.savingsData.heatingUse,
        singleFamilyHouse: this.savingsData.singleFamilyHouse,
        lastFloor: this.savingsData.lastFloor,
        surfaceHouse: this.savingsData.surfaceHouse,
      })

      this.comparator.changeStepComponent('comp-kitchen')
    },

    goBack() {
      this.comparator.changeStepComponent('comp-hot-water')
    },

  },
  template: /*html*/`
    <div class="an-form an-wrapper">
      <form @submit.prevent="submitRequest">

        <!-- 🚧 Para la calefacción uso: 🚧 -->
        <p class="an-body-l-bold mb-xl">Para la calefacción uso:</p>
        <div class="an-form__flex an-form__flex--2-cols mb-xxl">

          <div class="an-form__item">
            <div class="an-select an-select--full-width">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="savingsData.heatingUse" class="an-select__native" required>
                <option disabled value="">Seleccione energía...</option>
                <option value="GLP">GLP</option>
                <option value="GOC">GOC</option>
                <option value="Electricidad">Electricidad</option>
                <option value="Butano">Butano</option>
              </select>
            </div>
          </div>
        </div>

        <!-- 🚧 ¿Tu vivienda es unifamiliar? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Tu vivienda es unifamiliar?</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-radio an-form__item">
            <input v-model="savingsData.singleFamilyHouse" :value="true" class="an-radio__input" checked="" type="radio" name="single-family-use" id="single-family-use-si">
            <label class="an-radio__label" for="single-family-use-si">
              <span>
                Si
              </span>
            </label>
          </div>

          <div class="an-radio an-form__item">
            <input v-model="savingsData.singleFamilyHouse" :value="false" class="an-radio__input" type="radio" name="single-family-use" id="single-family-use-no">
            <label class="an-radio__label" for="single-family-use-no">
              <span>
                No
              </span>
            </label>
          </div>
        </div>

        <!-- 🚧 ¿Es una última planta? 🚧 -->
        <template v-if="!savingsData.singleFamilyHouse">
          <p class="an-body-l-bold mb-xl">¿Es una última planta?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
            <div class="an-radio an-form__item">
              <input v-model="savingsData.lastFloor" :value="true" class="an-radio__input" checked="" type="radio" name="last-floor" id="last-floor-si">
              <label class="an-radio__label" for="last-floor-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input v-model="savingsData.lastFloor" :value="false" class="an-radio__input" type="radio" name="last-floor" id="last-floor-no">
              <label class="an-radio__label" for="last-floor-no">
                <span>
                  No
                </span>
              </label>
            </div>
          </div>
        </template>

        <!-- 🚧 ¿Cual es su superficie? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Cual es su superficie?</p>
        <div class="an-form__flex an-form__flex--3-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-form__item">
            <div class="an-input">
              <input v-model="savingsData.surfaceHouse" type="number" class="an-input__field" placeholder="Metros cuadrados" required="">
            </div>
          </div>
        </div>

        <div class="an-form__flex an-form__flex--6-cols mb-xxl">
          <button @click="goBack" type="button" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Anterior</span>
          </button>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Continuar</span>
          </button>
        </div>

      </form>
    </div>
  `
}

export default compHeating