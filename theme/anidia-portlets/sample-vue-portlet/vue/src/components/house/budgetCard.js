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
  template:/*html*/`
  <div class="an-wrapper an-wrapper--center">
    <div class="an-card an-card--pack an-card--pack--big featured">
      <div class="an-card--pack__intro">
        <h5>{{ gasBudget.proposedPack }}</h5>
        <h5>{{ gasBudget.equipment }}</h5>
      </div>
      <div class="an-card--pack__info">
        <!-- <h4>Desde</h4> -->
        <p class="an-h2">{{ gasBudget.totalPrice }}</p>
        <!-- <h4>567€ de ahorro anual</h4> -->
      </div>
      <ul class="an-list">
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          {{ houseFormData.gasNaturalUse }}
        </li>
        <!-- <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Zona Norte de España
        </li> -->
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
        <!-- <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Ahorro medio de 700€/año
        </li> -->
      </ul>
      <button @click="house.changeStep('presupuesto-realizado')" class="an-btn an-btn--flatter an-btn--white">
        <span>Recibir presupuesto detallado</span>
      </button>
    </div>
  </div>`,
}

export default budgetCard;



