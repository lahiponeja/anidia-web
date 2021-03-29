const budgetCard = {
  inject: ["global", "house", "lead"],
  data() {
    return {
      accordeaonIsOpen: false, // change to false
      accordeaonSupIsOpen: false,
      extras: {
        panelsExtra: 0,
        pipelineExtra: 0,
        superiorInverterExtra: false,
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
        inverterExtra: false,
        superiorInverterExtra: false
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

    panelsTypeDescription() {
      if(this.leadData.calculatorSolar.input.panelsType == "Standard") {
        return "Paneles recomendados por Anidia";
      } else if (this.leadData.calculatorSolar.input.panelsType == "Diseño(LG)") {
        return "Paneles de Diseño LG";
      }
    },

    /************************************
     * EXTRAS | NOT SUPERIOR
     *************************************/

    monthlyRate() {
      return 0.011077;
    },

    hasSuperiorInstallation() {
      const sum = Number(this.solarBudget.superiorInstallation.superiorSize.price)
      return !Number.isNaN(sum)
    }

  },
  methods: {

    baseBudget({superior}){
      return superior ? this.solarBudget.superiorInstallation : this.solarBudget;
    },

    baseSize({superior}){
      return superior ? this.baseBudget({superior: superior}).superiorSize : this.baseBudget({superior: superior}).size;
    },

    selectedExtras({superior}) {
      return superior ? this.superiorExtras : this.extras;
    },

    panelsExtraTotalPrice({superior, withTax}) {
      const basePanelsPrice = withTax ? this.baseBudget({superior: superior}).panelsExtra.priceWithTax : this.baseBudget({superior: superior}).panelsExtra.price;
      return basePanelsPrice * this.selectedExtras({superior: superior}).panelsExtra
    },
    pipelineExtraTotalPrice({superior, withTax}) {
      const basePipelinePrice = withTax ? this.baseBudget({superior: superior}).pipelineExtra.priceWithTax : this.baseBudget({superior: superior}).pipelineExtra.price;
      return basePipelinePrice * this.selectedExtras({superior: superior}).pipelineExtra
    },
    triphasicExtraTotalPrice({superior, withTax}) {
      if(this.selectedExtras({superior: superior}).triphasicExtra) {
        return withTax ? Number(this.baseBudget({superior: superior}).triphasicExtra.priceWithTax) : Number(this.baseBudget({superior: superior}).triphasicExtra.price)
      }
      return 0
    },
    roofExtraTotalPrice({superior, withTax}) {
      if(this.selectedExtras({superior: superior}).roofExtra) {
        const baseRoofPrice = withTax ? this.baseBudget({superior: superior}).roofExtra.priceWithTax : this.baseBudget({superior: superior}).roofExtra.price;
        return baseRoofPrice * ( Number(this.baseSize({superior: superior}).basePanels) + this.selectedExtras({superior: superior}).panelsExtra )
      }

      return 0
    },
    pergolaExtraTotalPrice({superior, withTax}) {
      if(this.selectedExtras({superior: superior}).pergolaExtra) {
        const basePergolaPrice = withTax ? this.baseBudget({superior: superior}).pergolaExtra.priceWithTax : this.baseBudget({superior: superior}).pergolaExtra.price;
        return basePergolaPrice * ( Number(this.baseSize({superior: superior}).basePanels) + this.selectedExtras({superior: superior}).panelsExtra )
      }

      return 0
    },
    inverterExtraTotalPrice({superior, withTax}) {
      if(this.selectedExtras({superior: superior}).inverterExtra) {
        return withTax ? Number(this.baseBudget({superior: superior}).inverterExtra.priceWithTax) : Number(this.baseBudget({superior: superior}).inverterExtra.price)
      }

      return 0
    },

    superiorInverterExtraTotalPrice({superior, withTax}) {
      if(this.selectedExtras({superior: superior}).superiorInverterExtra) {
        return withTax ? Number(this.baseBudget({superior: superior}).superiorInverterExtra.priceWithTax) : Number(this.baseBudget({superior: superior}).superiorInverterExtra.price)
      }

      return 0
    },


    // EXTRAS SUM
    allExtrasSum({superior, withTax}) {
      return (
        this.panelsExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.pipelineExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.triphasicExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.roofExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.pergolaExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.inverterExtraTotalPrice({superior: superior, withTax: withTax}) +
        this.superiorInverterExtraTotalPrice({superior: superior, withTax: withTax})
        )
    },

    finalPrice({superior, withTax}) {
      const baseSize = this.baseSize({superior: superior});
      const basePrice = withTax ? Number(baseSize.priceWithTax) : Number(baseSize.price);
      const sum = basePrice + this.allExtrasSum({superior: superior, withTax: withTax})
      return sum.toFixed(2)
    },

    finalMonthlyPrice({superior, withTax}) {
      return Math.floor(this.finalPrice({superior: superior, withTax: withTax}) * this.monthlyRate);
    },

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
        this.lead.setFinalPrice(this.finalPrice({superior: true, withTax: false}), this.finalPrice({superior: true, withTax: true}))
      } else {
        this.lead.setSuperiorInstalation(false)
        this.lead.setSelectedExtras(this.extras)
        this.lead.setFinalPrice(this.finalPrice({superior: false, withTax: false}), this.finalPrice({superior: false, withTax: true}))
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
          <p class="an-h5">Pago único {{formatPrice(finalPrice({superior: false, withTax: true})) }}€</p>
        </div>
        <div class="an-card--pack__info">
          <p class="an-h4">Desde</p>
          <p class="an-h2">{{ formatPrice(finalMonthlyPrice({superior: false, withTax: true})) }} <span class="an-h3">€/mes*</span></p>
          <p class="an-h4">Genera ahorros de hasta el 60% en tu factura de la luz</p>
        </div>
        <ul class="an-list">
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ leadData.calculatorSolar.input.houseType }}
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ panelsTypeDescription }}
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Consumo de energía mensual {{ solarBudget.totalPowerInstalled }} KwP
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
                      Paneles solares extra (paneles)
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
                      Canalización soterrada (metros)
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
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox an-checkbox--white">
                  <input class="an-checkbox__input" type="checkbox" v-model="extras.superiorInverterExtra" id="check6SuperiorInverterExtra">
                  <label class="an-checkbox__label" for="check6SuperiorInverterExtra">
                    <span class="an-body-m-regular an-tooltip">
                      Inversor de tipo superior
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Inversor de tipo superior</p>
                        <p class="an-tooltip__text">Instalación de un inversor Fronius con mejores características que el inversor standard.</p>
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

      <div v-if="hasSuperiorInstallation" class="an-card an-card--pack an-card--vue" data-card="">
        <div class="an-card--pack__intro">
          <p class="an-h5">Según tus necesidades especiales</p>
          <p class="an-h5">Pago único {{ formatPrice(finalPrice({superior: true, withTax: true})) }}€</p>
        </div>
        <div class="an-card--pack__info">
          <p class="an-h4">Desde</p>
          <p class="an-h2">{{ formatPrice(finalMonthlyPrice({superior: true, withTax: true})) }} <span class="an-h3">€/mes*</span></p>
          <p class="an-h4">Genera ahorros de hasta el 60% en tu factura de la luz</p>
        </div>
        <ul class="an-list">
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ leadData.calculatorSolar.input.houseType }}
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            {{ panelsTypeDescription }}
          </li>
          <li class="an-list__item an-body-m-regular">
            <div class="an-list__icon an-icon--check-circle">&nbsp;</div>
            Consumo de energía mensual {{ solarBudget.superiorInstallation.totalPowerInstalled }} KwP
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
                      Canalización soterrada (metros)
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
              <li class="an-card__extra-list__item mb-s">
                <div class="an-checkbox">
                  <input class="an-checkbox__input" type="checkbox" v-model="superiorExtras.superiorInverterExtra" id="check6SuperiorSuperiorInverterExtra">
                  <label class="an-checkbox__label" for="check6SuperiorSuperiorInverterExtra">
                    <span class="an-body-m-regular an-tooltip an-tooltip--green">
                      Inversor de tipo superior
                      <div class="an-tooltip__content an-tooltip__content--slide">
                        <p class="an-tooltip__title an-body-xs-bold mb-xs"><span class="an-icon--info an-tooltip__icon"></span>Inversor de tipo superior</p>
                        <p class="an-tooltip__text">Instalación de un inversor Fronius con mejores características que el inversor standard.</p>
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
    <p class="an-body-s-bold color-an-theme-dark-grey mt-xl">
    * Cuota aportada, a modo de ejemplo, calculada para un préstamo a 120 meses (10 años) con un TIN de 5,95% y TAE de 6,12%. Sujeto al tipo de interés actual ofrecido por las entidades financiera y sujeto a la aceptación de concesión del préstamo por parte de las entidades financieras.
    </p>
    <div class="an-form__flex an-form__flex--6-cols mb-xxl">
      <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
        <span>Volver a calcular</span>
      </button>
    </div>
  </div>`,
}

export default budgetCard;
