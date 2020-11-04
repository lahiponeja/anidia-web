// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
  // optionsArr: [
  //   {
  //     name: "funnel",
  //     component: 'funnel-view',
  //     active: true,
  //   },
  //   {
  //     name: 'Unifamiliar',
  //     component: '',
  //     title: 'Hogar unifamiliar',
  //     icon: 'an-icon--unifamiliar-home',
  //   },
  //   {
  //     name: 'Bloque de pisos',
  //     component: '',
  //     title: 'Piso en bloque de viviendas',
  //     icon: 'an-icon--apartments',
  //   },
  //   {
  //     name: 'Negocio',
  //     component: '',
  //     title: 'Negocio',
  //     icon: 'an-icon--business',
  //   },
  // ],
  mainViewsArr: [
    {
      name: "funnel",
      component: 'funnel-view',
      active: true,
    },
    {
      name: 'Unifamiliar',
      component: 'house-view',
      title: 'Hogar unifamiliar',
      icon: 'an-icon--unifamiliar-home',
      active: false,
    },
    {
      name: 'Bloque de pisos',
      component: 'house-view',
      title: 'Piso en bloque de viviendas',
      icon: 'an-icon--apartments',
      active: false,
    },
    {
      name: 'Negocio',
      component: 'business-view',
      title: 'Negocio',
      icon: 'an-icon--business',
      active: false,
    },
  ],
})

const changeView = function (stepName) {
  const viewToChange = state.mainViewsArr.find((viewItem) => viewItem.name === stepName)
  state.mainViewsArr.forEach((viewItem) => viewItem.active = false)
  viewToChange.active = true
  activeView();
}

const activeView = function() {
  return state.mainViewsArr.find((viewItem) => viewItem.active)
}

export default {
  state: shallowReadonly(state), 
  changeView,
  activeView,
}