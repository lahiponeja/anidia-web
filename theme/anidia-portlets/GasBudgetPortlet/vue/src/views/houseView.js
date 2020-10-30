import coverageForm from '../components/house/coverageForm'
import houseForm from '../components/house/houseForm'
import budgetCard from '../components/house/budgetCard'
import budgetReady from '../components/house/budgetReady'

const homeView = {
  components: {
    'coverage-form': coverageForm,
    'house-form': houseForm,
    'budget-card': budgetCard,
    'budget-ready': budgetReady,
  },
  inject: ["house"],
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
    }
  },
  mounted() {
    document.querySelector('.an-centered-featured').classList.remove('hide');
  },
  template: /*html*/`
    <div class="an-house an-funnel__titles bg-white pt-xxxl pb-xxxl">
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

        <template v-if="houseActiveStep.name === 'cobertura'">
          <coverage-form />
        </template>
        <template v-else-if="houseActiveStep.name === 'vivienda'">
          <house-form />
        </template>
        <template v-else-if="houseActiveStep.name === 'presupuesto'">
          <budget-card />
        </template>
        <template v-else-if="houseActiveStep.name === 'presupuesto-realizado'">
          <budget-ready @form-success="setFormSuccess" />
        </template>


        <!-- START TESTING -->
        <!--
        <div class="mt-xxl mb-xxl">
          <div @click="house.changeHouseStep('cobertura')">Cobertura</div>
          <div @click="house.changeHouseStep('vivienda')">Vivienda</div>
          <div @click="house.changeHouseStep('presupuesto')">Presupuesto</div>
          <div @click="house.changeHouseStep('presupuesto-realizado')">Presupuesto Realizado</div>
        </div>
        -->
        <!-- END TESTING -->

      </template>
      <template v-else>
        <div class="an-wrapper an-wrapper--sml">
          <p class="an-h6 color-an-theme-dark-grey mb-l">SOLICITUD ONLINE REALIZADA CON ÉXITO</p>
          <p class="an-h2 color-an-theme mb-l">Muchas gracias {{ house.state.userFullName }} </p>
          <p class="an-body-l-regular color-an-theme">Nos pondremos en contacto contigo para darte toda la información al detalle</p>
        </div>
      </template>
    </div>
  `
}

export default homeView;
