const leadDefault = function() {
  return {
   personalData: {
     firstName: "",
     lastName: "",
     email: "",
     phone: "",
     prodInterest: "solar",
     acceptNotCom: false,
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
       address: ""// Aquí metemos el texto libre del piso
     }
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
     superiorInstallation: false,
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
}

export default leadDefault