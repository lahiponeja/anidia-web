const budgetCard = {
  inject: ["global", "house"],
  data() {
    return {};
  },
  computed: {
    houseFormData() {
      return this.house.state.houseFormData;
    },
    gasBudgets() {
      return this.house.state.gasBudgetArray.GasBudgetResult.GasBudgets.GasBudgets;
    },
    principalBudget () {
      return this.house.state.gasBudgetArray.GasBudgetResult.PrincipalBudget;
    }
  },
  methods: {
    calculateAgain() {
      const confirmation = confirm(
        "¿Estás seguro de que quieres volver a calcular?"
      );
      if (confirmation) {
        this.global.changeView("funnel");
        this.house.changeHouseStep("cobertura");
      }
    },

    setBudget (singleGasBudget) {
      this.house.setGasBudget(singleGasBudget)
      this.house.changeHouseStep('presupuesto-realizado')
    }
  },
  mounted() {
    window.dataLayer.push(
      this.house.getDatalayerDetailsStepInfo(
        "FUNNEL - CONTRATACIÓN",
        "maintenanceupselling",
        "gas"
      )
    );
    window.scrollTo({
      top: 200,
      behavior: "smooth",
    });
    
    const dots = document.querySelectorAll('.an-pack-cards__dot');
    const cardContainer = document.querySelector('.an-cards');
    const nCards = document.querySelectorAll('.an-card--pack').length;

    dots.forEach((dot, i) => {
      dot.addEventListener('click', e => {
        e.preventDefault();
        e.stopPropagation();
        setAriaPressed(i);
        const scrollLeft = Math.floor(cardContainer.scrollWidth * (i / nCards));
        smoothScroll(cardContainer, scrollLeft);
      });
    });
  
    cardContainer.addEventListener('scroll', () => {
      let index = Math.round((cardContainer.scrollLeft / cardContainer.scrollWidth) * nCards);
      setAriaPressed(index);
    }, 200);
  
    setAriaLabels();
  
    function smoothScroll (node, topOrLeft) {
      return node.scrollTo({
        ['left']: topOrLeft,
        behavior: 'smooth'
      });
    }
  
    function setAriaLabels() {
      dots.forEach((dot, i) => {
        dot.setAttribute('aria-label', `Scroll to item #${i + 1}`);
      });
    }
  
    function setAriaPressed(index) {
      dots.forEach((dot, i) => {
        dot.setAttribute('aria-pressed', !!(i === index));
      });
    }
  },
  template: /*html*/ `
  <div class="an-wrapper an-wrapper--center">
    <div class="an-cards">

      <div v-if="principalBudget" class="an-card an-card--pack an-card--pack--big featured">
        <div class="an-card--pack__intro">
          <h5>{{ principalBudget.equipment }}</h5>
        </div>
        <div class="an-card--pack__info">
          <p class="an-h2">{{ principalBudget.totalPrice }}</p>
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
        <button @click="setBudget(principalBudget)" class="an-btn an-btn--flatter an-btn--white">
          <span>Recibir presupuesto detallado</span>
        </button>
      </div>

    <div v-for="(singleGasBudget, index) in gasBudgets"  class="an-card an-card--pack an-card--pack--big">
      <div class="an-card--pack__intro">
        <h5>{{ singleGasBudget.equipment }}</h5>
      </div>
      <div class="an-card--pack__info">
        <p class="an-h2">{{ singleGasBudget.totalPrice }}</p>
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
    <button @click="setBudget(singleGasBudget)" class="an-btn an-btn--flatter an-btn--white">
      <span>Recibir presupuesto detallado</span>
    </button>
  </div>
    </div>
    <ul v-if="gasBudgets.length > 0" class="an-pack-cards__dots">
    <li class="an-pack-cards__dot" aria-pressed="true"></li>
    <li v-for="singleGasBudget in gasBudgets" class="an-pack-cards__dot"></li>
  </ul>
    <div class="an-form__flex an-form__flex--6-cols mb-xxl">
      <button @click="calculateAgain" type="button" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--half-arrow-left mt-xl">
        <span>Volver a calcular</span>
      </button>
    </div>
  </div>`,
};

export default budgetCard;
