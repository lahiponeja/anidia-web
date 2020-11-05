const results = {
  inject: ["global", "comparator"],
  computed: {
    gasConsumption() {
      return this.comparator.state.gasConsumptionComparison
    },
    isSavingsNegative() {
      const savings = this.comparator.state.gasConsumptionComparison.savings
      const parsedNun = parseInt(savings)
      return Math.sign(parsedNun) !== 1
    }
  },
  methods: {
    calculateAgain() {
      this.comparator.resetComparatorStateData()
      this.comparator.changeStepComponent('comp-hot-water')
      this.global.changeView('funnel')
      if(document.querySelector('.an-featured')) document.querySelector('.an-featured').classList.add('hide');
    }
  },
  mounted() {
    if(document.querySelector('.an-featured')) document.querySelector('.an-featured').classList.remove('hide');
    
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    }) 
  },
  template: /*html*/`
  <div>
    <div class="an-wrapper an-wrapper--med">

      <template v-if="isSavingsNegative">
        <div class="text-center mb-xxl">
          <h2 class="color-an-theme">¡Enhorabuena! Con tu instalación actual ya tienes el mejor precio</h2>
        </div>
      </template>
      <template v-else>
        <div class="text-center mb-xxl">
          <h2 class="color-an-theme">Instalando gas natural ahorrarías {{ gasConsumption.savings }} cada año.</h2>
          <p class="an-body-l-bold color-an-theme">Consumo anual estimado: {{ gasConsumption.consumptionRequired }}</p>
        </div>
      </template>

      <div class="an-result-lines">
        <div class="an-result-lines__row  an-result-lines__row--big">
          <div class="an-result-lines__left">
            <p class="an-body-l-bold">Total de energía que empleas actualmente</p>
            <div class="an-result-lines__left-line"></div>
          </div>
          <div class="an-result-lines__right">
            <p class="an-body-l-bold">Importe anual</p>
            <h3>{{ gasConsumption.currentCost }}</h3>
          </div>
        </div>

        <div class="an-result-lines__row  an-result-lines__row--small">
          <div class="an-result-lines__left">
            <p class="an-body-l-bold">Lo que gastarías con gas natural</p>
            <div class="an-result-lines__left-line"></div>
          </div>
          <div class="an-result-lines__right">
            <p class="an-body-l-bold">Importe anual</p>
            <h3>{{ gasConsumption.futureCost }}</h3>
          </div>
        </div>
      </div>
      
      <div class="an-funnel__footer justify-content-center">
        <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
          <span>Volver a calcular</span>
        </button>
      </div>

    </div>
  </div>
  `,

}

export default results