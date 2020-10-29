import results from "./results";

const compSaving = {
  components: {
    results,
  },
  inject: ["comparator"],
  data() {
    return {
      compSavingForm: {
        name: "",
        lastname: "",
        phone: "",
        email: "",
        privacyPolicy: false,
        offersAndServices: false,
      },

      sendingForm: false,
      leadSent: false,
    }
  },
  methods: {
    submitRequest() {
      // TODO: add validation
      this.sendingForm = true
      this.comparator.submitUserContactInfo(this.compSavingForm).then((res) => {
        this.sendingForm = false
        this.leadSent = true
        console.log(res)
      }).catch((err)=>{
        console.error(err)
        this.sendingForm = false
      })
    }
  },
  template: /*html*/`
  <div>
    <template v-if="!this.leadSent">
      <div class="an-form an-wrapper">
        <form @submit.prevent="submitRequest">
          <div class="an-form__flex an-form__flex--2-cols">
            <div class="an-input an-form__item">
              <input v-model="compSavingForm.name" type="text" class="an-input__field" placeholder="Nombre" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="compSavingForm.lastname" type="text" class="an-input__field" placeholder="Apellidos" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="compSavingForm.phone" type="text" class="an-input__field" placeholder="Teléfono" required="">
            </div>
            <div class="an-input an-form__item">
              <input v-model="compSavingForm.email" type="text" class="an-input__field" placeholder="Email" required="">
            </div>
            <div class="an-input an-form__item">
              <div class="an-checkbox mt-xl">
                <input v-model="compSavingForm.privacyPolicy" class="an-checkbox__input" type="checkbox" name="privacy-policy" id="privacy-policy">
                <label class="an-checkbox__label" for="privacy-policy">
                  <span> Acepto la política de privacidad </span>
                </label>
              </div>
            </div>
            <div class="an-input an-form__item">
              <div class="an-checkbox mt-xl">
                <input v-model="compSavingForm.offersAndServices" class="an-checkbox__input" type="checkbox" name="offers-and-services" id="offers-and-services">
                <label class="an-checkbox__label" for="offers-and-services">
                  <span> Quiero recibir publicidad con nuevas ofertas y servicios </span>
                </label>
              </div>
            </div>
          </div>

          <button type="submit" class="an-btn an-btn--flatter an-btn--gradient an-btn--icon an-icon--check-simple mt-xl">
            <span v-if="!sendingForm">Continuar</span>
            <span v-else>Enviando...</span>
          </button>
          
        </form>
      </div>
    </template>
    <template v-else>
      <results />
    </template>
  </div>
  `,
}

export default compSaving;