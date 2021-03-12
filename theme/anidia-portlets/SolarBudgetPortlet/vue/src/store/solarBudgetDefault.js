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
    superiorInverterExtra: "",
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
      superiorInverterExtra: "",
      panelsExtra: "",
      pergolaExtra: "",
      pipelineExtra: "",
      roofExtra: "",
      superiorSize: {
        basePanels: "",
        price: "",
        value: ""
      },
      triphasicExtra: "",
      totalPowerInstalled: ""
    },
    totalPrice: "",
    totalPowerInstalled: "",
    triphasicExtra: ""
  }
}

export default solarBudgetDefault