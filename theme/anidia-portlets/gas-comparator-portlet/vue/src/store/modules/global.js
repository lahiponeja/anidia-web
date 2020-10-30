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
  // activeStep
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

/*
Possible steps:
  'funnel': <funnel-view />
  'comparator': <comparator-view />
*/
// const changeStep = function (step) {
//   state.currentStep = step
// }

export default {
  state: shallowReadonly(state), 
  changeView,
  activeView,
}