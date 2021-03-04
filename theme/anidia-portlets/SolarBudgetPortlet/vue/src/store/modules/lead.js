import {reactive, shallowReadonly} from '@vue/composition-api'
import coverageService from '../../services/coverageServices'
import houseFormService from '../../services/houseFormService'
import contactInfoService from '../../services/contactInfoService'
import xmlToJsonImp from '../../helpers/xmlToJsonImp'
import objToXml from '../../helpers/objToXml'
import leadDefault from '../leadDefault'
import solarBudgetDefault from '../solarBudgetDefault'
import house from './house'

const state = reactive({
  lead: leadDefault(),
  solarBudget: solarBudgetDefault(),
  userFullName: ""
})

const setInstallerCode = function(installerCode) {
  state.lead.calculatorSolar.InstallerCode = installerCode
}

const setPostalCodeData = function(payload) {
  const { postalCode } = state.lead.personalData
  Object.assign(postalCode, payload)
}

const setEstateData = function(payload) {
  const { estate } = state.lead.personalData
  Object.assign(estate, payload)
}

const setAddress = function(address) {
  const { property } = state.lead.personalData
  property.address = address
}

const setSolarBudget = function(solarBudget) {
  Object.assign(state.solarBudget, solarBudget);
  Object.assign(state.lead.calculatorSolar.output, solarBudget);
}

const setSuperiorInstalation = function(bool) {
  state.lead.calculatorSolar.superiorInstallation = bool
}

const setSelectedExtras = function(selectedExtrasObj) {
  Object.assign(state.lead.calculatorSolar.selectedExtras, {
    extraPanels: selectedExtrasObj.panelsExtra,
    pipelineUnderground: selectedExtrasObj.pipelineExtra,
    triphasicExtra: selectedExtrasObj.triphasicExtra ? 'SI' : 'NO',
    roofExtra: selectedExtrasObj.roofExtra ? 'SI' : 'NO',
    pergolaExtra: selectedExtrasObj.pergolaExtra ? 'SI' : 'NO',
    inverterExtra: selectedExtrasObj.inverterExtra ? 'SI' : 'NO'
  })
}

const setFinalPrice = function(finalPrice, ivaPrice, finalPriceIva) {
  state.lead.calculatorSolar.finalPrice = finalPrice
  state.lead.calculatorSolar.ivaPrice = ivaPrice
  state.lead.calculatorSolar.finalPriceIva = finalPriceIva
}

const submitHouseData = function(solarBudgetRequest) {
  const { input } = state.lead.calculatorSolar

  const dataObj = Object.assign(input, solarBudgetRequest)
  const options = {
    rootName: 'SolarBudgetRequest', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(dataObj, options)

  const results = new Promise((resolve, reject) => {
    houseFormService.postHouseForm(xml).then((res)=> {
      const jsonData = xmlToJsonImp(res.data);
      setSolarBudget(jsonData.SolarBudget)
      resolve(state.solarBudget)
      house.changeHouseStep("presupuesto");
    })
    .catch((err)=>{
      console.error(reject(err))
    })
  })

  return results;
}

const submitLead = function (payload) {

  Object.assign(state.lead.personalData, {
    firstName: payload.name,
    lastName: payload.lastname,
    email: payload.email,
    phone: payload.phone,
    acceptNotCom: payload.offersAndServices
  })

  state.userFullName = `${payload.name} ${payload.lastname}`

  const options = {
    rootName: 'Lead', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(state.lead, options)

  const result = new Promise((resolve, reject) => {
    contactInfoService.postLeads(xml).then((res) => {
      resolve(res)
    }).catch((err) => {
      console.error(err)
    })
  })

  return result
}

export default {
  state: shallowReadonly(state),
  setInstallerCode,
  setPostalCodeData,
  setEstateData,
  setAddress,
  submitHouseData,
  setSuperiorInstalation,
  setSelectedExtras,
  setFinalPrice,
  submitLead
}

/*
const submitLead = function (budgetReadyForm) {
  const {
    name,
    lastname,
    phone,
    email,
    privacyPolicy,
    offersAndServices } = budgetReadyForm;

  const requestBody = state.lead

  state.userFullName = `${name} ${lastname} `

  const options = {
    rootName: 'Lead', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(requestBody, options)

  const result = new Promise((resolve, reject) => {
    contactInfoService.postLeads(xml).then((res) => {
      resolve(res)
    }).catch((err) => {
      console.error(err)
    })
  })

  return result
}
*/

/*
const submitBusinessContactInfo = function (budgetReadyForm) {
  const {
    name,
    lastname,
    phone,
    email,
    privacyPolicy,
    offersAndServices } = budgetReadyForm;

  const requestBody = {
    "personalData": {
      "firstName": name,
      "lastName": lastname,
      "email": email,
      "phone": phone,
      "prodInterest": "gas",
      "acceptNotCom": offersAndServices,
    },
  }

  state.userFullName = `${name} ${lastname} `

  const options = {
    rootName: 'Lead', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(requestBody, options)

  const result = new Promise((resolve, reject) => {
    contactInfoService.postLeads(xml).then((res) => {
      resolve(res)
    }).catch((err) => {
      console.error(err)
    })
  })

  return result
}
*/