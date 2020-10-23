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
      this.global.changeStep(this.optionPicked)
      this.house.setHouseType(this.optionPicked)
    },
  },
  template: /*html*/
    `<div>
    <div v-if="!postalCodesLoaded" style="z-index: 20; position: absolute; left: 0; top: 0; background-color: rgba(255,255,255, 0.6); width: 100%; height: 100%;"></div>
    
    <div class="an-funnel bg-white pt-xxxl pb-xxxl">
      <div class="an-funnel__titles an-wrapper--sml">
        <p class="an-h6 color-an-theme-dark-grey mb-l">COMIENZA TU SOLICITUD ONLINE</p>
        <p class="an-h2 color-an-theme">¿Dónde quieres utilizar los servicios de Anidia Gas?</p>
      </div>
      <div class="an-funnel__cards">
        <!-- CARD ITEM -->
        <div v-for="(option, index) in global.state.optionsArr" :key="index" class="an-funnel__cards-item">
          <div @click="pickOption(option.name)" class="an-selection" :class="{'an-selection--filled': (optionPicked === option.name)}">
            <p class="an-menu-bold an-card__text">{{ option.title }}</p>
            <div class="an-selection__icon" :class="option.icon"></div>
          </div>
        </div>
        
      </div>
      <div class="an-funnel__footer">
        <button type="button" @click="continueStep" :disabled="!optionPicked" :class="{ 'an-btn--disabled': !optionPicked  }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple">
          <span>Continuar</span>
        </button>
      </div>
    </div>
    </div>`,
}

export default funnelView;