// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'

const state = reactive({
  savingsByConsumption: {
    acsUse: false,
    heatingUse: false,
    kitchenUse: false,
    energyType: "",
    electricityConsumption: 0,
  },

  savingsByUse: {
    /* 
    	<province>string</province>
	<acsIndividual>true</acsIndividual>
	<acsUse>Butano</acsUse>
	<numberOfPeople>0</numberOfPeople>
	<heatingIndividual>true</heatingIndividual>
	<heatingUse>Butano</heatingUse>
	<singleFamilyHouse>true</singleFamilyHouse>
	<lastFloor>true</lastFloor>
	<surfaceHouse>string</surfaceHouse>
	<kitchenUse>Butano</kitchenUse>
	<weeklyKitchenUse>0</weeklyKitchenUse>
    */

  
  },

  province: "",

  comparatorStepsArr: [
    {
      name: "Agua caliente",
      component: "comp-hot-water",
      icon: "an-icon--hot-water",
      active: true,
    },
    {
      name: "Calefacción",
      component: "comp-heating",
      icon: "an-icon--thermometer",
      active: false,
    },
    {
      name: "Cocina",
      component: "comp-kitchen",
      icon: "an-icon--kitchen",
      active: false,
    },
    {
      name: "Ahorro",
      component: "comp-saving",
      icon: "an-icon--saving",
      active: false,
    },
  ],
})

const setSavingsByConsumption = function(obj) {
  Object.assign(state.savingsByConsumption, obj)

  console.log("🔥 state.savingsByConsumption 🔥")
  console.log(state.savingsByConsumption)
}

const setProvince = function(province) {
  state.province = province

  console.log("🌮 state.province 🌮")
  console.log(state.province)
}

const setSavingsByUse = function(obj) {
 // TODO
}

const changeStepComponent = function (componentName) {
  const compToChange = state.comparatorStepsArr.find((compItem) => compItem.component === componentName)
  state.comparatorStepsArr.forEach((compItem) => compItem.active = false)
  compToChange.active = true
  activeComponent();
}

const activeComponent = function() {
  return state.comparatorStepsArr.find((compItem) => compItem.active)
}

export default {
  state: shallowReadonly(state),
  setSavingsByConsumption,
  setProvince,
  changeStepComponent,
  activeComponent,
}