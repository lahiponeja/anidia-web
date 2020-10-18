const houseForm = {
  inject: ["global", "house"],
  methods: {
    submitRequest() {
      console.log("Vivienda, enviar data...")
    }
  },
  template: /*html*/
    `<div class="an-form an-wrapper">
        <form @submit.prevent="submitRequest">
          <p class="an-body-l-bold mb-xl">Rellena los datos de tu vivienda para poderte hacer un presupuesto lo más ajustado posible</p>
          <div class="an-form__flex an-form__flex--2-cols mb-xxl">
            <div class="an-input an-form__item">
              <input type="text" class="an-input__field" placeholder="Metros cuadrados" required="">
            </div>
            <div class="an-input an-form__item">
              <input type="text" class="an-input__field" placeholder="Plantas" required="">
            </div>
            <div class="an-input an-form__item">
              <input type="text" class="an-input__field" placeholder="Baños" required="">
            </div>
            <div class="an-input an-form__item">
              <input type="text" class="an-input__field" placeholder="Número de estancias" required="">
            <p class="an-input__caption an-body-s-regular">*(Incluye cocina y salón y excluye baños)</p>
            </div>
          </div>
          
          <p class="an-body-l-bold mb-xl">¿Que necesitas?</p>
          <div class="an-form__flex an-form__flex--4-cols mb-xxl">
            <label for="agua-caliente" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente" name="que-necesitas" value="agua caliente" checked>

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-cocina" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-cocina" name="que-necesitas" value="agua caliente y cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-calefaccion" name="que-necesitas" value="agua caliente, calefaccion">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-calefaccion-cocina" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-calefaccion-cocina" name="que-necesitas" value="agua caliente, calefaccion y cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente + calefacción + cocina</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>
          </div>


          <p class="an-body-l-bold mb-xl">Selecciona todo lo que tienes ahora mismo</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">
            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected=""> Select role...
                </option><option value="ds">UI/UX Designer</option>
                <option value="fe">Frontend Engineer</option>
                <option value="be">Backend Engineer</option>
                <option value="qa">QA Engineer</option>
                <option value="un">Unicorn</option>
              </select>
              <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role...</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div>
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected=""> Select role...
                </option><option value="ds">UI/UX Designer</option>
                <option value="fe">Frontend Engineer</option>
                <option value="be">Backend Engineer</option>
                <option value="qa">QA Engineer</option>
                <option value="un">Unicorn</option>
              </select>
              <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role...</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div>
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected=""> Select role...
                </option><option value="ds">UI/UX Designer</option>
                <option value="fe">Frontend Engineer</option>
                <option value="be">Backend Engineer</option>
                <option value="qa">QA Engineer</option>
                <option value="un">Unicorn</option>
              </select>
              <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role...</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div>
            </div>

            <div class="an-select an-form__item" data-select-container="">
              <span class="an-select__icon an-icon--chevron-down"></span>
              <select class="an-select__native" data-select-native="">
                <option value="sel" disabled="" selected=""> Select role...
                </option><option value="ds">UI/UX Designer</option>
                <option value="fe">Frontend Engineer</option>
                <option value="be">Backend Engineer</option>
                <option value="qa">QA Engineer</option>
                <option value="un">Unicorn</option>
              </select>
              <div class="an-select__custom" data-select-custom="" aria-hidden="true">
                <div class="an-select__custom-trigger" data-select-custom-trigger="">Select role...</div>
                <div class="an-select__custom-options" data-select-custom-options="">
                  <div class="an-select__custom-option" data-value="ds">UI/UX Designer</div>
                  <div class="an-select__custom-option" data-value="fe">Frontend Engineer</div>
                  <div class="an-select__custom-option" data-value="be">Backend Engineer</div>
                  <div class="an-select__custom-option" data-value="qa">QA Engineer</div>
                  <div class="an-select__custom-option" data-value="un">Unicorn</div>
                </div>
              </div>
            </div>
          </div>


          <p class="an-body-l-bold mb-xl">¿Tienes rejilla de ventilación superior?</p>
          <div class="an-form__flex an-form__flex--6-cols an-form__flex--justify-normal mb-xxl">
            <div class="an-radio an-form__item">
              <input class="an-radio__input" checked="" type="radio" name="rejilla-ventilacion-superior" id="vent-si">
              <label class="an-radio__label" for="vent-si">
                <span>
                  Si
                </span>
              </label>
            </div>

            <div class="an-radio an-form__item">
              <input class="an-radio__input" type="radio" name="rejilla-ventilacion-superior" id="vent-no">
              <label class="an-radio__label" for="vent-no">
                <span>
                  No
                </span>
              </label>
            </div>
          </div>


          <p class="an-body-l-bold mb-xl">¿Qué uso haces del agua caliente?</p>
          <div class="an-form__flex an-form__flex--3-cols mb-xxl">
            <label for="agua-caliente-un-sitio" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-un-sitio" name="uso-agua-caliente" value="agua caliente" checked>

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en un sitio para 1 o 2 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-dos-sitios" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-dos-sitios" name="uso-agua-caliente" value="agua caliente y cocina">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en dos sitios a la vez para 2 o 3 personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>

            <label for="agua-caliente-tres-sitios" class="an-form__item">
              <input type="radio" class="an-selection__radio" id="agua-caliente-tres-sitios" name="uso-agua-caliente" value="agua caliente, calefaccion">

              <div class="an-selection">
                <p class="an-menu-bold an-card__text">Agua caliente en tres o más sitios a la vez para 4 o más personas</p>
                <div class="an-selection__icon an-icon--hot-water-two"></div>
              </div>
            </label>
          </div>



          <button type="submit" class="an-btn an-btn--flatter an-btn--green-border an-btn--icon an-icon--check-simple mt-xl">
            <span>Continuar</span>
          </button>
          
        </form>
      </div>
    `
}

export default houseForm;