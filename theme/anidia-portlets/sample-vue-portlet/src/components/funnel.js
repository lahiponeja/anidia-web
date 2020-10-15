import Vue from 'vue/dist/vue.common';

Vue.component('funnel', {
  data: function() {
    return { 
      optionsArr: [
        {
          name: 'home',
          title: 'Hogar unifamiliar',
          icon: 'an-icon--unifamiliar-home',
        },
        {
          name: 'apartment',
          title: 'Piso en bloque de viviendas',
          icon: 'an-icon--apartments',
        },
        {
          name: 'business',
          title: 'Negocio',
          icon: 'an-icon--business',
        },
      ],
      optionPicked: null
    }
  },
  inject: ["global"],
  computed: {
    state() {
      return this.global.state
    },
    classOptionPicked(optionName) {
      return {'an-selection--filled': (this.optionPicked === optionName)}
    },
  },
  methods: {
    pickOption(option) {
      this.optionPicked = option
    },

    continueToForm() {
      if(this.optionPicked === 'home' || this.optionPicked === 'apartment') {
        // hide "funnel component" and show "homeFlow component"
      } else if(this.optionPicked === 'business') {
        // hide "funnel component" and show "businessFlow component"
      }
    }
  },
  template: /*html*/
    `<div class="an-funnel bg-white pt-xxxl pb-xxxl">
      <div class="an-funnel__titles an-wrapper--sml">
        <p class="an-h6 color-an-theme-dark-grey mb-l">COMIENZA TU SOLICITUD ONLINE</p>
        <p class="an-h2 color-an-theme">¿Dónde quieres utilizar los servicios de Anidia Gas?</p>
      </div>
      <div class="an-funnel__cards">
        <!-- CARD ITEM -->
        <div v-for="(option, index) in optionsArr" :key="index" class="an-funnel__cards-item">
          <div class="an-selection" :class="{'an-selection--filled': (optionPicked === option.name)}" @click="pickOption(option.name)">
            <p class="an-menu-bold an-card__text">{{ option.title }}</p>
            <div class="an-selection__icon" :class="option.icon"></div>
          </div>
        </div>
        
      </div>
      <div class="an-funnel__footer">
        <button type="button" @click="continueToForm" :class="{ 'an-btn--disabled': !optionPicked }" class="an-btn an-btn--white-border an-btn--icon an-icon--check-simple">
          <span>Continuar</span>
        </button>
      </div>
    </div>`,
  // <p>Cool: {{ state.formMainData.houseType }}</p>
  // mounted() {
  //     console.log(this.global.state.formMainData);
  // } 
})