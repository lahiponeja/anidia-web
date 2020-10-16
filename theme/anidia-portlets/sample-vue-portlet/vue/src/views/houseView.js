const homeView = {
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
      return this.house.state.homeSteps.find((step) => step.active)
    },
    showSteps() {
      return !(this.houseActiveStep.name === 'presupuesto-realizado')
    }
  },
  template: /*html*/`
    <div class="an-house an-funnel__titles bg-white pt-xxxl pb-xxxl">
      <div class="an-house__steps an-wrapper an-wrapper--med">
        <div class="an-funnel__titles">
          <p class="an-h6 color-an-theme-dark-grey mb-l">{{ houseActiveStep.heading.title }}</p>
          <p class="an-body-l-bold color-an-theme">{{ houseActiveStep.heading.subtitle }}</p>
        </div>

        <ul v-if="showSteps" class="an-house__steps-list">
          <template v-for="(houseStep, index) in houseState.homeSteps" class="an-house__steps-item">
            <div v-if="houseStep.icon" class="an-house__steps-item" :class="{'an-house__steps-item--active': houseStep.active }">
              <span class="an-house__steps-item-icon" :class="houseStep.icon"></span>
              <p class="an-h5">{{ houseStep.name }}</p>
            </div>
          </template>
        </ul>
      </div>

      <!-- START TESTING -->
      <div class="mt-xxl mb-xxl">
        <div @click="house.changeStep('cobertura')">Cobertura</div>
        <div @click="house.changeStep('vivienda')">Vivienda</div>
        <div @click="house.changeStep('presupuesto')">Presupuesto</div>
        <div @click="house.changeStep('presupuesto-realizado')">Presupuesto Realizado</div>
      </div>

      <template v-if="houseActiveStep.name === 'cobertura'">
        <h2>Componente: Formulario de cobertura</h2>
      </template>
      <template v-else-if="houseActiveStep.name === 'vivienda'">
        <h2>Componente: Formulario de vivienda</h2>
      </template>
      <template v-else-if="houseActiveStep.name === 'presupuesto'">
        <h2>Card de presupuesto</h2>
      </template>
      <template v-else-if="houseActiveStep.name === 'presupuesto-realizado'">
        <h2>{{ houseActiveStep.heading.title }}</h2>
      </template>
      <!-- END TESTING -->

      <div class="an-funnel__footer">
        <button type="button" @click="global.changeStep(optionPicked)" :disabled="!optionPicked" :class="{ 'an-btn--disabled': !optionPicked }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple">
          <span>Continuar</span>
        </button>
      </div>
    </div>
  `
}

export default homeView;