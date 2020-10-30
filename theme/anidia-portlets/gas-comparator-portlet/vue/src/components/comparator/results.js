import featuredBanner from '../static/featuredBanner'

const results = {
  inject: ["comparator"],
  components: {
    'featured-banner': featuredBanner,
  },
  computed: {
    gasConsumption() {
      return this.comparator.state.gasConsumptionComparison
    }
  },
  mounted() {
    if(document.querySelector('.an-featured')) document.querySelector('.an-featured').classList.remove('hide');
  },
  template: /*html*/`
  <div>
    <div class="an-wrapper an-wrapper--med">
      <div class="text-center mb-xxl">
        <h2 class="color-an-theme">Instalando gas natural ahorrarías {{ gasConsumption.savings }} cada año.</h2>
        <p class="an-body-l-bold color-an-theme">Consumo anual: {{ gasConsumption.consumptionRequired }}</p>
      </div>

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
    </div>

    <featured-banner />

  </div>
  `,

}

export default results
