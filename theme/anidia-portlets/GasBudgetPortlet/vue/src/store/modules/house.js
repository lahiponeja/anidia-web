import {reactive, shallowReadonly} from '@vue/composition-api'
import coverageService from '../../services/coverageServices'
import houseFormService from '../../services/houseFormService'
import contactInfoService from '../../services/contactInfoService'
import xmlToJsonImp from '../../helpers/xmlToJsonImp'
import objToXml from '../../helpers/objToXml'

const state = reactive({
  houseSteps: [
    {
      name: "cobertura",
      component: "coverage-form",
      icon: "an-icon--lightning-borders",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: true,
    },
    {
      name: "vivienda",
      component: "house-form",
      icon: "an-icon--living-place",
      heading: {
        title: "SOLICITUD ONLINE",
        subtitle: "Cuéntanos cómo es tu hogar o negocio para que podamos redefinir tu energía",
      },
      active: false,
    },
    {
      name: "presupuesto",
      component: "budget-card",
      icon: "an-icon--euro-cable",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: false
    },
    {
      name: "presupuesto-realizado",
      component: "budget-ready",
      heading: {
        title: "PRESUPUESTO REALIZADO",
        subtitle: "Rellena los datos para recibir la oferta detallada, te llamamos o te la mandamos por email",
      },
      active: false
    },
  ],
  coverageData: {
    postalCode: {
      postalCode: "",
      municipalityName: "",
      municipalityId: "",
      provinceId: ""
    },
    estate: {
      addressKind: "",
      addressName: "",
      number: "",
      annex: "",
      gateId: "",
    },
    property: {
      address: "",
      propertyId: "",
      block: "",
      ladder: "",
      floor: "",
      door: "",
      status: "",
      contractStatus: ""
    }
  },
  autocompData: {
    postalCodes: [],
    municipalities: [],
    addresses: [],
    estates: [],
    properties: [],
  },
  houseFormData: {},
  gasBudget: {},
  userContactInfo: {},
  userFullName: "",
  coverageError: "",
  houseType: "",
  postalCode: "",
})


const resetAutocompleteData = function() {
  Object.assign(state.autocompData, {
    municipalities: [],
    addresses: [],
    estates: [],
    properties: [],
  })
}

const resetHouseFormData = function() {
  state.houseFormData = {}
}

const setCoverageData = function(key, payloadObj) {
  Object.assign(state.coverageData[key], payloadObj)
}

const submitUserContactInfo = function (budgetReadyForm) {
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
      "postalCode": state.coverageData.postalCode,
      "estate": state.coverageData.estate,
      "property": state.coverageData.property
    },
    "calculatorGas": {
      "input": {
        "zipCode": "",
        "houseType": state.houseType,
        "propertyMeters": state.houseFormData.propertyMeters,
        "staysNumber": state.houseFormData.staysNumber,
        "bathroomNumber": state.houseFormData.bathroomNumber,
        "floorNumber": state.houseFormData.floorNumber,
        "gasNaturalUse": state.houseFormData.gasNaturalUse,
        "acsUse": state.houseFormData.acsUse,
        "kitchenUse": state.houseFormData.kitchenUse,
        "heatingUse": state.houseFormData.heatingUse,
        "personsWater": state.houseFormData.personsWater,
        "boilerLocation": state.houseFormData.boilerLocation,
        "extras": {
          "metersBoilerToWindow": state.houseFormData.metersBoilerToWindow,
          "metersWaterIntake": state.houseFormData.metersWaterIntake,
          "hasVentilationGrill": state.houseFormData.hasVentilationGrill,
          "controllHeatingFloor": state.houseFormData.controllHeatingFloor,
          "connectDeviceToKitchen": state.houseFormData.connectDeviceToKitchen,
          "convertDeviceKitchen": state.houseFormData.convertDeviceKitchen,
          "radiatorsBathroom": state.houseFormData.radiatorsBathroom
        }
      },
      "output": {
        "proposedPack": state.gasBudget.proposedPack,
        "equipment": state.gasBudget.equipment,
        "baseBadget": state.gasBudget.baseBudget,
        "bonus": state.gasBudget.bonus,
        "totalBudget": state.gasBudget.totalBudget,
        "iva21": state.gasBudget.vat,
        "totalPVP": state.gasBudget.totalPrice,
        "extras": {
          "MetersBoilerToWindow": state.gasBudget.metersBoilerToWindow.price,
          "MetersWaterIntake": state.gasBudget.metersWaterIntake.price,
          "HasVentilationGrill": state.gasBudget.hasVentilationGrill.price,
          "ControllHeatingFloor": state.gasBudget.controllHeatingFloor.price,
          "ConvertDeviceKitchen": state.gasBudget.convertDeviceKitchen.price,
          "RadiatorsBathroom": state.gasBudget.radiatorsBathroom.price,
          "ExtraTotalPrice": state.gasBudget.extraTotalPrice
        }
      }
    }
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

const setPostalCode = function(payload) {
  state.postalCode = payload
}

const setHouseType = function(payload) {
  state.houseType = payload
}

const getPostalCodes = function () {
  coverageService.getPostalCodes().then((res) => {
    const resJson = xmlToJsonImp(res.data);
    const { items } = resJson.Page.items
    state.autocompData.postalCodes = items
  }).catch((err) => {
    console.error(err);
  })
}

const getMunicipalities = function(pc) {
  return new Promise((resolve, reject) =>{
    coverageService.getMunicipalities(pc).then((res) => {
      const resJson = xmlToJsonImp(res.data);
      const { items } = resJson.Page.items
      state.autocompData.municipalities = [items];
      resolve(items)
    }).catch((err) => {
      reject(err)
      console.error(err);
    })
  })
}

