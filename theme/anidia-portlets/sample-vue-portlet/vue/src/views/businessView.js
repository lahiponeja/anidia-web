const businessView = {
  inject: ["global"],
  template: /*html*/`
    <div>
      <div @click="global.changeStep('funnel')">← Go back</div>
      <h2>🏢 businessView: Tu ahorro empieza aquí...</h2>
      <h3>Cobertura</h3>
    </div>
  `
}

export default businessView;