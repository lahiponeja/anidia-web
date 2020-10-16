import { reactive, readonly } from 'vue'

const state = reactive({
  homeSteps: [
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
})

const changeStep = function (step) {
  const stepToChange = state.homeSteps.find((homeStep) => homeStep.name === step)
  state.homeSteps.forEach((homeStep) => homeStep.active = false)
  stepToChange.active = true
  state.currentStep = step;
}

export default { 
  state: readonly(state), 
  changeStep,
}