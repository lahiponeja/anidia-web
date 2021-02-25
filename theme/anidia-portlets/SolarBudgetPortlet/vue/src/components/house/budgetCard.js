const budgetCard = {
  inject: ["global", "house", "lead"],
  data() {
    return {
      accordeaonIsOpen: false, // change to false
      accordeaonSupIsOpen: false,
      extras: {
        panelsExtra: 0,
        pipelineExtra: 0,
        triphasicExtra: false,
        roofExtra: false,
        pergolaExtra: false,
        inverterExtra: false
      },
      superiorExtras: {
        panelsExtra: 0,
        pipelineExtra: 0,
        triphasicExtra: false,
        roofExtra: false,
        pergolaExtra: false,
        inverterExtra: false
      }
    }
  },
  computed: {
    solarBudget() {
      return this.lead.state.solarBudget
    },
    leadData() {
      return this.lead.state.lead
    },

    /************************************ 
     * EXTRAS | NOT SUPERIOR
     *************************************/ 
    panelsExtraTotalPrice() {
      return this.solarBudget.panelsExtra * this.extras.panelsExtra
    },
    pipelineExtraTotalPrice() {
      return this.solarBudget.pipelineExtra * this.extras.pipelineExtra
    },
    triphasicExtraTotalPrice() {
      if(this.extras.triphasicExtra) {
        return Number(this.solarBudget.triphasicExtra)
      }

      return 0
    },
    roofExtraTotalPrice() {
      if(this.extras.roofExtra) {
        return this.solarBudget.roofExtra * ( Number(this.solarBudget.size.basePanels) + this.extras.panelsExtra )
      }
      
      return 0
    },
    pergolaExtraTotalPrice() {
      if(this.extras.pergolaExtra) {
        return this.solarBudget.pergolaExtra * ( Number(this.solarBudget.size.basePanels) + this.extras.panelsExtra )
      }
      
      return 0
    },
    inverterExtraTotalPrice() {
      if(this.extras.inverterExtra) {
        return Number(this.solarBudget.inverterExtra)
      }

      return 0
    },
    


    // EXTRAS SUM
    allExtrasSum() {
      return (
        this.panelsExtraTotalPrice + 
        this.pipelineExtraTotalPrice +
        this.triphasicExtraTotalPrice +
        this.roofExtraTotalPrice +
        this.pergolaExtraTotalPrice +
        this.inverterExtraTotalPrice
        )
    },

    finalPrice() {
      const sum = Number(this.solarBudget.size.price.replace('.', '')) + this.allExtrasSum
      return sum.toFixed(2)
    }, 

    ivaRate() {
      return 0.21;
    },

    finalPriceIvaExtra() {
      return  this.finalPrice*this.ivaRate;
    },

    finalPriceWithIva() {
      return  this.finalPrice*(1+this.ivaRate);
    },

    /************************************ 
   * EXTRAS | SUPERIOR
   *************************************/ 
    panelsExtraSuperiorTotalPrice() {
      return this.solarBudget.superiorInstallation.panelsExtra * this.superiorExtras.panelsExtra
    },
    pipelineExtraSuperiorTotalPrice() {
      return this.solarBudget.superiorInstallation.pipelineExtra * this.superiorExtras.pipelineExtra
    },
    triphasicExtraSuperiorTotalPrice() {
      if(this.superiorExtras.triphasicExtra) {
        return Number(this.solarBudget.superiorInstallation.triphasicExtra)
      }

      return 0
    },
    roofExtraSuperiorTotalPrice() {
      if(this.superiorExtras.roofExtra) {
        return this.solarBudget.superiorInstallation.roofExtra * ( Number(this.solarBudget.superiorInstallation.superiorSize.basePanels) + this.superiorExtras.panelsExtra )
      }
      
      return 0
    },
    pergolaExtraSuperiorTotalPrice() {
      if(this.superiorExtras.pergolaExtra) {
        return this.solarBudget.superiorInstallation.pergolaExtra * ( Number(this.solarBudget.superiorInstallation.superiorSize.basePanels) + this.superiorExtras.panelsExtra )
      }
      
      return 0
    },
    inverterExtraSuperiorTotalPrice() {
      if(this.superiorExtras.inverterExtra) {
        return Number(this.solarBudget.superiorInstallation.inverterExtra)
      }

      return 0
    },

    allExtraSuperiorsSum() {
      return (
        this.panelsExtraSuperiorTotalPrice + 
        this.pipelineExtraSuperiorTotalPrice +
        this.triphasicExtraSuperiorTotalPrice +
        this.roofExtraSuperiorTotalPrice +
        this.pergolaExtraSuperiorTotalPrice +
        this.inverterExtraSuperiorTotalPrice
      )
    },

    finalPriceSuperior() {
      const sum = Number(this.solarBudget.superiorInstallation.superiorSize.price.replace('.', '')) + this.allExtraSuperiorsSum
      return !Number.isNaN(sum) ? sum.toFixed(2) : false
    },

    finalPriceSuperiorIvaExtra() {
      return  this.finalPriceSuperior*this.ivaRate;
    },

    finalPriceSuperiorWithIva() {
      return  this.finalPriceSuperior*(1+this.ivaRate);
    }


  },
  methods: {
    calculateAgain() {
      const confirmation = confirm('¿Estás seguro de que quieres volver a calcular?')
      if(confirmation) {
        this.global.changeView('funnel')
        this.house.changeHouseStep('cobertura')
      }
    },
    toggleAccordeon(card) {
      if(card === 'sup') {
        this.accordeaonSupIsOpen = !this.accordeaonSupIsOpen
      } else {
        this.accordeaonIsOpen = !this.accordeaonIsOpen
      }
    },
    selectBudget(type) {
      if(type === 'sup') {
        this.lead.setSuperiorInstalation(true)
        this.lead.setSelectedExtras(this.superiorExtras)
        this.lead.setFinalPrice(this.finalPriceSuperior)
      } else {
        this.lead.setSuperiorInstalation(false)
        this.lead.setSelectedExtras(this.extras)
        this.lead.setFinalPrice(this.finalPrice)
      }

      this.house.changeHouseStep('presupuesto-realizado')

    },
    formatPrice(price) {
      return new Intl.NumberFormat('es-ES', { maximumFractionDigits: 2, minimumFractionDigits: 2 }).format(price);
    }
  },
  mounted() {
    // window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "maintenanceupselling", "gas"));
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template:/*html*/`
  <div class="an-wrapper an-wrapper--center">
    <div class="an-cards-vue" data-container="">
      <!-- first card -->
      <div class="an-card an-card--pack an-card--vue featured" data-card="">
        <div class="an-card--pack__intro">
          <p class="an-h5">Según tus necesidades especiales</p>
          <p class="an-h5">Pago único {{formatPrice(finalPrice) }}€</p>
        </div>
        <div class="an-card--pack__info">
          <!-- <p class="an-h4">Desde</p> -->
          <p class="an-h2">{{ formatPrice(finalPrice/12) }} <span class="an-h3">€ mes</span></p>
          <p class="an-h4">Genera ahorros de hasta el 60% en tu factura de la luz</p>
          <p class="an-h5">Precio total IVA Incluido: {{ formatPrice(finalPriceWithIva) }}€</p>
        </div>
        <ul class="an-list">
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ leadData.calculatorSolar.input.houseType }}
          </li>
          <li v-if="leadData.calculatorSolar.input.monthlyConsumption" class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Consumo de energía mensual {{ leadData.calculatorSolar.input.monthlyConsumption }}€
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Instalación de {{ solarBudget.size.basePanels }} paneles solares
          </li>
        </ul>
        <div class="an-accordion">
          <button type="button" @click="toggleAccordeon" class="an-accordion__item t-uppercase an-h6 mb-m" :class="{ 'closed': !accordeaonIsOpen }" data-accordion="" aria-controls="accordion_item_1" aria-expanded="false">
              Añádele extras
            <span class="an-accordion__item-icon an-icon--arrow-down-svg" aria-hidden="true"></span>
          </button>
          <div class="an-accordion__content" :class="{ 'closed': !accordeaonIsOpen }" id="accordion_item_1">
            <ul class="an-card__extra-list">
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                    <span class="an-body-m-regular an-tooltip">
                      Paneles solares extra
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Paneles solares extra</p>
                        <p class="an-tooltip__text">Incrementa la potencia de la talla seleccionada para cubrir todas las necesidades de tu hogar presentes y futuras.</p>
                      </div>
                    </span>
                  </label>
                </div>
                <div class="an-input-number an-input-number--white" data-inumber="">
                  <button @click="extras.panelsExtra--" type="button" data-inumber-decrement="" aria-label="Decrement" class="an-input-number__button" :class="{ 'disabled': !extras.panelsExtra }">-</button>
                  <input type="number" min="0" v-model="extras.panelsExtra" class="an-input-number__input">
                  <button @click="++extras.panelsExtra" type="button" data-inumber-increment="" aria-label="Increment" class="an-input-number__button">+</button>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                  <input class="an-checkbox__input" type="checkbox" v-model="extras.triphasicExtra" id="check2">
                  <label class="an-checkbox__label" for="check2">
                    <span class="an-body-m-regular an-tooltip">
                      Extra trifásica
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra trifásica</p>
                        <p class="an-tooltip__text">Incluye el inversor adaptado a tu potencia, protecciones y cableado necesario para la misma.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                  <input class="an-checkbox__input" type="checkbox" v-model="extras.roofExtra" id="check3">
                  <label class="an-checkbox__label" for="check3">
                    <span class="an-body-m-regular an-tooltip">
                      Extra Cubierta excepcional
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra Cubierta excepcional</p>
                        <p class="an-tooltip__text">Ante instalaciones en cubiertas que superen los 20º de inclinación, teja de barro o cerámica.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                  <input class="an-checkbox__input" type="checkbox" v-model="extras.pergolaExtra" id="check4">
                  <label class="an-checkbox__label" for="check4">
                    <span class="an-body-m-regular an-tooltip">
                      Pérgola
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Pérgola</p>
                        <p class="an-tooltip__text">Instalación de una pérgola bajo petición del cliente y con la valoración del incremento de coste correspondiente.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                    <span class="an-body-m-regular an-tooltip">
                      Canalización soterrada
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Canalización soterrada</p>
                        <p class="an-tooltip__text">Para la realización de una instalación soterrada bajo la petición del cliente.</p>
                      </div>
                    </span>
                  </label>
                </div>
                <div class="an-input-number an-input-number--white" data-inumber="">
                  <button @click="extras.pipelineExtra--" type="button" data-inumber-decrement="" aria-label="Decrement" class="an-input-number__button" :class="{ 'disabled': !extras.pipelineExtra }">-</button>
                  <input type="number" min="0" v-model="extras.pipelineExtra" class="an-input-number__input">
                  <button @click="++extras.pipelineExtra" type="button" data-inumber-increment="" aria-label="Increment" class="an-input-number__button">+</button>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                  <input class="an-checkbox__input" type="checkbox" v-model="extras.inverterExtra" id="check6">
                  <label class="an-checkbox__label" for="check6">
                    <span class="an-body-m-regular an-tooltip">
                      Extra Inversor
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra Inversor</p>
                        <p class="an-tooltip__text">Incrementa la potencia del inversor dejándolo sobredimensionado. Esto permitirá que en un futuro puedas incrementar la potencia instalada sin tener que modificarlo.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <button @click="selectBudget" type="button" class="an-btn an-btn--flatter an-btn--white">
          <span>Recibir presupuesto detallado</span>
        </button>
      </div>

      <!-- second card -->

      <div v-if="finalPriceSuperior" class="an-card an-card--pack an-card--vue" data-card="">
        <div class="an-card--pack__intro">
          <p class="an-h5">Según tus necesidades especiales</p>
          <p class="an-h5">Pago único {{ formatPrice(finalPriceSuperior) }}€</p>
        </div>
        <div class="an-card--pack__info">
          <!-- <p class="an-h4">Desde</p> -->
          <p class="an-h2">{{ formatPrice(finalPriceSuperior/12) }} <span class="an-h3">€/mes</span></p>
          <p class="an-h4">Genera ahorros de hasta el 60% en tu factura de la luz</p>
          <p class="an-h5">Precio total IVA Incluido: {{ formatPrice(finalPriceSuperiorWithIva) }}€</p>
        </div>
        <ul class="an-list">
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ leadData.calculatorSolar.input.houseType }}
          </li>
          <li v-if="leadData.calculatorSolar.input.monthlyConsumption" class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Consumo de energía mensual {{ leadData.calculatorSolar.input.monthlyConsumption }}€
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Instalación de {{ solarBudget.superiorInstallation.superiorSize.basePanels }} paneles solares
          </li>
        </ul>
        <div class="an-accordion">
          <button type="button" @click="toggleAccordeon('sup')" class="an-accordion__item t-uppercase an-h6 mb-m" :class="{ 'closed': !accordeaonSupIsOpen }" data-accordion="" aria-controls="accordion_item_1" aria-expanded="false">
              Añádele extras
            <span class="an-accordion__item-icon an-icon--arrow-down-green-svg" aria-hidden="true"></span>
          </button>
          <div class="an-accordion__content" :class="{ 'closed': !accordeaonSupIsOpen }" id="accordion_item_1">
            <ul class="an-card__extra-list">
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Paneles solares extra
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Paneles solares extra</p>
                        <p class="an-tooltip__text">Incrementa la potencia de la talla seleccionada para cubrir todas las necesidades de tu hogar presentes y futuras.</p>
                      </div>
                    </span>
                  </label>
                </div>
                <div class="an-input-number" data-inumber="">
                  <button @click="superiorExtras.panelsExtra--" type="button" data-inumber-decrement="" aria-label="Decrement" class="an-input-number__button" :class="{ 'disabled': !superiorExtras.panelsExtra }">-</button>
                  <input type="number" min="0" v-model="superiorExtras.panelsExtra" class="an-input-number__input">
                  <button @click="++superiorExtras.panelsExtra" type="button" data-inumber-increment="" aria-label="Increment" class="an-input-number__button">+</button>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                  <input class="an-checkbox__input" type="checkbox" v-model="superiorExtras.triphasicExtra" id="check2Superior">
                  <label class="an-checkbox__label" for="check2Superior">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Extra trifásica
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra trifásica</p>
                        <p class="an-tooltip__text">Incluye el inversor adaptado a tu potencia, protecciones y cableado necesario para la misma.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                  <input class="an-checkbox__input" type="checkbox" v-model="superiorExtras.roofExtra" id="check3Superior">
                  <label class="an-checkbox__label" for="check3Superior">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Extra Cubierta excepcional
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra Cubierta excepcional</p>
                        <p class="an-tooltip__text">Ante instalaciones en cubiertas que superen los 20º de inclinación, teja de barro o cerámica.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                  <input class="an-checkbox__input" type="checkbox" v-model="superiorExtras.pergolaExtra" id="check4Superior">
                  <label class="an-checkbox__label" for="check4Superior">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Pérgola
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Pérgola</p>
                        <p class="an-tooltip__text">Instalación de una pérgola bajo petición del cliente y con la valoración del incremento de coste correspondiente.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Canalización soterrada
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Canalización soterrada</p>
                        <p class="an-tooltip__text">Para la realización de una instalación soterrada bajo la petición del cliente.</p>
                      </div>
                    </span>
                  </label>
                </div>
                <div class="an-input-number" data-inumber="">
                  <button @click="superiorExtras.pipelineExtra--" type="button" data-inumber-decrement="" aria-label="Decrement" class="an-input-number__button" :class="{ 'disabled': !superiorExtras.pipelineExtra }">-</button>
                  <input type="number" min="0" v-model="superiorExtras.pipelineExtra" class="an-input-number__input">
                  <button @click="++superiorExtras.pipelineExtra" type="button" data-inumber-increment="" aria-label="Increment" class="an-input-number__button">+</button>
                </div>
              </li>
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                  <input class="an-checkbox__input" type="checkbox" v-model="superiorExtras.inverterExtra" id="check6Superior">
                  <label class="an-checkbox__label" for="check6Superior">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Extra Inversor
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Extra Inversor</p>
                        <p class="an-tooltip__text">Incrementa la potencia del inversor dejándolo sobredimensionado. Esto permitirá que en un futuro puedas incrementar la potencia instalada sin tener que modificarlo.</p>
                      </div>
                    </span>
                  </label>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <button @click="selectBudget('sup')" type="button" class="an-btn an-btn--flatter an-btn--white">
          <span>Recibir presupuesto detallado</span>
        </button>
      </div>
    </div>

    <div class="an-form__flex an-form__flex--6-cols mb-xxl">
      <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
        <span>Volver a calcular</span>
      </button>
    </div>
  </div>`,
}

export default budgetCard;



