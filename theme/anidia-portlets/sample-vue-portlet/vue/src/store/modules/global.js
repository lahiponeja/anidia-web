// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
  currentStep: "funnel",
})

const changeStep = function (step) {
  state.currentStep = step
}

export default {
  state: shallowReadonly(state), 
  changeStep,
}