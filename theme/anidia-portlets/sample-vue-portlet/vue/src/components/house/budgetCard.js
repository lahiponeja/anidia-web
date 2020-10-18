const budgetCard = {
  inject: ["global", "house"],
  data() {
    return {}
  },
  template:/*html*/`
  <div class="an-wrapper an-wrapper--center">
    <div class="an-card an-card--pack an-card--pack--big featured">
      <div class="an-card--pack__intro">
        <h5>Pack 1</h5>
        <h5>Mantenimiento</h5>
      </div>
      <div class="an-card--pack__info">
        <h4>Desde</h4>
        <h1>380€</h1>
        <h4>567€ de ahorro anual</h4>
      </div>
      <ul class="an-list">
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Agua caliente y calefacción
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Zona Norte de España
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Casas unifamiliares de 80 m2, 3 estancias y 4 personas
        </li>
        <li class="an-list__item an-body-m-regular">
          <div class="an-list__icon an-icon--check-circle"></div>
          Ahorro medio de 700€/año
        </li>
      </ul>
      <button class="an-btn an-btn--flatter an-btn--white">
        <span>Lorem ipsum dolor</span>
      </button>
    </div>
  </div>`,
}

export default budgetCard;



