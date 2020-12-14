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
      name: "comparator",
      component: 'comparator-view',
      active: false,
    },
  ],
  modalOpen: false,
  modalSettings: {
    type: 'default',
    componentName: ''
  }
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

export default {
  state: shallowReadonly(state), 
  changeView,
  activeView,
  changeModalStatus
}