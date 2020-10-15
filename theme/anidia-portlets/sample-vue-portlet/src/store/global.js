import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
  formMainData: {
    houseType: "Testing house type",
    propertyMeters: 0,
  }
})

export default { 
  state: shallowReadonly(state), 
}