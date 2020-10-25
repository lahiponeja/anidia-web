// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
  optionsArr: [
    {
      name: 'Unifamiliar',
      title: 'Hogar unifamiliar',
      icon: 'an-icon--unifamiliar-home',
    },
    {
      name: 'Bloque de pisos',
      title: 'Piso en bloque de viviendas',
      icon: 'an-icon--apartments',
    },
    {
      name: 'Negocio',
      title: 'Negocio',
      icon: 'an-icon--business',
    },
  ],
  currentStep: "funnel",
})

/*
Possible steps:
  'funnel': <funnel-view />
  'Unifamiliar': <house-view />
  'Bloque de pisos': <house-view />
  'Negocio': <business-view />
*/
const changeStep = function (step) {
  state.currentStep = step
}

export default {
  state: shallowReadonly(state), 
  changeStep,
}