import { reactive, readonly } from 'vue'

const state = reactive({
  formMainData: {
    houseType: "Testing house type",
    propertyMeters: 0,
  },
  currentStep: "funnel",
})

const changeStep = function (step) {
  state.currentStep = step
}

export default { 
  state: readonly(state), 
  changeStep,
}