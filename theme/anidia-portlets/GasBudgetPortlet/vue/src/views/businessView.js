import businessContactForm from '../components/business/businessContactForm'

const businessView = {
  inject: ["global", "house"],
  data() {
    return {
      formSuccess: false,
    }
  },
  components: {
    businessContactForm,
  },
  methods: {
    setFormSuccess() {
      this.formSuccess = true
    },
  },
  mounted() {
    if(document.querySelector('.an-centered-featured')) document.querySelector('.an-centered-featured').classList.remove('hide');
  },
  template: /*html*/`
    <div class="an-funnel__titles bg-white pb-xxxl pt-xxxl">
    <template v-if="!formSuccess">
        <div class="an-funnel__titles mb-xl">
          <p class="an-h6 color-an-theme-dark-grey mb-l">TE INFORMAMOS</p>
          <p class="an-body-l-bold color-an-theme">Déjanos tus datos y contactamos contigo</p>
        </div>

        <businessContactForm @form-success="setFormSuccess" />
      </template>
      <template v-else>
        <div class="an-wrapper an-wrapper--sml">
          <p class="an-h6 color-an-theme-dark-grey mb-l">SOLICITUD ONLINE REALIZADA CON ÉXITO</p>
          <p class="an-h2 color-an-theme mb-l">Muchas gracias {{ house.state.userFullName }} </p>
          <p class="an-body-l-regular color-an-theme">Nos pondremos en contacto contigo para darte toda la información al detalle</p>
          <div class="an-form__flex an-form__flex--6-cols justify-content-center">
            <button @click="global.changeView('funnel')" type="button" class="an-btn an-btn--green-border an-btn--icon an-icon--half-arrow-left mt-xl">
              <span>Volver a calcular</span>
            </button>
          </div> 
        </div>
      </template>
    </div>
  `
}

export default businessView;
