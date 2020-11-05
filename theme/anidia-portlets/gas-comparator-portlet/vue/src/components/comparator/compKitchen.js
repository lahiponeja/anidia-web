const compKitchen = {
  inject: ["global", "comparator"],
  data() {
    return {
      savingsData: {
        kitchenUse: "",
        weeklyKitchenUse: 0,
      },

      sendingForm: false,
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
        kitchenUse: this.savingsData.kitchenUse,
        weeklyKitchenUse: this.savingsData.weeklyKitchenUse,
      })

      this.sendingForm = true
      this.comparator.sendSavingByUseService(this.comparator.state.savingsByUse)
        .then((res) => { 
          this.sendingForm = false 
          this.comparator.changeStepComponent('comp-saving')
        })
        .catch((err) => { this.sendingForm = false })
    },

    goBack() {
      this.comparator.changeStepComponent('comp-heating')
    },

    setWeeklyKitchenUse(operation) {
      if(operation === "add") {
        ++this.savingsData.weeklyKitchenUse
      } else if("substract") {
        if(this.savingsData.weeklyKitchenUse > 1) {
          --this.savingsData.weeklyKitchenUse
        }
      }
    }

  },
  template: /*html*/`
    <div class="an-form an-wrapper">
      <form @submit.prevent="submitRequest">

        <!-- 🚧 ¿Qué energía usas para cocinar? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Qué energía usas para cocinar?</p>
        <div class="an-form__flex an-form__flex--2-cols mb-xxl">

          <div class="an-form__item">
            <div class="an-select an-select--full-width">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select v-model="savingsData.kitchenUse" class="an-select__native" required>
                <option disabled value="">Seleccione energía...</option>
                <option value="GLP">GLP</option>
                <option value="GOC">Gas-oil Canalizado</option>
                <option value="Electricidad">Electricidad</option>
                <option value="Butano">Butano</option>
              </select>
            </div>
          </div>
        </div>

        
        <!-- 🚧 ¿Cuántas veces a la semana? 🚧 -->
        <p class="an-body-l-bold mb-xl">¿Cuántas veces a la semana?</p>
        <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
          <div class="an-counter">
            <button class="an-counter__btn" @click="setWeeklyKitchenUse('substract')" type="button"> - </button>
            <span class="an-body-m-regular">{{ savingsData.weeklyKitchenUse }}</span>
            <button class="an-counter__btn an-counter__btn--green" @click="setWeeklyKitchenUse('add')" type="button"> + </button>
          </div>
        </div>

        <div class="an-form__flex an-form__flex--6-cols mb-xxl">
          <button @click="goBack" type="button" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
            <span>Anterior</span>
          </button>

          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span v-if="!sendingForm">Continuar</span>
            <span v-else>Cargando...</span>
          </button>
        </div>

      </form>
    </div>
  `
}

export default compKitchen