import { reactive, shallowReadonly } from '@vue/composition-api'
import objToXml from '../../helpers/objToXml'
import xmlToJsonImp from '../../helpers/xmlToJsonImp'
import savingsService from '../../services/savingsService'
import global from './global'

const state = reactive({
  savingsByConsumption: {
    acsUse: false,
    heatingUse: false,
    kitchenUse: false,
    energyType: "",
    electricityConsumption: 0,
  },

  gasConsumptionComparison: {
    consumptionRequired: "",
    currentCost: "",
    futureCost: "",
    savings: "",
  },

  savingsByUse: {
    province: "",
    acsIndividual: true,
    acsUse: "",
    numberOfPeople: 1,
    heatingIndividual: true,
    heatingUse: "",
    singleFamilyHouse: true,
    lastFloor: true,
    surfaceHouse: 0,
    kitchenUse: "",
    weeklyKitchenUse: 0,
  },

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

const setGasConsumptionComparison = function (payload) {
  Object.assign(state.gasConsumptionComparison, payload.GasConsumptionComparison)
}

const setSavingsByConsumption = function(obj) {
  Object.assign(state.savingsByConsumption, obj)

  const options = {
    rootName: 'GasCalculatedConsumption', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(obj, options)
  console.log(xml)
  
  return new Promise((resolve, reject) => {
    savingsService.postSavingsByConsumption(xml)
      .then((res) => {
        const json = xmlToJsonImp(res.data)
        setGasConsumptionComparison(json)
        global.changeView('comparator')
        changeStepComponent('comp-saving')
        resolve(res)
      })
      .catch((err) => {
        console.log("setSavingsByConsumption", err)
        reject(err)
      })
  })
}

const setSavingByUse = function (obj) {
  Object.assign(state.savingsByUse, obj)
}

const sendSavingByUseService = function(obj) {
  const options = {
    rootName: 'GasConsumptionByUse', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(obj, options)
  console.log(xml)
  return new Promise((resolve, reject) => {
    savingsService.postSavingsByUse(xml)
      .then((res) => {
        const json = xmlToJsonImp(res.data)
        setGasConsumptionComparison(json)
        resolve(res)
      })
      .catch((err) => {
        console.log("setSavingsByConsumption", err)
        reject(err)
      })
  })
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

const submitUserContactInfo = function (compSavingForm) {
  const { 
    name,
    lastname,
    phone,
    email,
    privacyPolicy,
    offersAndServices } = compSavingForm;

  const requestBody = {
    "personalData": {
      "firstName": name,
      "lastName": lastname,
      "email": email,
      "phone": phone,
      "acceptNotCom": offersAndServices,
    }
  }

  const options = {
    rootName: 'Lead', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(requestBody, options)

  const result = new Promise((resolve, reject) => {
    savingsService.postLeads(xml).then((res) => {
      resolve(res)
    }).catch((err) => {
      console.error(err)
    })
  })

  return result
}

export default {
  state: shallowReadonly(state),
  setSavingsByConsumption,
  setSavingByUse,
  changeStepComponent,
  activeComponent,
  submitUserContactInfo,
  sendSavingByUseService,
}