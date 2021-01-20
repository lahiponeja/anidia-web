const funnelView = {
  data() {
    return {
      optionPicked: null,
      postalCodes: [],
    }
  },
  inject: ["global", "house"],
  computed: {
    classOptionPicked(optionName) {
      return {'an-selection--filled': (this.optionPicked === optionName)}
    },
    postalCodesLoaded() {
      return this.house.state.autocompData.postalCodes.length > 0
    },
  },
  methods: {
    pickOption(option) {
      this.optionPicked = option
    },
    continueStep() {
      this.global.changeView(this.optionPicked)
      this.house.setHouseType(this.optionPicked)
      window.dataLayer.push(this.house.getDatalayerFirstStepInfo("FUNNEL - CONTRATACIÓN", "instalationtype OK", "gas"));
    },
  },
  mounted() {
    window.dataLayer.push(this.house.getDatalayerInitialInfo("FUNNEL - CONTRATACIÓN", "instalationtype", "gas"));
    if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.add('hide');
  },
  template: /*html*/
    `<div>
      <div v-if="!postalCodesLoaded" class="an-funnel__white-overlay">
        <p class="an-h3">Cargando...</p>
      </div>

      <div class="an-funnel bg-white pt-xxxl pb-xxxl">
        <div class="an-funnel__titles an-wrapper--sml">
          <p class="an-h6 color-an-theme-dark-grey mb-l">COMIENZA TU CALCULO ONLINE</p>
          <p class="an-h2 color-an-theme">¿Dónde quieres utilizar los servicios de Anidia Solar?</p>
        </div>
        <div class="an-funnel__cards">
          <!-- CARD ITEM -->
          <div v-for="(option, index) in global.state.mainViewsArr" :key="index" class="an-funnel__cards-item">
            <template v-if="option.name !== 'funnel'">          
              <div @click="pickOption(option.name)" class="an-selection" :class="{'an-selection--filled': (optionPicked === option.name)}">
                <p class="an-menu-bold an-card__text">{{ option.title }}</p>
                <div class="an-selection__icon" :class="option.icon"></div>
              </div>
            </template>
          </div>
        </div>
        <h3>Funnel Option picked: {{ optionPicked }}</h3>
        <div class="an-funnel__footer">
          <button type="button" @click="continueStep" :disabled="!optionPicked" :class="{ 'an-btn--disabled': !optionPicked  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple">
            <span>Continuar</span>
          </button>
        </div>
      </div>
    </div>`,
}

export default funnelView;
