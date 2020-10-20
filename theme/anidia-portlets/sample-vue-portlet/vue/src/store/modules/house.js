import { http } from '../../services/http/index'
import { reactive, readonly } from 'vue'
import coverageService from '../../services/coverageServices'

const state = reactive({
  houseType: "",
  houseSteps: [
    {
      name: "cobertura",
      icon: "an-icon--lightning-borders",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: true,
    },
    {
      name: "vivienda",
      icon: "an-icon--living-place",
      heading: {
        title: "SOLICITUD ONLINE",
        subtitle: "Cuéntanos cómo es tu hogar o negocio para que podamos redefinir tu energía",
      },
      active: false,
    },
    {
      name: "presupuesto",
      icon: "an-icon--euro-cable",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: false
    },
    {
      name: "presupuesto-realizado",
      heading: {
        title: "PRESUPUESTO REALIZADO",
        subtitle: "Rellena los datos para recibir la oferta detallada, te llamamos o te la mandamos por email",
      },
      active: false
    },
  ],
  currentStep: "cobertura",
  autocompData: {
    postalCodes: [],
    municipalities: [],
    addresses: [],
    estates: [],
    properties: [],
  },
  coverageError: "",
})

const changeStep = function (step) {
  const stepToChange = state.houseSteps.find((homeStep) => homeStep.name === step)
  state.houseSteps.forEach((homeStep) => homeStep.active = false)
  stepToChange.active = true
  state.currentStep = step;
}

const submitCoverageData = function (formData) {
  const { 
    postalCode, 
    municipality,
    street,
    number,
    houseType,
    privacyPolicy 
  } = formData;

  http.post('posts', {
    title: 'foo',
    body: 'bar',
    userId: 1,
  })
  .then((response) => {
    // TODO: Handle all the possible responses (there's at least 3).
    // if response.data === some condition
    //   coverageError = ""
    // else 
      changeStep("vivienda")
      state.houseType = houseType
    // console.log(response.data)
    // state.coverageError = err.message
  })
  .catch((err) => {
    console.error(err)
  })
}

const submitHouseData = function () {}

const submitUserContactInfo = function (budgetReadyForm) {
  const { 
    name,
    lastname,
    phone,
    email,
    privacyPolicy,
    offersAndServices } = budgetReadyForm;

    console.log("budgetReadyForm", budgetReadyForm);
}

const getPostalCodes = function () {
  return new Promise(resolve => {
    coverageService.getPostalCodes().then((data) => {
      resolve(data)
    })
  })
}

export default { 
  state: readonly(state), 
  changeStep,
  submitCoverageData,
  submitHouseData,
  submitUserContactInfo,
  getPostalCodes,
}