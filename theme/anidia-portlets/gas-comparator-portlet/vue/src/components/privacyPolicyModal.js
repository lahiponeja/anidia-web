const privacyPolicyModal = {
  template: /*html*/`
  <div>
    <a aria-hidden="true" class="an-modal__close" href="#" @click.prevent="$emit('closeModal')"></a>
    <h4>Política de privacidad</h4>
    <table>
      <tbody>
        <tr>
          <td>Responsable del tratamiento</td>
          <td>Redexis Gas Servicios, S.L.U. Domicilio: calle Mahonia, nº 2, 28043, Madrid</td>
        </tr>
        <tr>
          <td>Finalidades del tratamiento</td>
          <td>Gestionar su solicitud así como la relación contractual relativa a los servicios y productos contratados, y para enviarle por cualquier medio información comercial relacionada con el sector energético en el caso en que así lo desee</td>
        </tr>
        <tr>
          <td>Legitimación del tratamiento</td>
          <td>Existencia de un contrato o precontrato. Consentimiento del interesado</td>
        </tr>
        <tr>
          <td>Destinatarios de la información</td>
          <td>Empresas colaboradoras de Redexis Gas Servicios, S.L.U., y empresas pertenecientes al grupo Redexis que necesiten acceder a los mismos para la prestación de los servicios solicitados o contratados</td>
        </tr>
        <tr>
          <td>Sus derechos</td>
          <td>Puede revocar su consentimiento y ejercitar sus derechos de acceso, rectificación, supresión, oposición, limitación del tratamiento y portabilidad dirigiendo un escrito al Delegado de Protección de Datos (<a class="an-link" href="mailto:dpo@redexisgas.es">dpo@redexisgas.es</a>)</td>
        </tr>
        <tr>
          <td colspan="2">Puede consultar la política de privacidad de Redexis Gas Servicios, S.L.U. en la web <a href="https://www.redexisgas.es/protecciondedatos/" class="an-link" target="_blank">www.redexisgas.es/protecciondedatos</a></td>
        </tr>
      </tbody>
    </table>
  </div>`
}

export default privacyPolicyModal