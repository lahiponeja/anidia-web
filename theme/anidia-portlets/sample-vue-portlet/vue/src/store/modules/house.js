import { http } from '../../services/http/index'
// import { reactive, readonly } from 'vue'
import { reactive, shallowReadonly } from '@vue/composition-api'
import coverageService from '../../services/coverageServices'
import houseFormService from '../../services/houseFormService'
import xmlToJsonImp from '../../helpers/xmlToJsonImp'
import objToXml from '../../helpers/objToXml'

const state = reactive({
  postalCode: "05500", // JUST FOR DEBBUGGING
  houseType: "Unifamiliar", // JUST FOR DEBBUGGING
  houseSteps: [
    {
      name: "cobertura",
      icon: "an-icon--lightning-borders",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: true,
    },
    {
      name: "vivienda",
      icon: "an-icon--living-place",
      heading: {
        title: "SOLICITUD ONLINE",
        subtitle: "Cuéntanos cómo es tu hogar o negocio para que podamos redefinir tu energía",
      },
      active: false,
    },
    {
      name: "presupuesto",
      icon: "an-icon--euro-cable",
      heading: {
        title: "TU AHORRO EMPIEZA AQUÍ",
        subtitle: "Disfruta de un calor homogéneo y una temperatura constante en el agua caliente gracias a un suministro sin interrupciones que no ocupa espacio de almacenamiento",
      },
      active: false
    },
    {
      name: "presupuesto-realizado",
      heading: {
        title: "PRESUPUESTO REALIZADO",
        subtitle: "Rellena los datos para recibir la oferta detallada, te llamamos o te la mandamos por email",
      },
      active: false
    },
  ],
  // currentStep: "cobertura",
  // currentStep: "vivienda",
  autocompData: {
    postalCodes: [],
    municipalities: [],
    addresses: [],
    estates: [],
    properties: [],
  },
  coverageError: "",
  gasBudget: {}
})

const setPostalCode = function(payload) {
  state.postalCode = payload
}

const setHouseType = function(payload) {
  console.log("house.js", payload)
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
  coverageService.getMunicipalities(pc).then((res) => {
    const resJson = xmlToJsonImp(res.data);
    const { items } = resJson.Page.items
    state.autocompData.municipalities = [items];
  }).catch((err) => {
    console.error(err);
  })
}

const getAddresses = function(municipalityId, postalCode) {
  coverageService.getAddresses(municipalityId, postalCode).then((res) => {
    const resJson = xmlToJsonImp(res.data);
    const { items } = resJson.Page.items
    const result = items
    if(result.length) {
      state.autocompData.addresses = result
    } else {
      state.autocompData.addresses = []
    }
  }).catch((err) => {
    console.error(err);
  })
}

const getEstates = function(municipalityId, postalCode, addressKind, addressName) {
  coverageService.getEstates(municipalityId, postalCode, addressKind, addressName).then((res) => {
    const resJson = xmlToJsonImp(res.data);
    const { items } = resJson.Page.items
    const result = items
    state.autocompData.estates = result
  }).catch((err) => {
    console.error(err);
  })
}

const getProperties = function(gateId) {
  coverageService.getProperties(gateId).then((res) => {
    const resJson = xmlToJsonImp(res.data);
    const { items } = resJson.Page.items
    const result = items
    state.autocompData.properties = [result]
    
  }).catch((err) => {
    console.error(err);
  })
}

const changeStep = function (step) {
  const stepToChange = state.houseSteps.find((homeStep) => homeStep.name === step)
  state.houseSteps.forEach((homeStep) => homeStep.active = false)
  stepToChange.active = true
  state.currentStep = step;
}

const setCoverageError = function(msg) {
  state.coverageError = msg
}

const submitHouseData = function(gasBudgetRequest) {
  
  const options = {
    rootName: 'GasBudgetRequest', // defaults to 'root'
    attributes: false
  }

  const dataObj = Object.assign(gasBudgetRequest, {
    postalCode: state.postalCode,
    houseType: state.houseType,
  })

  const xml = objToXml(dataObj, options)

  houseFormService.postHouseForm(xml).then((res)=> {
    const jsonData = xmlToJsonImp(res.data);
    Object.assign(state.gasBudget, jsonData.GasBudget);
    console.log("state.gasBudget", state.gasBudget);
    changeStep("presupuesto");
  })
  .catch((err)=>{
    console.error(err)
  })

  console.log(xml)
}

const submitUserContactInfo = function (budgetReadyForm) {
  const { 
    name,
    lastname,
    phone,
    email,
    privacyPolicy,
    offersAndServices } = budgetReadyForm;

    console.log("budgetReadyForm", budgetReadyForm);
}

export default { 
  state: shallowReadonly(state), 
  setPostalCode,
  setHouseType,
  changeStep,
  submitUserContactInfo,
  getPostalCodes,
  getMunicipalities,
  getAddresses,
  getEstates,
  getProperties,
  setCoverageError,
  submitHouseData,
}