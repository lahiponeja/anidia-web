// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
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
  modalOpen: false,
  modalSettings: {
    type: 'default',
    componentName: ''
  }
})

const setModalStatus = function(payload) {
  state.modalOpen = payload
}

const setModalSettings = function(payload) {
  state.modalSettings = payload
}

const changeModalStatus = function(payload) {
  const { open, options } = payload
  setModalStatus(open)
  if (options) {
    setModalSettings(options)
  }
}

const changeView = function (name) {
  const viewToChange = state.mainViewsArr.find((viewItem) => viewItem.name === name)
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
  changeModalStatus
}