import {reactive, shallowReadonly} from '@vue/composition-api'
import coverageService from '../../services/coverageServices'
import houseFormService from '../../services/houseFormService'
import contactInfoService from '../../services/contactInfoService'
import xmlToJsonImp from '../../helpers/xmlToJsonImp'
import objToXml from '../../helpers/objToXml'

const state = reactive({
  lead: {
    personalData: {
      firstName: "",
      lastName: "",
      email: "",
      phone: "",
      prodInterest: "solar",
      acceptNotCom: true,
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
        annex: "",//Letra 
        gateId: ""// Código redexis de portal,
      },
      property: {
        address: ""
      }
      // property: {// Para solar,no se informa
      //   address: "string"// Para solar,no se informa
      //   propertyId: "string",// Para solar,no se informa
      //   block: "string",// Para solar,no se informa
      //   ladder: "string",// Para solar,no se informa
      //   floor: "string",// Para solar,no se informa
      //   door: "string",// Para solar,no se informa
      //   status: "string",//Para solar,no se informa
      //   contractStatus: //Para solar,no se informa
      // }
    },
  
    calculatorSolar: {
      input: {
        houseType: "",
        monthlyConsumption: "",
        roofType: ""
      }, 
      selectedExtras: {
        extraPanels: "",
        triphasicExtra: "",
        roofExtra: "",
        pergolaExtra: "",
        pipelineUnderground: "",
        battery: "",
        carCharger: ""
      },
      superiorInstallation: true,
      output: {
        panelsType: "",
        size: {
          value: "",
          unitPrice: "",
          price: "",
          basePanels: "",
          totalPanels: ""
        },
        inverter: {
          brand: "",
          model: "",
          price: ""
        },
        panelsExtra: "",
        triphasicExtra: "",
        inverterExtra: "",
        roofExtra: "",
        pergolaExtra: "",
        pipelineExtra: "",
        carCharger: "",
        battery: "",
        additionalPanelsInstallation: "",
        totalPrice: "",
        superiorInstallation: {
          superiorSize: {
            value: "",
            price: "",
            basePanels: ""
          },
          panelsType: "",
          inverterType: "",
          extraFornius: "",
          panelsExtra: "",
          triphasicExtra: "",
          inverterExtra: "",
          roofExtra: "",
          pergolaExtra: "",
          pipelineExtra: "",
          carCharger: "",
          battery: "",
          additionalPanelsInstallation: ""
        }
      },
      finalPrice: "",
      InstallerCode: ""
    }
  }
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
      console.log(jsonData)
      // Object.assign(state.gasBudget, jsonData.GasBudget);
      // resolve(state.gasBudget)
      // changeHouseStep("presupuesto");
    })
    .catch((err)=>{
      console.error(reject(err))
    })
  })

  return results;
}

export default {
  state: shallowReadonly(state),
  setInstallerCode,
  setPostalCodeData,
  setEstateData,
  setAddress,
  submitHouseData
}

/*
const submitUserContactInfo = function (budgetReadyForm) {
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