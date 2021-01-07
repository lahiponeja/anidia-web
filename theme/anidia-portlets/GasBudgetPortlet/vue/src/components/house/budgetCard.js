const budgetCard = {
  inject: ["global", "house"],
  data() {
    return {}
  },
  computed: {
    gasBudget() {
      return this.house.state.gasBudget
    },
    houseFormData() {
      return this.house.state.houseFormData
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
  },
  mounted() {
    window.dataLayer.push(this.house.getDatalayerDetailsStepInfo("FUNNEL - CONTRATACIÓN", "maintenanceupselling", "gas"));
    window.scrollTo({
      top: 200,
      behavior: 'smooth',
    })
  },
  template:/*html*/`
  <div class="an-wrapper an-wrapper--center">
    <div class="an-card an-card--pack an-card--pack--big featured">
      <div class="an-card--pack__intro">
        <h5>{{ gasBudget.proposedPack }}</h5>
        <h5>{{ gasBudget.equipment }}</h5>
      </div>
      <div class="an-card--pack__info">
        <p class="an-h2">{{ gasBudget.totalPrice }}</p>
      </div>
      <ul class="an-list">
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.gasNaturalUse }}
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Tipo de vivienda: {{ house.state.houseType }}
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.propertyMeters }}
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.staysNumber }} estancias
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.bathroomNumber }} baños
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.floorNumber }} plantas
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.personsWater }} personas
        </li>
      </ul>
      <button @click="house.changeHouseStep('presupuesto-realizado')" class="an-btn an-btn--flatter an-btn--white">
        <span>Recibir presupuesto detallado</span>
      </button>
    </div>

    <div class="an-form__flex an-form__flex--6-cols mb-xxl">
      <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
        <span>Volver a calcular</span>
      </button>
    </div>
  </div>`,
}

export default budgetCard;



