import businessContactForm from '../components/business/businessContactForm'

const businessView = {
  components: {
    businessContactForm,
  },
  inject: ["global"], // TODO: refactor once I tackle this business form.
  template: /*html*/`
    <div class="an-wrapper">
      <div class="an-funnel__titles mb-xl">
        <p class="an-h6 color-an-theme-dark-grey mb-l">TE INFORMAMOS</p>
        <p class="an-body-l-bold color-an-theme">Déjanos tus datos y contactamos contigo</p>
      </div>
      <businessContactForm />
    </div>
  `
}

export default businessView;