const solarBudgetDefault = function() {
  return {
    additionalPanelsInstallation: "",
    battery: "",
    carCharger: "",
    inverter: {
       brand: "",
       model: null,
       price: "0"
    },
    inverterExtra: "",
    panelsExtra: "",
    panelsType: "",
    pergolaExtra: "",
    pipelineExtra: "",
    roofExtra: "",
    size: {
       basePanels: "",
       price: "",
       totalPanels: "",
       unitPrice: null,
       value: ""
    },
    superiorInstallation: {
       additionalPanelsInstallation: "",
       battery: "",
       carCharger: "",
       extraFornius: "",
       inverterExtra: "",
       panelsExtra: "",
       pergolaExtra: "",
       pipelineExtra: "",
       roofExtra: "",
       superiorSize: {
          basePanels: "",
          price: "",
          value: ""
       },
       triphasicExtra: ""
    },
    totalPrice: "",
    triphasicExtra: ""
  }
}

export default solarBudgetDefault