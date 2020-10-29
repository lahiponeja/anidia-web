import { http, httpBudget } from './index'

const savingsService = {
  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postSavingsByConsumption: function(payload) {
    return http.post('savings-by-consumption', payload)
  },

  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postSavingsByUse: function(payload) {
    return http.post('savings-by-use', payload)
  },

  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postLeads: function(payload) {
    return httpBudget.post('leads', payload)
  },

}

export default savingsService
