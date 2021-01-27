import { http, httpSolar } from './index'

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
  getAvailability: function(postalCode) {
    return httpSolar.get(`availability?postalCode=${postalCode}`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getMunicipalities: function(postalCode) {
    return httpSolar.get(`municipalities/${postalCode}`)
  },

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  getAddresses: function(populationId, postalCode) {
    return httpSolar.get(`addresses/${populationId}/${postalCode}`)
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
