import coverageForm from '../components/house/coverageForm'
// import houseForm from '../components/house/houseForm'
// import budgetCard from '../components/house/budgetCard'
// import budgetReady from '../components/house/budgetReady'
// import modal from '../components/modal'

const homeView = {
  components: {
    'coverage-form': coverageForm,
    // 'house-form': houseForm,
    // 'budget-card': budgetCard,
    // 'budget-ready': budgetReady,
    // modal,
  },
  inject: ["global", "house"],
  data() {
    return {
      formSuccess: false,
    }
  },
  computed: {
    houseState() {
      return this.house.state
    },
    houseActiveStep() {
      return this.house.state.houseSteps.find((step) => step.active)
    },
    showSteps() {
      return !(this.houseActiveStep.name === 'presupuesto-realizado')
    }
  },
  methods: {
    setFormSuccess() {
      this.formSuccess = true
    },

    goBack() {
      this.global.changeView('funnel')
      this.house.changeHouseStep('cobertura')
    },
  },
  mounted() {
    if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.remove('hide');
  },
  template: /*html*/`
    <div class="an-house an-funnel__titles bg-white pt-xxxl pb-xxxl">
      <!-- <modal /> -->
      <template v-if="!formSuccess">
        <div class="an-house__steps an-wrapper an-wrapper--med">
          <div class="an-funnel__titles mb-xl">
            <p class="an-h6 color-an-theme-dark-grey mb-l">{{ houseActiveStep.heading.title }}</p>
            <p class="an-body-l-bold color-an-theme">{{ houseActiveStep.heading.subtitle }}</p>
          </div>

          <ul v-if="showSteps" class="an-house__steps-list mb-xxxl">
            <template v-for="(houseStep, index) in houseState.houseSteps" class="an-house__steps-item">
              <div v-if="houseStep.icon" class="an-house__steps-item" :class="{'an-house__steps-item--active': houseStep.active }">
                <span class="an-house__steps-item-icon" :class="houseStep.icon"></span>
                <p class="an-h5">{{ houseStep.name }}</p>
              </div>
            </template>
          </ul>
        </div>

        <transition name="view">
          <component :is="houseActiveStep.component" @form-success="setFormSuccess"></component>
        </transition>

      </template>
      <template v-else>
        <div class="an-wrapper an-wrapper--sml">
          <p class="an-h6 color-an-theme-dark-grey mb-l">SOLICITUD ONLINE REALIZADA CON ÉXITO</p>
          <p class="an-h2 color-an-theme mb-l">Muchas gracias {{ house.state.userFullName }} </p>
          <p class="an-body-l-regular color-an-theme">Nos pondremos en contacto contigo para darte toda la información al detalle</p>
          <div class="an-form__flex an-form__flex--6-cols justify-content-center">
            <button @click="goBack" type="button" class="an-btn an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
              <span>Volver a calcular</span>
            </button>
          </div> 
        </div>
      </template>
    </div>
  `
}

export default homeView;