const getAddresses = function(municipalityId, postalCode) {
  return new Promise((resolve, reject) =>{
    coverageService.getAddresses(municipalityId, postalCode).then((res) => {
      const resJson = xmlToJsonImp(res.data);
      const { items } = resJson.Page.items
      const result = items
      state.autocompData.addresses = result.length ? result : [result]
      resolve(items)
    }).catch((err) => {
      reject(err)
      console.error(err);
    })
  })
}

const getEstates = function(municipalityId, postalCode, addressKind, addressName) {
  return new Promise((resolve, reject) =>{
    coverageService.getEstates(municipalityId, postalCode, addressKind, addressName).then((res) => {
      const resJson = xmlToJsonImp(res.data);
      const { items } = resJson.Page.items
      const result = items
      state.autocompData.estates = result.length ? result : [result]
      resolve(items)
    }).catch((err) => {
      reject(err)
      console.error(err);
    })
  })
}

const getProperties = function(gateId) {
  return new Promise((resolve, reject) =>{
    coverageService.getProperties(gateId).then((res) => {
      const resJson = xmlToJsonImp(res.data);
      const { items } = resJson.Page.items
      const result = items
      state.autocompData.properties = result.length ? result : [result]
      resolve(items)
    }).catch((err) => {
      reject(err)
      console.error(err);
    })
  })
}

const changeHouseStep = function (step) {
  const stepToChange = state.houseSteps.find((homeStep) => homeStep.name === step)
  state.houseSteps.forEach((homeStep) => homeStep.active = false)
  stepToChange.active = true
}

const setCoverageError = function(msg) {
  state.coverageError = msg
}

const setHouseFormData = function(payload) {
  state.houseFormData = payload
}

const submitHouseData = function(gasBudgetRequest) {
  const dataObj = Object.assign(gasBudgetRequest, {
    postalCode: state.postalCode,
    houseType: state.houseType,
  })
  const options = {
    rootName: 'GasBudgetRequest', // defaults to 'root'
    attributes: false
  }
  const xml = objToXml(dataObj, options)

  setHouseFormData(dataObj)

  const results = new Promise((resolve, reject) => {
    houseFormService.postHouseForm(xml).then((res)=> {
      const jsonData = xmlToJsonImp(res.data);
      Object.assign(state.gasBudget, jsonData.GasBudget);
      resolve(state.gasBudget)
      changeHouseStep("presupuesto");
    })
    .catch((err)=>{
      console.error(reject(err))
    })
  })

  return results;
}

const getDatalayerInitialInfo = function(category, action, label) {
  return {
    event: 'ananalyticsevent',
    eventdata: {
      category: category,
      action: action,
      label: label
    }
  }
}

const getDatalayerFirstStepInfo = function(category, action, label) {
  var datalayerInfo = this.getDatalayerInitialInfo(category, action, label);
  datalayerInfo.eventdata.installationtype = this.state.houseType;
  return datalayerInfo;
}

const getDatalayerAddressStepInfo = function(category, action, label) {
  var datalayerInfo = this.getDatalayerFirstStepInfo(category, action, label);
  datalayerInfo.eventdata.formcity = this.state.coverageData.postalCode.municipalityName;
  return datalayerInfo;
}

const getDatalayerDetailsStepInfo = function(category, action, label) {
  var datalayerInfo = this.getDatalayerAddressStepInfo(category, action, label);
  datalayerInfo.eventdata.formcity = this.state.coverageData.postalCode.municipalityName;
  datalayerInfo.eventdata.sqmeters = this.state.houseFormData.propertyMeters;
  datalayerInfo.eventdata.bathrooms = this.state.houseFormData.bathroomNumber;
  datalayerInfo.eventdata.rooms = this.state.houseFormData.staysNumber;
  datalayerInfo.eventdata.floors = this.state.houseFormData.floorNumber;
  datalayerInfo.eventdata.servicetype = this.state.houseFormData.gasNaturalUse;
  datalayerInfo.eventdata.previouswater = this.state.houseFormData.acsUse;
  datalayerInfo.eventdata.previouskitchen = this.state.houseFormData.kitchenUse;
  datalayerInfo.eventdata.previousheat = this.state.houseFormData.heatingUse;
  datalayerInfo.eventdata.sqmetersboilerroom = this.state.houseFormData.metersBoilerToWindow;
  datalayerInfo.eventdata.boilerventilation = this.state.houseFormData.hasVentilationGrill;
  datalayerInfo.eventdata.watteruse = this.state.houseFormData.personsWater;
  return datalayerInfo;
}

const getLeadFormStepInfo = function(category, action, label, hasEmail, hasPhone) {
  var datalayerInfo = this.getDatalayerDetailsStepInfo(category, action, label);
  datalayerInfo.eventdata.leademailhas = hasEmail ? "si" : "no";
  datalayerInfo.eventdata.leadphonehash = hasPhone ? "si" : "no";
  datalayerInfo.eventdata.leadcontactype = "presupuestadora gas"
  return datalayerInfo;
}

export default {
  state: shallowReadonly(state),
  setPostalCode,
  setHouseType,
  changeHouseStep,
  submitUserContactInfo,
  getPostalCodes,
  getMunicipalities,
  getAddresses,
  getEstates,
  getProperties,
  setCoverageError,
  submitHouseData,
  setCoverageData,
  submitBusinessContactInfo,
  resetAutocompleteData,
  resetHouseFormData,
  getDatalayerInitialInfo,
  getDatalayerFirstStepInfo,
  getDatalayerAddressStepInfo,
  getDatalayerDetailsStepInfo,
  getLeadFormStepInfo
}
