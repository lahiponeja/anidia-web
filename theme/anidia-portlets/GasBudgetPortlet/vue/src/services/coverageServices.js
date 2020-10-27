import { http } from './index'

const coverageService = {
  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getPostalCodes: function() {
    return http.get(`postal-codes`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getMunicipalities: function(postalCode) {
    return http.get(`municipalities/${postalCode}`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getAddresses: function(municipalityId, postalCode) {
    return http.get(`addresses/${municipalityId}/${postalCode}`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getEstates: function(municipalityId, postalCode, addressKind, addressName) {
    return http.get(`estates/${municipalityId}/${postalCode}/${addressKind}/${addressName}`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getProperties: function(gateId) {
    return http.get(`properties/${gateId}`)
  },
}

export default coverageService
