import { http } from './http/index'

class coverageService {
  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  static getPostalCodes() {
    return http.get(`/postal-codes`)
  }

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  static getMunicipalities(postalCode) {
    return http.get(`/municipalities/${postalCode}`)
  }

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  static getAddresses(municipalityId, postalCode) {
    return http.get(`/municipalities/${municipalityId}/${postalCode}`)
  }

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  static getEstates(municipalityId, postalCode, addressKind, addressName) {
    return http.get(`/municipalities/${municipalityId}/${postalCode}/${addressKind}/${addressName}`)
  }

  /**
   * Request new example
   * API GET Method
   * @param { string } id
   */
  static getProperties(gateId) {
    return http.get(`/properties/${gateId}`)
  }
}

export default coverageService
