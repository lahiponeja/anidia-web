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
  inject: ["global", "house"],
  data() {
    return {
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
  template: /*html*/`
    <div class="an-house an-funnel__titles bg-white pt-xxxl pb-xxxl">
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
        <budget-ready />
      </template>


      <!-- START TESTING -->
      <div class="mt-xxl mb-xxl">
        <div @click="house.changeStep('cobertura')">Cobertura</div>
        <div @click="house.changeStep('vivienda')">Vivienda</div>
        <div @click="house.changeStep('presupuesto')">Presupuesto</div>
        <div @click="house.changeStep('presupuesto-realizado')">Presupuesto Realizado</div>
      </div>
      <!-- END TESTING -->

    </div>
  `
}

export default homeView;