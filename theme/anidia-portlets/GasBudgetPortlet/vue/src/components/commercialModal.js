const commercialModal = {
  template: /*html*/`
  <div>
    <a aria-hidden="true" class="an-modal__close" href="#" @click.prevent="$emit('closeModal')"></a>
    <h4>Información comercial</h4>
    <p>Al marcar esta casilla, Ud., autoriza a Redexis Gas Servicios, S.L.U., y al resto de las empresas del grupo Redexis, a remitirle por cualquier medio, incluyendo medios electrónicos, comunicaciones comerciales relativas a productos y servicios relacionados con la gestión energética de su inmueble, ofertadas por las mismas. Estas comunicaciones podrán estar basadas en el análisis de los datos relativos a su persona que sean objeto de tratamiento y en la elaboración de un perfil que permita ofrecerle solamente aquella información que pueda resultar de su interés.</p>
    <p>Puede revocar su consentimiento y ejercitar sus derechos de acceso, rectificación, supresión, oposición, limitación del tratamiento y portabilidad dirigiendo un escrito al Delegado de Protección de Datos (<a class="an-link" href="mailto:dpo@redexisgas.es">dpo@redexisgas.es</a>)</p>
    <p>Puede consultar la política de privacidad de Redexis Gas Servicios, S.L.U. en la web <a href="https://www.redexisgas.es/protecciondedatos/" class="an-link" target="_blank">www.redexisgas.es/protecciondedatos</a></p>
  </div>`
}

export default commercialModal