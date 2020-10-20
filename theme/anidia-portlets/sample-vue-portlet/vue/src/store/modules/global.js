import { reactive, readonly } from 'vue'

const state = reactive({
  currentStep: "funnel",
})

const changeStep = function (step) {
  state.currentStep = step
}

export default {
  state: readonly(state), 
  changeStep,
}